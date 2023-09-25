package com.buni.userservice.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buni.usercommon.entity.UserRole;
import com.buni.usercommon.vo.role.UserRoleDTO;
import com.buni.userservice.mapper.UserRoleMapper;
import com.buni.userservice.service.UserRoleService;
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
     * @param userId
     * @return {@link List}<{@link UserRoleDTO}>
     */
    @Override
    public List<UserRoleDTO> findByUserId(Long userId) {
        List<UserRole> list = super.list(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getUserId, userId));
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


}