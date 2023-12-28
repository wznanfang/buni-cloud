package com.buni.userservice.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buni.usercommon.entity.RoleAuthority;
import com.buni.usercommon.vo.role.RoleAuthorityDTO;
import com.buni.userservice.mapper.RoleAuthorityMapper;
import com.buni.userservice.service.RoleAuthorityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @description 针对表【roleAuthority(角色权限)】的数据库操作Service实现
 * @createDate 2023-09-25 13:45:26
 */
@Slf4j
@Service
@AllArgsConstructor
public class RoleAuthorityServiceImpl extends ServiceImpl<RoleAuthorityMapper, RoleAuthority> implements RoleAuthorityService {


    /**
     * 查询roleAuthority
     *
     * @param roleIds 角色id集合
     * @return {@link List}<{@link RoleAuthorityDTO}>
     */
    @Override
    public List<RoleAuthorityDTO> findByRoleIds(List<Long> roleIds) {
        List<RoleAuthority> roleAuthorityList = super.list(Wrappers.<RoleAuthority>lambdaQuery().in(RoleAuthority::getRoleId, roleIds));
        return getRoleAuthorityDtoS(roleAuthorityList);
    }


    private static List<RoleAuthorityDTO> getRoleAuthorityDtoS(List<RoleAuthority> roleAuthorityList) {
        List<RoleAuthorityDTO> roleAuthorityDtoS = new ArrayList<>();
        if (CollUtil.isNotEmpty(roleAuthorityList)) {
            roleAuthorityList.forEach(roleAuthority -> {
                RoleAuthorityDTO roleAuthorityDto = new RoleAuthorityDTO();
                BeanUtil.copyProperties(roleAuthority, roleAuthorityDto);
                roleAuthorityDtoS.add(roleAuthorityDto);
            });
        }
        return roleAuthorityDtoS;
    }


    /**
     * 查询roleAuthority
     *
     * @param authorityId 权限id
     * @return {@link List}<{@link RoleAuthorityDTO}>
     */
    @Override
    public List<RoleAuthorityDTO> findByAuthorityId(Long authorityId) {
        List<RoleAuthority> roleAuthList = super.list(Wrappers.<RoleAuthority>lambdaQuery().eq(RoleAuthority::getAuthorityId, authorityId));
        return getRoleAuthorityDtoS(roleAuthList);
    }


    /**
     * 根据角色id删除角色权限
     *
     * @param roleIds 角色id集合
     */
    @Override
    public void deleteByRoleIds(List<Long> roleIds) {
        super.remove(Wrappers.<RoleAuthority>lambdaQuery().in(RoleAuthority::getRoleId, roleIds));
    }


}