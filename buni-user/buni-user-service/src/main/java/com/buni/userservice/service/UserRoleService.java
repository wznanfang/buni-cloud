package com.buni.userservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.buni.usercommon.entity.UserRole;
import com.buni.usercommon.vo.role.UserRoleDTO;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【user_role(用户角色)】的数据库操作Service
 * @createDate 2023-09-25 13:45:26
 */
public interface UserRoleService extends IService<UserRole> {


    /**
     * 查询用户角色列表
     *
     * @param userId
     * @return {@link List}<{@link UserRoleDTO}>
     */
    List<UserRoleDTO> findByUserId(Long userId);

    /**
     * 查询用户角色列表
     *
     * @param roleIds 角色id集合
     * @return {@link List}<{@link UserRoleDTO}>
     */
    List<UserRoleDTO> findByRoleIds(List<Long> roleIds);


    /**
     * 根据用户id删除用户角色
     *
     * @param userId 用户id
     * @return {@link List}<{@link Long}>
     */
    List<Long> deleteByUserId(Long userId);

}