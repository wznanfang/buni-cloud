package com.buni.userservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buni.usercommon.entity.Role;
import com.buni.userservice.mapper.RoleMapper;
import com.buni.userservice.service.RoleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @description 针对表【role(角色
)】的数据库操作Service实现
 * @createDate 2023-09-25 13:45:26
 */
@Slf4j
@Service
@AllArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {


}