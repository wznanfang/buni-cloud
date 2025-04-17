package com.buni.user.dto.login;

import com.buni.user.enums.BooleanEnum;
import com.buni.user.enums.SexEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zp.wei
 * @date 2023/9/19 15:07
 */
@Schema(description = "用户登录返回信息")
@Data
public class UserLoginVO implements Serializable {


    /**
     * id
     */
    @Schema(description = "id")
    // todo 后续研究一下怎么解决这个问题，主要是redis序列化影响
//    @JsonSerialize(using = StringSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String username;

    /**
     * 姓名
     */
    @Schema(description = "姓名")
    private String name;

    /**
     * 性别
     */
    @Schema(description = "性别")
    private SexEnum sex;

    /**
     * 年龄
     */
    @Schema(description = "年龄")
    private Integer age;

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

    /**
     * token信息
     */
    @Schema(description = "token信息")
    private TokenVO tokenVO;


}
