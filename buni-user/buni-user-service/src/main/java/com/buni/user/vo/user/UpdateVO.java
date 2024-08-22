package com.buni.user.vo.user;

import com.buni.user.enums.SexEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zp.wei
 * @date 2023/9/19 10:41
 */
@Schema(description = "修改用户信息VO")
@Data
public class UpdateVO implements Serializable {

    /**
     * 用户id
     */
    @Schema(description = "用户id")
    @NotNull(message = "id不能为空")
    private Long id;

    /**
     * 姓名
     */
    @Schema(description = "姓名")
    private String name;

    /**
     * 性别
     */
    @Schema(description = "性别（0：女；1：男）")
    private SexEnum sex;

    /**
     * 年龄
     */
    @Schema(description = "年龄")
    private Integer age;

    /**
     * 电话
     */
    @Schema(description = "电话")
    private String tel;


}
