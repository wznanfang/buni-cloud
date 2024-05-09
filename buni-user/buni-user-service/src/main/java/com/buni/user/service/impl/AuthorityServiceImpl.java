package com.buni.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
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
import com.buni.user.enums.ErrorEnum;
import com.buni.user.vo.role.AuthorityDTO;
import com.buni.user.vo.role.RoleAuthorityDTO;
import com.buni.user.vo.role.UserRoleDTO;
import com.buni.user.mapper.AuthorityMapper;
import com.buni.user.service.AuthorityService;
import com.buni.user.service.RoleAuthorityService;
import com.buni.user.service.UserRoleService;
import com.buni.user.vo.authority.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @description 针对表【authority(角色)】的数据库操作Service实现
 * @createDate 2023-09-25 13:45:26
 */
@Slf4j
@Service
@AllArgsConstructor
public class AuthorityServiceImpl extends ServiceImpl<AuthorityMapper, Authority> implements AuthorityService {

    private RedisService redisService;
    private RoleAuthorityService roleAuthorityService;
    private UserRoleService userRoleService;


    /**
     * 新增权限
     *
     * @param addVO 权限信息
     * @return true/false
     */
    @Override
    public boolean save(AddVO addVO) {
        Authority authority = super.getOne(Wrappers.<Authority>lambdaQuery().eq(Authority::getCode, addVO.getCode()).or().eq(Authority::getUrl, addVO.getUrl()));
        if (ObjUtil.isNotEmpty(authority)) {
            throw new CustomException(ErrorEnum.AUTHORITY_EXISTS.getCode(), ErrorEnum.AUTHORITY_EXISTS.getMessage());
        }
        Authority addAuthority = new Authority();
        BeanUtils.copyProperties(addVO, addAuthority);
        super.save(addAuthority);
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
        Authority authority = super.getOne(Wrappers.<Authority>lambdaQuery().ne(Authority::getId, updateVO.getId()).eq(Authority::getCode, updateVO.getCode())
                .or().eq(Authority::getUrl, updateVO.getUrl()));
        if (ObjUtil.isNotEmpty(authority)) {
            throw new CustomException(ErrorEnum.AUTHORITY_EXISTS.getCode(), ErrorEnum.AUTHORITY_EXISTS.getMessage());
        }
        Authority updateAuthority = new Authority();
        BeanUtils.copyProperties(updateVO, updateAuthority);
        super.updateById(updateAuthority);
        // 更新角色权限表，剔除拥有该权限的用户缓存
        updateAuthority(updateVO.getId());
        redisService.deleteKey(Authority.REDIS_KEY + updateVO.getId());
        return true;
    }


    private void updateAuthority(Long id) {
        List<RoleAuthorityDTO> roleAuthorityDtoS = roleAuthorityService.findByAuthorityId(id);
        if (CollUtil.isNotEmpty(roleAuthorityDtoS)) {
            List<Long> roleIds = roleAuthorityDtoS.stream().map(RoleAuthorityDTO::getRoleId).distinct().toList();
            List<UserRoleDTO> userRoleDtoS = userRoleService.findByRoleIds(roleIds);
            List<String> keys = new ArrayList<>();
            if (CollUtil.isNotEmpty(userRoleDtoS)) {
                userRoleDtoS.forEach(userRole -> keys.add(Authority.REDIS_KEY + userRole.getUserId()));
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
        Authority authority = getAuthority(id);
        super.removeById(authority);
        // 更新角色权限表，剔除拥有该权限的用户缓存
        updateAuthority(id);
        redisService.deleteKey(Authority.REDIS_KEY + id);
        return true;
    }


    /**
     * 根据ID集合批量删除权限
     *
     * @param batchIds 权限id集合
     * @return true/false
     */
    @Override
    public boolean batchDelete(BatchIds batchIds) {
        List<Long> ids = batchIds.getIds();
        super.removeBatchByIds(ids);
        //更新角色权限表，剔除拥有该权限的用户缓存
        List<String> keys = new ArrayList<>();
        ids.forEach(id -> {
            updateAuthority(id);
            keys.add(Authority.REDIS_KEY + id);
        });
        redisService.delAllByKeys(keys);
        return true;
    }


    private Authority getAuthority(Long id) {
        Authority authority = super.getById(id);
        if (ObjUtil.isEmpty(authority)) {
            throw new CustomException(ErrorEnum.AUTHORITY_NOT_EXISTS.getCode(), ErrorEnum.AUTHORITY_NOT_EXISTS.getMessage());
        }
        return authority;
    }


    @Override
    public AuthorityInfoVO findById(Long id) {
        AuthorityInfoVO infoVO = (AuthorityInfoVO) redisService.get(Authority.REDIS_KEY + id);
        if (ObjUtil.isEmpty(infoVO)) {
            Authority authority = getAuthority(id);
            infoVO = new AuthorityInfoVO();
            BeanUtils.copyProperties(authority, infoVO);
            redisService.setOneHour(Authority.REDIS_KEY + id, infoVO);
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
        IPage<Authority> ipage = new Page<>(pageVO.getCurrent(), pageVO.getSize());
        QueryWrapper<Authority> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(ObjectUtil.isNotEmpty(pageVO.getName()), Authority::getName, pageVO.getName());
        queryWrapper.lambda().like(ObjectUtil.isNotEmpty(pageVO.getType()), Authority::getType, pageVO.getType());
        queryWrapper.lambda().like(ObjectUtil.isNotEmpty(pageVO.getCode()), Authority::getCode, pageVO.getCode());
        IPage<Authority> infoPage = super.page(ipage, queryWrapper);
        IPage<AuthorityGetVO> resultPage = new Page<>(infoPage.getCurrent(), infoPage.getSize(), infoPage.getTotal());
        List<AuthorityGetVO> list = new ArrayList<>();
        if (CollUtil.isNotEmpty(infoPage.getRecords())) {
            infoPage.getRecords().forEach(authority -> {
                AuthorityGetVO getVO = new AuthorityGetVO();
                BeanUtils.copyProperties(authority, getVO);
                list.add(getVO);
            });
        }
        resultPage.setRecords(list);
        return resultPage;
    }


    /**
     * 根据id查询权限
     *
     * @param ids 权限id集合
     * @return {@link List}<{@link AuthorityDTO}>
     */
    @Override
    public List<AuthorityDTO> findByIds(List<Long> ids) {
        List<Authority> list = super.list(Wrappers.<Authority>lambdaQuery().in(Authority::getId, ids));
        List<AuthorityDTO> authorityDtoS = new ArrayList<>();
        if (CollUtil.isNotEmpty(list)) {
            list.forEach(authority -> {
                AuthorityDTO authorityDTO = new AuthorityDTO();
                BeanUtil.copyProperties(authority, authorityDTO);
                authorityDtoS.add(authorityDTO);
            });
        }
        return authorityDtoS;
    }
}