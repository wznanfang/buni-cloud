package com.buni.userservice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.buni.usercommon.entity.User;
import com.buni.usercommon.vo.LoginVO;
import com.buni.usercommon.vo.UserLoginVO;
import com.buni.userservice.vo.user.AddVO;
import com.buni.userservice.vo.user.PageVO;
import com.buni.userservice.vo.user.UserInfoVO;

/**
 * @author Administrator
 * @description 针对表【user】的数据库操作Service
 * @createDate 2023-09-19 10:52:51
 */
public interface UserService extends IService<User> {


    /**
     * 登录
     *
     * @param loginVO
     * @return
     */
    UserLoginVO login(LoginVO loginVO);

    /**
     * 退出登录
     *
     * @return
     */
    Boolean loginOut();

    /**
     * 新增
     *
     * @param addVO
     * @return true/false
     */
    boolean save(AddVO addVO);

    /**
     * 根据id查询用户基本信息
     *
     * @param id
     * @return
     */
    UserInfoVO findById(Long id);

    /**
     * 分页查询用户信息
     *
     * @param pageVO
     * @return
     */
    IPage<UserInfoVO> findPage(PageVO pageVO);


}