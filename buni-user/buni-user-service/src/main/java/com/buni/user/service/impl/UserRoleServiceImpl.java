package com.buni.user.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buni.user.entity.UserRole;
import com.buni.user.vo.role.UserRoleDTO;
import com.buni.user.mapper.UserRoleMapper;
import com.buni.user.service.UserRoleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @description 针对表【userRole(用户角色)】的数据库操作Service实现
 * @createDate 2023-09-25 13:45:26
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {


    /**
     * 查询用户角色列表
     *
     * @param userId 用户id
     * @return {@link List}<{@link UserRoleDTO}>
     */
    @Override
    public List<UserRoleDTO> findByUserId(Long userId) {
        List<UserRole> list = super.list(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getUserId, userId));
        return getUserRoleDtoS(list);
    }


    private static List<UserRoleDTO> getUserRoleDtoS(List<UserRole> list) {
        List<UserRoleDTO> userRoleDtoS = new ArrayList<>();
        if (CollUtil.isNotEmpty(list)) {
            list.forEach(userRole -> {
                UserRoleDTO userRoleDTO = new UserRoleDTO();
                BeanUtil.copyProperties(userRole, userRoleDTO);
                userRoleDtoS.add(userRoleDTO);
            });
        }
        return userRoleDtoS;
    }


    /**
     * 查询用户角色列表
     *
     * @param roleIds 角色id集合
     * @return {@link List}<{@link UserRoleDTO}>
     */
    @Override
    public List<UserRoleDTO> findByRoleIds(List<Long> roleIds) {
        List<UserRole> list = super.list(Wrappers.<UserRole>lambdaQuery().in(UserRole::getRoleId, roleIds));
        return getUserRoleDtoS(list);
    }


    /**
     * 根据用户id删除用户角色
     *
     * @param userId 用户id
     * @return {@link List}<{@link Long}>
     */
    @Override
    public List<Long> deleteByUserId(Long userId) {
        List<Long> roleIds = new ArrayList<>();
        List<UserRole> list = super.list(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getUserId, userId));
        if (CollUtil.isEmpty(list)) {
            return roleIds;
        }
        roleIds = list.stream().map(UserRole::getId).toList();
        super.removeBatchByIds(roleIds);
        return roleIds;
    }


}