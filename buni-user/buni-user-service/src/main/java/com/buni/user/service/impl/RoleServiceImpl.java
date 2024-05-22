package com.buni.user.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buni.framework.config.exception.CustomException;
import com.buni.framework.config.redis.RedisService;
import com.buni.user.entity.Authority;
import com.buni.user.entity.Role;
import com.buni.user.enums.ErrorEnum;
import com.buni.user.service.RoleAuthorityService;
import com.buni.user.vo.IdVOs;
import com.buni.user.vo.role.UserRoleDTO;
import com.buni.user.mapper.RoleMapper;
import com.buni.user.service.RoleService;
import com.buni.user.service.UserRoleService;
import com.buni.user.vo.role.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @description 针对表【role(角色)】的数据库操作Service实现
 * @createDate 2023-09-25 13:45:26
 */
@Slf4j
@Service
@AllArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    private RedisService redisService;
    private UserRoleService userRoleService;
    private RoleAuthorityService roleAuthorityService;


    /**
     * 新增角色
     *
     * @param addVO 角色信息
     * @return true/false
     */
    @Override
    public boolean save(AddVO addVO) {
        Role role = super.getOne(Wrappers.<Role>lambdaQuery().eq(Role::getName, addVO.getName()));
        if (ObjUtil.isNotEmpty(role)) {
            throw new CustomException(ErrorEnum.ROLE_EXISTS.getCode(), ErrorEnum.ROLE_EXISTS.getMessage());
        }
        Role addRole = new Role();
        BeanUtils.copyProperties(addVO, addRole);
        super.save(addRole);
        return true;
    }


    /**
     * 根据ID修改角色
     *
     * @param updateVO 角色数据
     * @return true/false
     */
    @Override
    public boolean update(UpdateVO updateVO) {
        Role role = super.getOne(Wrappers.<Role>lambdaQuery().eq(Role::getName, updateVO.getName()).ne(Role::getId, updateVO.getId()));
        if (ObjUtil.isNotEmpty(role)) {
            throw new CustomException(ErrorEnum.ROLE_EXISTS.getCode(), ErrorEnum.ROLE_EXISTS.getMessage());
        }
        Role updateRole = new Role();
        BeanUtils.copyProperties(updateVO, updateRole);
        super.updateById(updateRole);
        redisService.deleteKey(Role.REDIS_KEY + updateVO.getId());
        return true;
    }


    /**
     * 根据ID删除角色
     *
     * @param id 角色id
     * @return true/false
     */
    @Override
    public boolean delete(Long id) {
        Role role = getRole(id);
        super.removeById(role.getId());
        // 剔除拥有该角色的用户缓存
        List<Long> roleIds = new ArrayList<>();
        roleIds.add(role.getId());
        List<UserRoleDTO> userRoleDtoS = userRoleService.findByRoleIds(roleIds);
        if (CollUtil.isNotEmpty(userRoleDtoS)) {
            List<String> authorityKeys = new ArrayList<>();
            userRoleDtoS.forEach(userRole -> authorityKeys.add(Authority.REDIS_KEY + userRole.getUserId()));
            redisService.delAllByKeys(authorityKeys);
        }
        //删除角色权限关联
        roleAuthorityService.deleteByRoleIds(roleIds);
        redisService.deleteKey(Role.REDIS_KEY + role.getId());
        return true;
    }


    /**
     * 根据ID集合批量删除角色
     *
     * @param idVOs id集合
     * @return true/false
     */
    @Override
    public boolean batchDelete(IdVOs idVOs) {
        List<Long> ids = idVOs.getIds();
        super.removeBatchByIds(ids);
        //更新角色权限表，剔除拥有该权限的用户缓存
        List<UserRoleDTO> userRoleDtoS = userRoleService.findByRoleIds(ids);
        if (CollUtil.isNotEmpty(userRoleDtoS)) {
            List<String> authorityKeys = new ArrayList<>();
            userRoleDtoS.forEach(userRole -> authorityKeys.add(Authority.REDIS_KEY + userRole.getUserId()));
            redisService.delAllByKeys(authorityKeys);
        }
        //删除角色权限关联
        roleAuthorityService.deleteByRoleIds(ids);
        List<String> roleKeys = new ArrayList<>();
        ids.forEach(id -> roleKeys.add(Role.REDIS_KEY + id));
        redisService.delAllByKeys(roleKeys);
        return true;
    }


    private Role getRole(Long id) {
        Role role = super.getById(id);
        if (ObjUtil.isEmpty(role)) {
            throw new CustomException(ErrorEnum.ROLE_NOT_EXISTS.getCode(), ErrorEnum.ROLE_NOT_EXISTS.getMessage());
        }
        return role;
    }


    /**
     * 根据ID查询角色
     *
     * @param id 角色id
     * @return {@link RoleInfoVO}
     */
    @Override
    public RoleInfoVO findById(Long id) {
        RoleInfoVO roleInfoVO = (RoleInfoVO) redisService.get(Role.REDIS_KEY + id);
        if (ObjUtil.isEmpty(roleInfoVO)) {
            Role role = getRole(id);
            roleInfoVO = new RoleInfoVO();
            BeanUtils.copyProperties(role, roleInfoVO);
            redisService.setOneHour(Role.REDIS_KEY + role.getId(), roleInfoVO);
        }
        return roleInfoVO;
    }


    /**
     * 分页查询角色信息
     *
     * @param pageVO 分页条件
     * @return IPage<UserInfoVO>
     */
    @Override
    public IPage<RoleGetVO> findPage(PageVO pageVO) {
        IPage<Role> ipage = new Page<>(pageVO.getCurrent(), pageVO.getSize());
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(ObjectUtil.isNotEmpty(pageVO.getName()), Role::getName, pageVO.getName());
        IPage<Role> infoPage = super.page(ipage, queryWrapper);
        IPage<RoleGetVO> resultPage = new Page<>(infoPage.getCurrent(), infoPage.getSize(), infoPage.getTotal());
        List<RoleGetVO> list = new ArrayList<>();
        if (CollUtil.isNotEmpty(infoPage.getRecords())) {
            infoPage.getRecords().forEach(role -> {
                RoleGetVO getVO = new RoleGetVO();
                BeanUtils.copyProperties(role, getVO);
                list.add(getVO);
            });
        }
        resultPage.setRecords(list);
        return resultPage;
    }


}