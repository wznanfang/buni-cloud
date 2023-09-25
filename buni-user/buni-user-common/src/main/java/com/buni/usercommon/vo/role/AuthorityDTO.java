package com.buni.usercommon.vo.role;

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
     * 描述
     */
    private String description;


    /**
     * 接口url
     */
    private String url;


}
