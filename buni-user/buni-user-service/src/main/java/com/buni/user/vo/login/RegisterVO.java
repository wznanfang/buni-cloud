package com.buni.user.vo.login;

import com.buni.user.enums.GenderEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zp.wei
 * @date 2023/9/19 10:41
 */
@Schema(description = "新增用户VO")
@Data
public class RegisterVO implements Serializable {

    /**
     * 微信openId
     */
    @Schema(description = "微信openId")
    @NotBlank(message = "微信openId不能为空")
    private String wxOpenId;

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 姓名
     */
    @Schema(description = "姓名")
    @NotBlank(message = "姓名不能为空")
    private String name;

    /**
     * 性别
     */
    @Schema(description = "性别（0：女；1：男）")
    private GenderEnum gender;

    /**
     * 年龄
     */
    @Schema(description = "年龄")
    private Integer age;

    /**
     * 电话
     */
    @Schema(description = "电话")
    @NotBlank(message = "电话不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "电话格式不正确")
    private String phone;

    /**
     * 生日
     */
    @Schema(description = "生日")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "生日格式不正确")
    private String birthday;


}
