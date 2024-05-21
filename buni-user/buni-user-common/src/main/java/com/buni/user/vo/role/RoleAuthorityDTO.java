package com.buni.user.vo.role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 角色权限
 *
 * @TableName role_authority
 */
@Schema(name = "角色权限")
@Data
public class RoleAuthorityDTO {
    /**
     * id
     */
    @Schema(name = "id")
    private Long id;


    /**
     * 角色id
     */
    @Schema(name = "角色id")
    private Long roleId;


    /**
     * 权限id
     */
    @Schema(name = "权限id")
    private Long authorityId;


}
