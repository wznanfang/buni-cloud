package com.buni.user.dto.role;

import com.buni.user.enums.AuthTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 角色
 *
 * @TableName authority
 */
@Schema(name = "权限DTO")
@Data
public class AuthorityDTO {

    /**
     * id
     */
    @Schema(description = "id")
    private Long id;

    /**
     * 名字
     */
    @Schema(description = "名字")
    @Size(max = 50, message = "名字不能超过50")
    private String name;

    /**
     * 父级id
     */
    @Schema(description = "父级id")
    private Long parentId;

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

    /**
     * 序号
     */
    @Schema(description = "序号")
    private Integer sort;


    /**
     * 接口url
     */
    @Schema(description = "接口url")
    private String url;


}
