package com.buni.usercommon.entity;

import com.baomidou.mybatisplus.annotation.*;
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
 * 角色
 *
 * @TableName role
 */
@TableName(value = "role")
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {

    /**
     * redis缓存KEY
     */
    public static final String REDIS_KEY = "role:";

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
     * 角色名字
     */
    @Size(max = 50, message = "角色名字不能超过50")
    private String name;


}
