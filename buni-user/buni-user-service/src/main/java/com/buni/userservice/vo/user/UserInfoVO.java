package com.buni.userservice.vo.user;

import com.buni.usercommon.enums.BooleanEnum;
import com.buni.usercommon.enums.SexEnum;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zp.wei
 * @date 2023/9/21 11:36
 */
@Data
public class UserInfoVO {

    /**
     * id
     */
    private Long id;

    /**
     * 创建时间
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updateTime;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 名字
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private SexEnum sex;

    /**
     * 是否是超级管理员(0:否，1：是)
     */
    private BooleanEnum isAdmin;

    /**
     * 是否删除(0:否，1：是)
     */
    private BooleanEnum isDelete;

}
