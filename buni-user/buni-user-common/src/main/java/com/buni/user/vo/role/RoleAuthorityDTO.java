package com.buni.user.vo.role;

import lombok.Data;

/**
 * 角色权限
 *
 * @TableName role_authority
 */
@Data
public class RoleAuthorityDTO {
    /**
     * id
     */
    private Long id;


    /**
     * 角色id
     */
    private Long roleId;


    /**
     * 权限id
     */
    private Long authorityId;


}
