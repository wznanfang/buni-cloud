package com.buni.user.vo.user;

import com.buni.framework.util.StringSerializer;
import com.buni.user.enums.BooleanEnum;
import com.buni.user.enums.SexEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zp.wei
 * @date 2023/9/21 11:36
 */
@Schema(description = "用户信息GetVO")
@Data
public class UserGetVO implements Serializable {

    /**
     * id
     */
    @Schema(description = "id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @Schema(description = "修改时间")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String username;

    /**
     * 名字
     */
    @Schema(description = "名字")
    private String name;

    /**
     * 年龄
     */
    @Schema(description = "年龄")
    private Integer age;

    /**
     * 性别
     */
    @Schema(description = "性别（0：女；1：男）")
    private SexEnum sex;

    /**
     * 电话
     */
    @Schema(description = "电话")
    private String tel;

    /**
     * 是否是超级管理员(0:否，1：是)
     */
    @Schema(description = "是否是超级管理员(0:否，1：是)")
    private BooleanEnum admin;

    /**
     * 是否启用(0:否，1：是)
     */
    @Schema(description = "是否启用(0:否，1：是)")
    private BooleanEnum enable;

    /**
     * 是否删除(0:否，1：是)
     */
    @Schema(description = "是否删除(0:否，1：是)")
    private BooleanEnum deleted;

}
