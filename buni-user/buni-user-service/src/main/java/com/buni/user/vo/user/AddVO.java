package com.buni.user.vo.user;

import com.buni.user.enums.BooleanEnum;
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
public class AddVO implements Serializable {

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @Schema(description = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

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
     * 生日
     */
    @Schema(description = "生日")
    private String birthday;


    /**
     * 电话
     */
    @Schema(description = "电话")
    @NotBlank(message = "电话不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "电话格式不正确")
    private String phone;

    /**
     * 是否启用(0:否，1：是)
     */
    @Schema(description = "是否启用(0:否，1：是)")
    private BooleanEnum enable;


}
