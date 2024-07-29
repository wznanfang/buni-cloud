package com.buni.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.buni.user.entity.UserRole;
import com.buni.user.dto.role.UserRoleDTO;
import com.buni.user.vo.userrole.AddVO;
import com.buni.user.vo.userrole.UpdateVO;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【user_role(用户角色)】的数据库操作Service
 * @createDate 2023-09-25 13:45:26
 */
public interface UserRoleService extends IService<UserRole> {


    /**
     * 保存
     *
     * @param addVO
     * @return
     */
    boolean save(AddVO addVO);

    /**
     * 修改
     *
     * @param updateVO
     * @return
     */
    boolean update(UpdateVO updateVO);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    List<Long> findById(Long id);

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
     */
    void deleteByUserId(Long userId);

    /**
     * 根据用户id删除用户角色
     *
     * @param userIds 用户id集合
     */
    void deleteByUserIds(List<Long> userIds);

}