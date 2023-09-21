package com.buni.userservice.vo.user;

import com.buni.usercommon.enums.SexEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author zp.wei
 * @date 2023/9/19 10:41
 */
@Data
public class AddVO {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

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


}
