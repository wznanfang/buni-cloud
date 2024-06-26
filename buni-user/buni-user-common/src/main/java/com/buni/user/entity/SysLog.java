package com.buni.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.buni.user.enums.BooleanEnum;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zp.wei
 * @date 2022/2/25 15:27
 */
@TableName(value = "sys_log")
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysLog implements Serializable {

    /**
     * redis缓存KEY
     */
    public static final String REDIS_KEY = "sys_log:";

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
    private String username;

    /**
     * url
     */
    private String url;

    /**
     * urlPath
     */
    private String urlPath;

    /**
     * 请求类型
     */
    private String method;

    /**
     * 请求参数
     */
    private String parameter;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 描述
     */
    private String description;

    /**
     * 耗时
     */
    private Long elapsedTime;

    /**
     * 是否删除(0:否，1：是)
     */
    @TableLogic
    private BooleanEnum deleted;


}
