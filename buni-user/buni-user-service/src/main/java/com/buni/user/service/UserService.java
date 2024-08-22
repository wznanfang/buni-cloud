package com.buni.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.buni.user.entity.User;
import com.buni.user.vo.IdVOs;
import com.buni.user.vo.user.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

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
     * 批量启用-禁用用户
     *
     * @param batchEnableVO
     * @return
     */
    boolean batchEnable(BatchEnableVO batchEnableVO);

    /**
     * 根据id删除用户
     *
     * @param id 用户id
     * @return true/false
     */
    boolean delete(Long id);

    /**
     * 批量删除用户
     *
     * @param idVOs 用户id集合
     * @return true/false
     */
    Boolean batchDelete(IdVOs idVOs);

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

    /**
     * 修改密码
     *
     * @param updatePasswordVO
     * @return
     */
    Boolean updatePassword(UpdatePasswordVO updatePasswordVO);

    /**
     * 最近新增用户统计
     *
     * @return
     */
    List<UserStatisticsVO> statistics(Integer days);


}