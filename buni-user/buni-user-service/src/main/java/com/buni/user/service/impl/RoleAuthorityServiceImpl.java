package com.buni.user.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buni.user.dto.role.RoleAuthorityDTO;
import com.buni.user.entity.RoleAuthority;
import com.buni.user.mapper.RoleAuthorityMapper;
import com.buni.user.service.RoleAuthorityService;
import com.buni.user.vo.roleauthority.AddVO;
import com.buni.user.vo.roleauthority.UpdateVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
     * 保存角色权限
     *
     * @param addVO 新增数据Vo
     * @return
     */
    @Override
    public boolean save(AddVO addVO) {
        if (CollUtil.isNotEmpty(addVO.getAuthorityIds())) {
            saveRoleAuthority(addVO.getRoleId(), addVO.getAuthorityIds());
        }
        return true;
    }

    private void saveRoleAuthority(Long roleId, List<Long> authorityIds) {
        List<RoleAuthority> list = new ArrayList<>();
        authorityIds.forEach(authorityId -> {
            RoleAuthority roleAuthority = new RoleAuthority();
            roleAuthority.setAuthorityId(authorityId);
            roleAuthority.setRoleId(roleId);
            list.add(roleAuthority);
        });
        super.saveBatch(list);
    }


    /**
     * 更新角色权限
     *
     * @param updateVO 修改Vo
     * @return
     */
    @Override
    public boolean update(UpdateVO updateVO) {
        super.remove(Wrappers.<RoleAuthority>lambdaQuery().eq(RoleAuthority::getRoleId, updateVO.getRoleId()));
        if (CollUtil.isNotEmpty(updateVO.getAuthorityIds())) {
            saveRoleAuthority(updateVO.getRoleId(), updateVO.getAuthorityIds());
        }
        return true;
    }


    /**
     * 根据id查询角色权限
     *
     * @param id 角色id
     * @return
     */
    @Override
    public List<Long> findById(Long id) {
        List<RoleAuthority> authorityGetVoS = super.list(Wrappers.<RoleAuthority>lambdaQuery().eq(RoleAuthority::getRoleId, id));
        List<Long> authorityIds = new ArrayList<>();
        if (CollUtil.isNotEmpty(authorityGetVoS)) {
            authorityIds = authorityGetVoS.stream().map(RoleAuthority::getAuthorityId).toList();
        }
        return authorityIds;
    }


    /**
     * 查询roleAuthority
     *
     * @param roleIds 角色id集合
     * @return
     */
    @Override
    public List<RoleAuthorityDTO> findByRoleIds(List<Long> roleIds) {
        List<RoleAuthority> roleAuthorityList = super.list(Wrappers.<RoleAuthority>lambdaQuery().in(RoleAuthority::getRoleId, roleIds));
        return getRoleAuthorityDtoS(roleAuthorityList);
    }


    private static List<RoleAuthorityDTO> getRoleAuthorityDtoS(List<RoleAuthority> roleAuthorityList) {
        return Optional.ofNullable(roleAuthorityList).orElse(new ArrayList<>()).stream().map(roleAuthority -> {
            RoleAuthorityDTO roleAuthorityDto = new RoleAuthorityDTO();
            BeanUtil.copyProperties(roleAuthority, roleAuthorityDto);
            return roleAuthorityDto;
        }).toList();
    }


    /**
     * 查询roleAuthority
     *
     * @param authorityId 权限id
     * @return
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