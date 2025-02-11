package com.buni.user.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buni.framework.config.exception.CustomException;
import com.buni.framework.config.redis.RedisService;
import com.buni.framework.constant.CommonConstant;
import com.buni.user.dto.role.AuthorityDTO;
import com.buni.user.dto.role.RoleAuthorityDTO;
import com.buni.user.dto.role.UserRoleDTO;
import com.buni.user.entity.SysAuthority;
import com.buni.user.enums.ErrorEnum;
import com.buni.user.mapper.SysAuthorityMapper;
import com.buni.user.service.SysAuthorityService;
import com.buni.user.service.SysRoleAuthorityService;
import com.buni.user.service.SysUserRoleService;
import com.buni.user.vo.IdVOs;
import com.buni.user.vo.authority.*;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Administrator
 * @description 针对表【authority(角色)】的数据库操作Service实现
 * @createDate 2023-09-25 13:45:26
 */
@Slf4j
@Service
@AllArgsConstructor
public class SysAuthorityServiceImpl extends ServiceImpl<SysAuthorityMapper, SysAuthority> implements SysAuthorityService {

    @Resource
    private RedisService redisService;
    @Resource
    private SysRoleAuthorityService sysRoleAuthorityService;
    @Resource
    private SysUserRoleService sysUserRoleService;


    /**
     * 新增权限
     *
     * @param addVO 权限信息
     * @return true/false
     */
    @Override
    public boolean save(AddVO addVO) {
        SysAuthority sysAuthority = super.getOne(Wrappers.<SysAuthority>lambdaQuery().eq(SysAuthority::getCode, addVO.getCode()).or().eq(SysAuthority::getUrl, addVO.getUrl()));
        if (ObjUtil.isNotEmpty(sysAuthority)) {
            throw new CustomException(ErrorEnum.AUTHORITY_EXISTS.getCode(), ErrorEnum.AUTHORITY_EXISTS.getMessage());
        }
        SysAuthority addSysAuthority = new SysAuthority();
        BeanUtils.copyProperties(addVO, addSysAuthority);
        super.save(addSysAuthority);
        return true;
    }


    /**
     * 根据ID修改权限
     *
     * @param updateVO 权限数据
     * @return true/false
     */
    @Override
    public boolean update(UpdateVO updateVO) {
        SysAuthority sysAuthority = super.getOne(Wrappers.<SysAuthority>lambdaQuery().ne(SysAuthority::getId, updateVO.getId()).ne(SysAuthority::getParentId, updateVO.getParentId())
                .eq(SysAuthority::getCode, updateVO.getCode()).or().eq(SysAuthority::getUrl, updateVO.getUrl()));
        if (ObjUtil.isNotEmpty(sysAuthority) && !updateVO.getId().equals(sysAuthority.getId())) {
            throw new CustomException(ErrorEnum.AUTHORITY_EXISTS.getCode(), ErrorEnum.AUTHORITY_EXISTS.getMessage());
        }
        SysAuthority updateSysAuthority = new SysAuthority();
        BeanUtils.copyProperties(updateVO, updateSysAuthority);
        super.updateById(updateSysAuthority);
        // 更新角色权限表，剔除拥有该权限的用户缓存
        updateAuthority(updateVO.getId());
        redisService.deleteKey(SysAuthority.REDIS_KEY + updateVO.getId());
        return true;
    }


    private void updateAuthority(Long id) {
        List<RoleAuthorityDTO> roleAuthorityDtoS = sysRoleAuthorityService.findByAuthorityId(id);
        if (CollUtil.isNotEmpty(roleAuthorityDtoS)) {
            List<Long> roleIds = roleAuthorityDtoS.stream().map(RoleAuthorityDTO::getRoleId).distinct().toList();
            List<UserRoleDTO> userRoleDtoS = sysUserRoleService.findByRoleIds(roleIds);
            List<String> keys = new ArrayList<>();
            if (CollUtil.isNotEmpty(userRoleDtoS)) {
                userRoleDtoS.forEach(userRole -> keys.add(SysAuthority.REDIS_KEY + userRole.getUserId()));
            }
            redisService.delAllByKeys(keys);
        }
    }


    /**
     * 根据ID删除权限
     *
     * @param id 权限id
     * @return true/false
     */
    @Override
    public boolean delete(Long id) {
        SysAuthority sysAuthority = getAuthority(id);
        super.removeById(sysAuthority);
        // 更新角色权限表，剔除拥有该权限的用户缓存
        updateAuthority(id);
        redisService.deleteKey(SysAuthority.REDIS_KEY + id);
        return true;
    }


    /**
     * 根据ID集合批量删除权限
     *
     * @param idVOs 权限id集合
     * @return true/false
     */
    @Override
    public boolean batchDelete(IdVOs idVOs) {
        List<Long> ids = idVOs.getIds();
        super.removeBatchByIds(ids);
        // 更新角色权限表，剔除拥有该权限的用户缓存
        List<String> keys = new ArrayList<>();
        ids.forEach(id -> {
            updateAuthority(id);
            keys.add(SysAuthority.REDIS_KEY + id);
        });
        redisService.delAllByKeys(keys);
        return true;
    }


    private SysAuthority getAuthority(Long id) {
        SysAuthority sysAuthority = super.getById(id);
        if (ObjUtil.isEmpty(sysAuthority)) {
            throw new CustomException(ErrorEnum.AUTHORITY_NOT_EXISTS.getCode(), ErrorEnum.AUTHORITY_NOT_EXISTS.getMessage());
        }
        return sysAuthority;
    }


    @Override
    public AuthorityInfoVO findById(Long id) {
        AuthorityInfoVO infoVO = (AuthorityInfoVO) redisService.get(SysAuthority.REDIS_KEY + id);
        if (ObjUtil.isEmpty(infoVO)) {
            SysAuthority sysAuthority = getAuthority(id);
            infoVO = new AuthorityInfoVO();
            BeanUtils.copyProperties(sysAuthority, infoVO);
            redisService.setOneHour(SysAuthority.REDIS_KEY + id, infoVO);
        }
        return infoVO;
    }


    /**
     * 分页查询用户信息
     *
     * @param pageVO 分页条件
     * @return IPage<UserInfoVO>
     */
    @Override
    public IPage<AuthorityGetVO> findPage(PageVO pageVO) {
        IPage<SysAuthority> ipage = new Page<>(pageVO.getCurrent(), pageVO.getSize());
        QueryWrapper<SysAuthority> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysAuthority::getParentId, CommonConstant.ONE);
        queryWrapper.lambda().eq(ObjectUtil.isNotEmpty(pageVO.getParentId()), SysAuthority::getParentId, pageVO.getParentId());
        queryWrapper.lambda().like(ObjectUtil.isNotEmpty(pageVO.getName()), SysAuthority::getName, pageVO.getName());
        queryWrapper.lambda().like(ObjectUtil.isNotEmpty(pageVO.getType()), SysAuthority::getType, pageVO.getType());
        queryWrapper.lambda().like(ObjectUtil.isNotEmpty(pageVO.getCode()), SysAuthority::getCode, pageVO.getCode());
        queryWrapper.lambda().orderByAsc(SysAuthority::getSort);
        IPage<SysAuthority> infoPage = super.page(ipage, queryWrapper);
        IPage<AuthorityGetVO> resultPage = new Page<>(infoPage.getCurrent(), infoPage.getSize(), infoPage.getTotal());
        List<AuthorityGetVO> list = Optional.ofNullable(infoPage.getRecords()).orElse(new ArrayList<>()).stream().map(authority -> {
            AuthorityGetVO getVO = new AuthorityGetVO();
            BeanUtils.copyProperties(authority, getVO);
            return getVO;
        }).toList();
        resultPage.setRecords(list);
        return resultPage;
    }


    /**
     * 获取菜单树
     *
     * @return
     */
    @Override
    public List<Tree<String>> findMenuTree() {
        List<SysAuthority> list = super.list();
        List<Tree<String>> treeNodes = new ArrayList<>();
        if (ObjUtil.isNotEmpty(list)) {
            treeNodes = TreeUtil.build(list, String.valueOf(CommonConstant.ZERO), (treeNode, tree) -> {
                tree.setId(String.valueOf(treeNode.getId()));
                tree.setParentId(String.valueOf(treeNode.getParentId()));
                tree.setName(treeNode.getName());
                tree.putExtra("type", treeNode.getType());
                tree.putExtra("code", treeNode.getCode());
                tree.putExtra("sort", treeNode.getSort());
                tree.putExtra("url", treeNode.getUrl());
            });
        }
        return treeNodes;
    }


    /**
     * 根据父级id查询子集权限
     *
     * @param id
     * @return
     */
    @Override
    public List<AuthorityGetVO> findByParentId(Long id) {
        List<SysAuthority> list = super.list(Wrappers.<SysAuthority>lambdaQuery().eq(SysAuthority::getParentId, id));
        List<AuthorityGetVO> getVos = new ArrayList<>();
        if (CollUtil.isNotEmpty(list)) {
            list.forEach(authority -> {
                AuthorityGetVO getVO = new AuthorityGetVO();
                BeanUtils.copyProperties(authority, getVO);
                getVos.add(getVO);
            });
        }
        return getVos;
    }


    /**
     * 根据id查询权限
     *
     * @param ids 权限id集合
     * @return {@link List}<{@link AuthorityDTO}>
     */
    @Override
    public List<AuthorityDTO> findByIds(List<Long> ids) {
        List<SysAuthority> list = super.list(Wrappers.<SysAuthority>lambdaQuery().in(SysAuthority::getId, ids));
        return Optional.ofNullable(list).orElse(new ArrayList<>()).stream().map(authority -> {
            AuthorityDTO authorityDTO = new AuthorityDTO();
            BeanUtils.copyProperties(authority, authorityDTO);
            return authorityDTO;
        }).toList();
    }
}
