package com.buni.user.vo.role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author zp.wei
 * @date 2023/9/25 13:54
 */
@Schema(description = "用户角色")
@Data
public class UserRoleDTO {

    /**
     * id
     */
    @Schema(description = "id")
    private Long id;

    /**
     * 用户id
     */
    @Schema(description = "用户id")
    private Long userId;

    /**
     * 角色id
     */
    @Schema(description = "角色id")
    private Long roleId;


}
