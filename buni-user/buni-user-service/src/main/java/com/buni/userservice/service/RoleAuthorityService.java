package com.buni.userservice.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.buni.usercommon.entity.RoleAuthority;
import com.buni.usercommon.vo.role.RoleAuthorityDTO;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【role_authority(角色权限)】的数据库操作Service
 * @createDate 2023-09-25 13:45:26
 */
public interface RoleAuthorityService extends IService<RoleAuthority> {


    /**
     * 查询roleAuthority
     *
     * @param roleIds
     * @return {@link List}<{@link RoleAuthorityDTO}>
     */
    List<RoleAuthorityDTO> findByRoleIds(List<Long> roleIds);


}