package com.buni.usercommon.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author zp.wei
 * @date 2023/9/19 15:06
 */
@Data
public class LoginVO {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

}
