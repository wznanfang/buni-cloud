package com.buni.userservice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.buni.usercommon.entity.User;
import com.buni.userservice.vo.user.*;

/**
 * @author Administrator
 * @description 针对表【user】的数据库操作Service
 * @createDate 2023-09-19 10:52:51
 */
public interface UserService extends IService<User> {

    /**
     * 新增
     *
     * @param addVO 用户信息
     * @return true/false
     */
    boolean save(AddVO addVO);

    /**
     * 编辑用户基本信息
     *
     * @param updateVO 用户信息
     * @return true/false
     */
    boolean update(UpdateVO updateVO);

    /**
     * 根据id 禁用用户
     *
     * @param enableVO 启用/禁用信息
     * @return true/false
     */
    boolean enable(EnableVO enableVO);

    /**
     * 根据id删除用户
     *
     * @param id 用户id
     * @return true/false
     */
    boolean delete(Long id);

    /**
     * 根据id查询用户基本信息
     *
     * @param id 用户id
     * @return 用户信息
     */
    UserInfoVO findById(Long id);

    /**
     * 分页查询用户信息
     *
     * @param pageVO 分页条件
     * @return IPage<UserInfoVO>
     */
    IPage<UserGetVO> findPage(PageVO pageVO);

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    User findByUsername(String username);


}