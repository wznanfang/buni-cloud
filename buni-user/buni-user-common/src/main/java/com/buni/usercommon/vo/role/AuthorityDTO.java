package com.buni.usercommon.vo.role;

import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 角色
 *
 * @TableName authority
 */
@Data
public class AuthorityDTO {

    /**
     * id
     */
    private Long id;

    /**
     * 名字
     */
    @Size(max = 50, message = "名字不能超过50")
    private String name;

    /**
     * 父级id
     */
    private Long parentId;

    /**
     * 0：模块，1：菜单，2：按钮
     */
    private Integer type;

    /**
     * 标识码
     */
    private String code;

    /**
     * 序号
     */
    private Integer sort;


    /**
     * 接口url
     */
    private String url;


}
