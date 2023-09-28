package com.buni.usercommon.vo.login;

import com.buni.buniframework.util.StringSerializer;
import com.buni.usercommon.enums.BooleanEnum;
import com.buni.usercommon.enums.SexEnum;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zp.wei
 * @date 2023/9/19 15:07
 */
@Data
public class UserLoginVO implements Serializable {


    /**
     * id
     */
    @JsonSerialize(using = StringSerializer.class)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private SexEnum sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 是否是超级管理员(0:否，1：是)
     */
    private BooleanEnum isAdmin;

    /**
     * 是否删除(0:否，1：是)
     */
    private BooleanEnum isDelete;

    /**
     * token信息
     */
    private TokenVO tokenVO;


}
