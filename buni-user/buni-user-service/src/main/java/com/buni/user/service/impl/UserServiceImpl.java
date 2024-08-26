package com.buni.user.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SmUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buni.framework.config.exception.CustomException;
import com.buni.framework.config.redis.RedisService;
import com.buni.framework.constant.CommonConstant;
import com.buni.framework.util.DateUtil;
import com.buni.framework.util.EncryptUtil;
import com.buni.user.entity.User;
import com.buni.user.enums.BooleanEnum;
import com.buni.user.enums.ErrorEnum;
import com.buni.user.mapper.UserMapper;
import com.buni.user.properties.UserProperties;
import com.buni.user.service.AuthService;
import com.buni.user.service.UserRoleService;
import com.buni.user.service.UserService;
import com.buni.user.vo.IdVOs;
import com.buni.user.vo.user.*;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserProperties userProperties;
    @Resource
    private RedisService redisService;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private AuthService authService;


    /**
     * 新增
     *
     * @param addVO 新增信息
     * @return true/false
     */
    @Override
    public boolean save(AddVO addVO) {
        if (!Validator.isMobile(addVO.getTel())) {
            throw new CustomException(ErrorEnum.PHONE_ERROR.getCode(), ErrorEnum.PHONE_ERROR.getMessage());
        }
        User existUser = super.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, addVO.getUsername()).or().eq(User::getTel, addVO.getTel()));
        if (ObjUtil.isNotEmpty(existUser)) {
            throw new CustomException(ErrorEnum.USER_EXISTS.getCode(), ErrorEnum.USER_EXISTS.getMessage());
        }
        User user = new User();
        BeanUtils.copyProperties(addVO, user);
        String password = EncryptUtil.decrypt(addVO.getPassword());
        user.setPassword(SmUtil.sm3(userProperties.getSalt() + password));
        return super.save(user);
    }


    private User getUser(Long id) {
        User user = super.getById(id);
        if (ObjUtil.isEmpty(user)) {
            throw new CustomException(ErrorEnum.USER_NOT_EXISTS.getCode(), ErrorEnum.USER_NOT_EXISTS.getMessage());
        }
        return user;
    }


    /**
     * 编辑用户基本信息
     *
     * @param updateVO 用户信息
     * @return true/false
     */
    @Override
    public boolean update(UpdateVO updateVO) {
        User user = getUser(updateVO.getId());
        User existUser = super.getOne(Wrappers.<User>lambdaQuery().eq(User::getTel, updateVO.getTel()));
        if (ObjUtil.isNotEmpty(existUser) && !existUser.getId().equals(user.getId())) {
            throw new CustomException(ErrorEnum.USER_EXISTS.getCode(), ErrorEnum.USER_EXISTS.getMessage());
        }
        User updateUser = new User();
        BeanUtils.copyProperties(updateVO, updateUser);
        super.updateById(updateUser);
        redisService.deleteKey(User.REDIS_KEY + updateVO.getId());
        return true;
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
        User forbiddenUser = new User();
        BeanUtils.copyProperties(enableVO, forbiddenUser);
        super.updateById(forbiddenUser);
        redisService.deleteKey(User.REDIS_KEY + enableVO.getId());
        if (BooleanEnum.NO.equals(enableVO.getEnable())) {
            deleteToken(Collections.singletonList(enableVO.getId()));
        }
        return true;
    }

    private void deleteToken(List<Long> userIds) {
        List<String> tokens = authService.findByUserId(userIds);
        if (ObjUtil.isNotEmpty(tokens)) {
            List<String> tokenKeys = new ArrayList<>();
            tokens.forEach(token -> {
                tokenKeys.add(CommonConstant.TOKEN_REDIS_KEY + token);
            });
            redisService.delAllByKeys(tokenKeys);
        }
    }


    /**
     * 批量启用/禁用用户
     *
     * @param batchEnableVO
     * @return
     */
    @Override
    public boolean batchEnable(BatchEnableVO batchEnableVO) {
        if (ObjUtil.isEmpty(batchEnableVO.getIdVOs().getIds())) {
            return true;
        }
        List<Long> userIds = batchEnableVO.getIdVOs().getIds();
        super.update(Wrappers.<User>lambdaUpdate().in(User::getId, userIds).set(User::getEnable, batchEnableVO.getEnable()));
        List<String> deleteKeys = userIds.stream().map(id -> User.REDIS_KEY + id).toList();
        redisService.delAllByKeys(deleteKeys);
        if (BooleanEnum.NO.equals(batchEnableVO.getEnable())) {
            deleteToken(batchEnableVO.getIdVOs().getIds());
        }
        return true;
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
        userRoleService.deleteByUserId(id);
        redisService.deleteKey(User.REDIS_KEY + id);
        deleteToken(Collections.singletonList(id));
        return true;
    }


    /**
     * 批量删除
     *
     * @param idVOs 用户id集合
     * @return
     */
    @Override
    public Boolean batchDelete(IdVOs idVOs) {
        List<Long> ids = idVOs.getIds();
        super.removeByIds(ids);
        userRoleService.deleteByUserIds(ids);
        List<String> deleteKeys = ids.stream().map(id -> User.REDIS_KEY + id).toList();
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
        UserInfoVO userInfoVO = (UserInfoVO) redisService.get(User.REDIS_KEY + id);
        if (ObjUtil.isEmpty(userInfoVO)) {
            User user = getUser(id);
            userInfoVO = new UserInfoVO();
            BeanUtils.copyProperties(user, userInfoVO);
            redisService.setOneDay(User.REDIS_KEY + user.getId(), userInfoVO);
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
        IPage<User> ipage = new Page<>(pageVO.getCurrent(), pageVO.getSize());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().ne(User::getId, CommonConstant.ADMIN_ID);
        queryWrapper.lambda().like(ObjectUtil.isNotEmpty(pageVO.getUsername()), User::getUsername, pageVO.getUsername());
        queryWrapper.lambda().like(ObjectUtil.isNotEmpty(pageVO.getName()), User::getName, pageVO.getName());
        IPage<User> infoPage = super.page(ipage, queryWrapper);
        IPage<UserGetVO> resultPage = new Page<>(infoPage.getCurrent(), infoPage.getSize(), infoPage.getTotal());
        List<UserGetVO> list = Optional.ofNullable(infoPage.getRecords()).orElse(new ArrayList<>()).stream().map(user -> {
            UserGetVO getVO = new UserGetVO();
            BeanUtils.copyProperties(user, getVO);
            getVO.setTel(DesensitizedUtil.mobilePhone(getVO.getTel()));
            return getVO;
        }).toList();
        resultPage.setRecords(list);
        return resultPage;
    }


    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    @Override
    public User findByUsername(String username) {
        return super.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username).eq(User::getDeleted, BooleanEnum.NO));
    }


    /**
     * 修改密码
     *
     * @param updatePasswordVO
     * @return
     */
    @Override
    public Boolean updatePassword(UpdatePasswordVO updatePasswordVO) {
        User user = getUser(updatePasswordVO.getId());
        if (!SmUtil.sm3(userProperties.getSalt() + updatePasswordVO.getOldPassword()).equals(user.getPassword())) {
            throw new CustomException(ErrorEnum.OLD_PASSWORD_ERROR.getCode(), ErrorEnum.OLD_PASSWORD_ERROR.getMessage());
        }
        if (SmUtil.sm3(userProperties.getSalt() + updatePasswordVO.getNewPassword()).equals(user.getPassword())) {
            throw new CustomException(ErrorEnum.EQUALS_OLD_PASSWORD.getCode(), ErrorEnum.EQUALS_OLD_PASSWORD.getMessage());
        }
        user.setPassword(SmUtil.sm3(userProperties.getSalt() + updatePasswordVO.getNewPassword()));
        super.updateById(user);
        redisService.deleteKey(User.REDIS_KEY + updatePasswordVO.getId());
        deleteToken(Collections.singletonList(updatePasswordVO.getId()));
        return true;
    }


    /**
     * 重置密码
     *
     * @param id
     * @return
     */
    @Override
    public Boolean resetPassword(Long id) {
        User user = getUser(id);
        user.setPassword(SmUtil.sm3(userProperties.getSalt() + userProperties.getPassword()));
        super.updateById(user);
        redisService.deleteKey(User.REDIS_KEY + id);
        deleteToken(Collections.singletonList(id));
        return true;
    }


    /**
     * 修改头像
     *
     * @param updateAvatarVO
     * @return
     */
    @Override
    public Boolean updateAvatar(UpdateAvatarVO updateAvatarVO) {
        User user = getUser(updateAvatarVO.getId());
        user.setAvatar(updateAvatarVO.getAvatar());
        super.updateById(user);
        return true;
    }


    /**
     * 最近新增用户统计
     *
     * @param days
     * @return
     */
    @Override
    public List<UserStatisticsVO> statistics(Integer days) {
        days = days < CommonConstant.ONE ? CommonConstant.ZERO : days;
        LocalDate startDate = LocalDate.now().minusDays(days - CommonConstant.ONE);
        Map<LocalDate, Integer> dateCounts = new LinkedHashMap<>();
        for (LocalDate date = startDate; !date.isAfter(LocalDate.now()); date = date.plusDays(CommonConstant.ONE)) {
            dateCounts.put(date, CommonConstant.ZERO);
        }
        List<User> userList = super.list(Wrappers.<User>lambdaQuery().ge(User::getCreateTime, DateUtil.getDayZeroTime(days)));
        if (CollUtil.isNotEmpty(userList)) {
            for (User user : userList) {
                LocalDate createTime = user.getCreateTime().toLocalDate();
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