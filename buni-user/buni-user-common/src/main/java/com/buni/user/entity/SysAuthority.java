package com.buni.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.buni.user.enums.AuthTypeEnum;
import com.buni.user.enums.BooleanEnum;
import lombok.*;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 角色
 *
 * @TableName authority
 */
@TableName(value = "sys_authority")
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysAuthority implements Serializable {

    /**
     * redis缓存KEY
     */
    public static final String REDIS_KEY = "authority:";

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
    private AuthTypeEnum type;

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
    @Size(max = 255, message = "接口url不能超过255")
    private String url;

    /**
     * 是否删除(0:否，1：是)
     */
    @TableLogic
    private BooleanEnum deleted;

}
