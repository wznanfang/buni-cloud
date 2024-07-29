package com.buni.user.dto.login;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author zp.wei
 * @date 2023/9/19 15:10
 */
@Schema(description = "登录返回token")
@Data
public class TokenVO {


    /**
     * 有效期
     */
    @Schema(description = "有效期")
    private Long expireTime;

    /**
     * token
     */
    @Schema(description = "token")
    private String token;

    /**
     * token前缀
     */
    @Schema(description = "token前缀")
    private String prefix;


}
