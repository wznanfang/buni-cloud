package com.buni.user.vo.roleauthority;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zp.wei
 * @date 2024/7/15 14:39
 */
@Schema(description = "角色权限编辑VO")
@Data
public class UpdateVO implements Serializable {


    /**
     * 角色id
     */
    @Schema(description = "角色id")
    private Long roleId;

    /**
     * 权限id集合
     */
    @Schema(description = "权限id集合")
    private List<Long> authorityIds;

}
