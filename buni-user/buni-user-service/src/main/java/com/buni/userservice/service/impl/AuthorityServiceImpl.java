package com.buni.userservice.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buni.usercommon.entity.Authority;
import com.buni.usercommon.vo.role.AuthorityDTO;
import com.buni.userservice.mapper.AuthorityMapper;
import com.buni.userservice.service.AuthorityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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


    /**
     * 根据id查询权限
     *
     * @param ids
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