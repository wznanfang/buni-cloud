package com.buni.user.vo.login;

import com.buni.user.enums.AuthTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色
 *
 * @TableName authority
 */
@Schema(name = "权限DTO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityVO {

    /**
     * 0：模块，1：菜单，2：按钮
     */
    @Schema(description = "权限类型（0：模块，1：菜单，2：按钮）")
    private AuthTypeEnum type;

    /**
     * 标识码
     */
    @Schema(description = "标识码")
    private String code;


}
