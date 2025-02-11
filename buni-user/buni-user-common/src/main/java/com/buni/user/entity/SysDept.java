package com.buni.user.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.buni.user.enums.BooleanEnum;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * 部门
 * @TableName sys_dept
 */
@TableName(value ="sys_dept")
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysDept  implements Serializable {

    /**
     * redis缓存KEY
     */
    public static final String REDIS_KEY = "dept:";


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
    @TableField(fill = FieldFill.INSERT, value = "create_time")
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
     * 负责人
     */
    private Long leaderUserId;

    /**
     * 是否删除(0:否，1：是)
     */
    private BooleanEnum deleted;


}
