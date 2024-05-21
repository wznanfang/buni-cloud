package com.buni.user.vo.role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author zp.wei
 * @date 2023/9/25 13:55
 */
@Schema(description = "角色信息")
@Data
public class RoleDTO {

    /**
     * id
     */
    @Schema(description = "id")
    private Long id;


    /**
     * 角色名字
     */
    @Schema(description = "角色名字")
    private String name;

}
