package com.buni.user.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buni.user.dto.role.UserRoleDTO;
import com.buni.user.entity.SysUserRole;
import com.buni.user.mapper.UserRoleMapper;
import com.buni.user.service.UserRoleService;
import com.buni.user.vo.userrole.AddVO;
import com.buni.user.vo.userrole.UpdateVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Administrator
 * @description 针对表【userRole(用户角色)】的数据库操作Service实现
 * @createDate 2023-09-25 13:45:26
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, SysUserRole> implements UserRoleService {


    /**
     * 新增用户角色
     *
     * @param addVO 添加VO
     * @return boolean
     */
    @Override
    public boolean save(AddVO addVO) {
        if (CollUtil.isNotEmpty(addVO.getRoleIds())) {
            saveUserRole(addVO.getUserId(), addVO.getRoleIds());
        }
        return true;
    }

    private void saveUserRole(Long userId, List<Long> roleIds) {
        List<SysUserRole> sysUserRoleList = roleIds.stream().map(roleId -> {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(roleId);
            sysUserRole.setUserId(userId);
            return sysUserRole;
        }).toList();
        super.saveBatch(sysUserRoleList);
    }


    /**
     * 更新用户角色
     *
     * @param updateVO 更新VO
     * @return boolean
     */
    @Override
    public boolean update(UpdateVO updateVO) {
        super.remove(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId, updateVO.getUserId()));
        if (CollUtil.isNotEmpty(updateVO.getRoleIds())) {
            saveUserRole(updateVO.getUserId(), updateVO.getRoleIds());
        }
        return true;
    }


    /**
     * 根据用户id查询角色id集合
     *
     * @param id 用户id
     * @return {@link List}<{@link Long}>
     */
    @Override
    public List<Long> findById(Long id) {
        List<SysUserRole> list = super.list(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId, id));
        return Optional.ofNullable(list).orElse(new ArrayList<>()).stream().map(SysUserRole::getRoleId).toList();
    }


    /**
     * 查询用户角色列表
     *
     * @param userId 用户id
     * @return {@link List}<{@link UserRoleDTO}>
     */
    @Override
    public List<UserRoleDTO> findByUserId(Long userId) {
        List<SysUserRole> list = super.list(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId, userId));
        return getUserRoleDtoS(list);
    }


    private static List<UserRoleDTO> getUserRoleDtoS(List<SysUserRole> list) {
        return Optional.ofNullable(list).orElse(new ArrayList<>()).stream().map(sysUserRole -> {
            UserRoleDTO userRoleDTO = new UserRoleDTO();
            BeanUtil.copyProperties(sysUserRole, userRoleDTO);
            return userRoleDTO;
        }).toList();
    }


    /**
     * 查询用户角色列表
     *
     * @param roleIds 角色id集合
     * @return {@link List}<{@link UserRoleDTO}>
     */
    @Override
    public List<UserRoleDTO> findByRoleIds(List<Long> roleIds) {
        List<SysUserRole> list = super.list(Wrappers.<SysUserRole>lambdaQuery().in(SysUserRole::getRoleId, roleIds));
        return getUserRoleDtoS(list);
    }


    /**
     * 根据用户id删除用户角色关联
     *
     * @param userId 用户id
     */
    @Override
    public void deleteByUserId(Long userId) {
        super.remove(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId, userId));
    }


    /**
     * 根据用户id集合删除用户角色关联
     *
     * @param userIds 用户id集合
     */
    @Override
    public void deleteByUserIds(List<Long> userIds) {
        super.remove(Wrappers.<SysUserRole>lambdaQuery().in(SysUserRole::getUserId, userIds));
    }


}
