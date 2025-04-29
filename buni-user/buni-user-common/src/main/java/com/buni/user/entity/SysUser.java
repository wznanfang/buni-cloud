package com.buni.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.buni.user.enums.BooleanEnum;
import com.buni.user.enums.SexEnum;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @TableName user
 */
@TableName(value = "sys_user")
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysUser implements Serializable {

    /**
     * redis缓存KEY
     */
    public static final String REDIS_KEY = "user:";

    /**
     * id
     */
    @NotNull(message = "[id]不能为空")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT, value = "create_time")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE, value = "update_time")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updateTime;

    /**
     * 用户名
     */
    @Size(max = 20, message = "用户名不能超过20")
    private String username;

    /**
     * openId
     */
    private String openId;

    /**
     * 密码
     */
    @Size(max = 255, message = "密码不能超过255")
    private String password;

    /**
     * 名字
     */
    @Size(max = 100, message = "名字不能超过100")
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
     * 电话
     */
    private String tel;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 是否是超级管理员(0:否，1：是)
     */
    private BooleanEnum admin;

    /**
     * 是否启用(0:否，1：是)
     */
    private BooleanEnum enable;

    /**
     * 是否删除(0:否，1：是)
     */
    @TableLogic
    private BooleanEnum deleted;


}
