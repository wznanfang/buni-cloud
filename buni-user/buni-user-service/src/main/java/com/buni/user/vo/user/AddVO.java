package com.buni.user.vo.user;

import com.buni.user.enums.SexEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author zp.wei
 * @date 2023/9/19 10:41
 */
@Data
public class AddVO {

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空")
    private String name;

    /**
     * 性别
     */
    private SexEnum sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 电话
     */
    @NotBlank(message = "电话不能为空")
    private String tel;


}
