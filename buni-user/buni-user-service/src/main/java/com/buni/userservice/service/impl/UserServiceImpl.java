package com.buni.userservice.service.impl;

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
import com.buni.buniframework.config.exception.CustomException;
import com.buni.buniframework.config.redis.RedisService;
import com.buni.usercommon.entity.User;
import com.buni.usercommon.enums.BooleanEnum;
import com.buni.usercommon.enums.ErrorEnum;
import com.buni.userservice.constant.UserConstant;
import com.buni.userservice.mapper.UserMapper;
import com.buni.userservice.service.RoleAuthorityService;
import com.buni.userservice.service.UserRoleService;
import com.buni.userservice.service.UserService;
import com.buni.userservice.vo.user.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2023-09-19 10:52:51
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private UserConstant userConstant;
    private RedisService redisService;
    private UserRoleService userRoleService;
    private RoleAuthorityService roleAuthorityService;


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
        long count = super.count(Wrappers.<User>lambdaQuery().eq(User::getUsername, addVO.getUsername()).or().eq(User::getTel, addVO.getTel()).last("LIMIT 1"));
        if (count > 0) {
            throw new CustomException(ErrorEnum.USER_EXISTS.getCode(), ErrorEnum.USER_EXISTS.getMessage());
        }
        User user = new User();
        BeanUtils.copyProperties(addVO, user);
        user.setPassword(SmUtil.sm3(userConstant.getSalt() + addVO.getPassword()));
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
        getUser(updateVO.getId());
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
        User deleteUser = new User();
        deleteUser.setId(id);
        deleteUser.setDeleted(BooleanEnum.YES);
        super.updateById(deleteUser);
        List<Long> roleIds = userRoleService.deleteByUserId(id);
        roleAuthorityService.deleteByRoleIds(roleIds);
        redisService.deleteKey(User.REDIS_KEY + id);
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
            userInfoVO.setTel(DesensitizedUtil.mobilePhone(userInfoVO.getTel()));
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
        queryWrapper.lambda().eq(User::getDeleted, BooleanEnum.NO);
        queryWrapper.lambda().like(ObjectUtil.isNotEmpty(pageVO.getUsername()), User::getUsername, pageVO.getUsername());
        queryWrapper.lambda().like(ObjectUtil.isNotEmpty(pageVO.getName()), User::getName, pageVO.getName());
        IPage<User> infoPage = super.page(ipage, queryWrapper);
        IPage<UserGetVO> resultPage = new Page<>(infoPage.getCurrent(), infoPage.getSize(), infoPage.getTotal());
        List<UserGetVO> list = new ArrayList<>();
        if (CollUtil.isNotEmpty(infoPage.getRecords())) {
            infoPage.getRecords().forEach(user -> {
                UserGetVO getVO = new UserGetVO();
                BeanUtils.copyProperties(user, getVO);
                list.add(getVO);
            });
        }
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


}