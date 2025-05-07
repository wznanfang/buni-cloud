package com.buni.user.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SmUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buni.file.FileDubboService;
import com.buni.framework.config.exception.CustomException;
import com.buni.framework.config.redis.RedisService;
import com.buni.framework.constant.CommonConstant;
import com.buni.framework.util.DateUtil;
import com.buni.framework.util.EncryptUtil;
import com.buni.user.entity.SysUser;
import com.buni.user.enums.BooleanEnum;
import com.buni.user.enums.ErrorEnum;
import com.buni.user.mapper.SysUserMapper;
import com.buni.user.properties.PasswordProperties;
import com.buni.user.service.SysAuthService;
import com.buni.user.service.SysUserRoleService;
import com.buni.user.service.SysUserService;
import com.buni.user.vo.IdVOs;
import com.buni.user.vo.user.*;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

/**
 * @author Administrator
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2023-09-19 10:52:51
 */
@Slf4j
@Service
@AllArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    private PasswordProperties passwordProperties;
    @Resource
    private RedisService redisService;
    @Resource
    private SysUserRoleService sysUserRoleService;
    @Resource
    private SysAuthService sysAuthService;
    @DubboReference
    private FileDubboService fileDubboService;


    /**
     * 新增
     *
     * @param addVO 新增信息
     * @return true/false
     */
    @Override
    public boolean save(AddVO addVO) {
        SysUser existSysUser = super.getOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, addVO.getUsername()).or().eq(SysUser::getPhone, addVO.getPhone()));
        if (ObjUtil.isNotEmpty(existSysUser)) {
            throw new CustomException(ErrorEnum.USER_EXISTS.getCode(), ErrorEnum.USER_EXISTS.getMessage());
        }
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(addVO, sysUser);
        String password = EncryptUtil.decrypt(addVO.getPassword());
        sysUser.setPassword(SmUtil.sm3(passwordProperties.getSalt() + password));
        return super.save(sysUser);
    }


    private SysUser getUser(Long id) {
        SysUser sysUser = super.getById(id);
        if (ObjUtil.isEmpty(sysUser)) {
            throw new CustomException(ErrorEnum.USER_NOT_EXISTS.getCode(), ErrorEnum.USER_NOT_EXISTS.getMessage());
        }
        return sysUser;
    }


    /**
     * 编辑用户基本信息
     *
     * @param updateVO 用户信息
     * @return true/false
     */
    @Override
    public boolean update(UpdateVO updateVO) {
        SysUser sysUser = getUser(updateVO.getId());
        SysUser existSysUser = super.getOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getPhone, updateVO.getPhone()));
        if (ObjUtil.isNotEmpty(existSysUser) && !existSysUser.getId().equals(sysUser.getId())) {
            throw new CustomException(ErrorEnum.USER_EXISTS.getCode(), ErrorEnum.USER_EXISTS.getMessage());
        }
        SysUser updateSysUser = new SysUser();
        BeanUtils.copyProperties(updateVO, updateSysUser);
        super.updateById(updateSysUser);
        redisService.deleteKey(SysUser.REDIS_KEY + updateVO.getId());
        return true;
    }


    private void deleteToken(List<Long> userIds) {
        List<String> tokens = sysAuthService.findByUserId(userIds);
        if (ObjUtil.isNotEmpty(tokens)) {
            List<String> tokenKeys = new ArrayList<>();
            tokens.forEach(token -> {
                tokenKeys.add(CommonConstant.TOKEN_REDIS_KEY + token);
            });
            redisService.delAllByKeys(tokenKeys);
        }
    }


    /**
     * 根据id删除用户
     *
     * @param id 用户id
     * @return true/false
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {
        getUser(id);
        super.removeById(id);
        sysUserRoleService.deleteByUserId(id);
        redisService.deleteKey(SysUser.REDIS_KEY + id);
        deleteToken(Collections.singletonList(id));
        return true;
    }


    /**
     * 批量删除
     *
     * @param idVOs 用户id集合
     * @return true/false
     */
    @Override
    public Boolean batchDelete(IdVOs idVOs) {
        List<Long> ids = idVOs.getIds();
        super.removeByIds(ids);
        sysUserRoleService.deleteByUserIds(ids);
        List<String> deleteKeys = ids.stream().map(id -> SysUser.REDIS_KEY + id).toList();
        redisService.delAllByKeys(deleteKeys);
        deleteToken(ids);
        return true;
    }


    /**
     * 根据id查询用户基本信息
     *
     * @param id 用户id
     * @return {@link UserInfoVO}
     */
    @Override
    public UserInfoVO findById(Long id) {
        UserInfoVO userInfoVO = (UserInfoVO) redisService.get(SysUser.REDIS_KEY + id);
        if (ObjUtil.isEmpty(userInfoVO)) {
            SysUser sysUser = getUser(id);
            userInfoVO = new UserInfoVO();
            BeanUtils.copyProperties(sysUser, userInfoVO);
            userInfoVO.setAvatar(ObjUtil.isEmpty(sysUser.getAvatar()) ? "" : fileDubboService.preview(sysUser.getAvatar()));
            redisService.setOneDay(SysUser.REDIS_KEY + sysUser.getId(), userInfoVO);
        }
        return userInfoVO;
    }


    /**
     * 分页查询用户信息
     *
     * @param pageVO 分页信息
     * @return {@link Page}<{@link UserInfoVO}>
     */
    @Override
    public IPage<UserGetVO> findPage(PageVO pageVO) {
        IPage<SysUser> ipage = new Page<>(pageVO.getCurrent(), pageVO.getSize());
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ne(SysUser::getId, CommonConstant.ADMIN_ID);
        if (ObjectUtil.isNotEmpty(pageVO.getInputSearch())) {
            queryWrapper.like(SysUser::getUsername, pageVO.getInputSearch()).or().like(SysUser::getName, pageVO.getInputSearch());
        }
        IPage<SysUser> infoPage = super.page(ipage, queryWrapper);
        IPage<UserGetVO> resultPage = new Page<>(infoPage.getCurrent(), infoPage.getSize(), infoPage.getTotal());
        List<UserGetVO> list = Optional.ofNullable(infoPage.getRecords()).orElse(new ArrayList<>()).stream().map(user -> {
            UserGetVO getVO = new UserGetVO();
            BeanUtils.copyProperties(user, getVO);
            getVO.setPhone(DesensitizedUtil.mobilePhone(getVO.getPhone()));
            return getVO;
        }).toList();
        resultPage.setRecords(list);
        return resultPage;
    }


    /**
     * 根据id 禁用用户
     *
     * @param enableVO 启用/禁用信息
     * @return true/false
     */
    @Override
    public boolean enable(EnableVO enableVO) {
        getUser(enableVO.getId());
        SysUser forbiddenSysUser = new SysUser();
        BeanUtils.copyProperties(enableVO, forbiddenSysUser);
        super.updateById(forbiddenSysUser);
        redisService.deleteKey(SysUser.REDIS_KEY + enableVO.getId());
        if (BooleanEnum.NO.equals(enableVO.getEnable())) {
            deleteToken(Collections.singletonList(enableVO.getId()));
        }
        return true;
    }


    /**
     * 批量启用/禁用用户
     *
     * @param batchEnableVO 批量启用/禁用信息
     * @return true/false
     */
    @Override
    public boolean batchEnable(BatchEnableVO batchEnableVO) {
        if (ObjUtil.isEmpty(batchEnableVO.getIdVOs().getIds())) {
            return true;
        }
        List<Long> userIds = batchEnableVO.getIdVOs().getIds();
        super.update(Wrappers.<SysUser>lambdaUpdate().in(SysUser::getId, userIds).set(SysUser::getEnable, batchEnableVO.getEnable()));
        List<String> deleteKeys = userIds.stream().map(id -> SysUser.REDIS_KEY + id).toList();
        redisService.delAllByKeys(deleteKeys);
        if (BooleanEnum.NO.equals(batchEnableVO.getEnable())) {
            deleteToken(batchEnableVO.getIdVOs().getIds());
        }
        return true;
    }


    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return {@link SysUser}
     */
    @Override
    public SysUser findByUsername(String username) {
        return super.getOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, username));
    }


    /**
     * 根据openId查询用户
     *
     * @param openId
     * @return
     */
    @Override
    public SysUser findByOpenId(String openId) {
        return super.getOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getWxOpenId, openId));
    }


    /**
     * 修改密码
     *
     * @param updatePasswordVO 修改密码信息
     * @return true/false
     */
    @Override
    public Boolean updatePassword(UpdatePasswordVO updatePasswordVO) {
        SysUser sysUser = getUser(updatePasswordVO.getId());
        if (!SmUtil.sm3(passwordProperties.getSalt() + updatePasswordVO.getOldPassword()).equals(sysUser.getPassword())) {
            throw new CustomException(ErrorEnum.OLD_PASSWORD_ERROR.getCode(), ErrorEnum.OLD_PASSWORD_ERROR.getMessage());
        }
        if (SmUtil.sm3(passwordProperties.getSalt() + updatePasswordVO.getNewPassword()).equals(sysUser.getPassword())) {
            throw new CustomException(ErrorEnum.EQUALS_OLD_PASSWORD.getCode(), ErrorEnum.EQUALS_OLD_PASSWORD.getMessage());
        }
        sysUser.setPassword(SmUtil.sm3(passwordProperties.getSalt() + updatePasswordVO.getNewPassword()));
        super.updateById(sysUser);
        redisService.deleteKey(SysUser.REDIS_KEY + updatePasswordVO.getId());
        deleteToken(Collections.singletonList(updatePasswordVO.getId()));
        return true;
    }


    /**
     * 重置密码
     *
     * @param id id
     * @return true/false
     */
    @Override
    public Boolean resetPassword(Long id) {
        SysUser sysUser = getUser(id);
        sysUser.setPassword(SmUtil.sm3(passwordProperties.getSalt() + passwordProperties.getPassword()));
        super.updateById(sysUser);
        redisService.deleteKey(SysUser.REDIS_KEY + id);
        deleteToken(Collections.singletonList(id));
        return true;
    }


    /**
     * 修改头像
     *
     * @param updateAvatarVO 修改头像信息
     * @return true/false
     */
    @Override
    public Boolean updateAvatar(UpdateAvatarVO updateAvatarVO) {
        SysUser sysUser = getUser(updateAvatarVO.getId());
        sysUser.setAvatar(updateAvatarVO.getAvatar());
        super.updateById(sysUser);
        redisService.deleteKey(SysUser.REDIS_KEY + updateAvatarVO.getId());
        return true;
    }


    /**
     * 最近新增用户统计
     *
     * @param days 天数
     * @return 数据信息
     */
    @Override
    public List<UserStatisticsVO> statistics(Integer days) {
        days = days < CommonConstant.ONE ? CommonConstant.ZERO : days;
        LocalDate startDate = LocalDate.now().minusDays(days - CommonConstant.ONE);
        Map<LocalDate, Integer> dateCounts = new LinkedHashMap<>();
        for (LocalDate date = startDate; !date.isAfter(LocalDate.now()); date = date.plusDays(CommonConstant.ONE)) {
            dateCounts.put(date, CommonConstant.ZERO);
        }
        List<SysUser> sysUserList = super.list(Wrappers.<SysUser>lambdaQuery().ge(SysUser::getCreateTime, DateUtil.getDayZeroTime(days)));
        if (CollUtil.isNotEmpty(sysUserList)) {
            for (SysUser sysUser : sysUserList) {
                LocalDate createTime = sysUser.getCreateTime().toLocalDate();
                if (dateCounts.containsKey(createTime)) {
                    dateCounts.merge(createTime, CommonConstant.ONE, Integer::sum);
                }
            }
        }
        return dateCounts.entrySet().stream().map(entry -> {
            UserStatisticsVO vo = new UserStatisticsVO();
            vo.setCreateTime(entry.getKey());
            vo.setNewUserCount(entry.getValue());
            return vo;
        }).toList();
    }


}
