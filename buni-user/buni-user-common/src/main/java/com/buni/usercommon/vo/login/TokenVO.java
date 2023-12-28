package com.buni.usercommon.vo.login;

import lombok.Data;

/**
 * @author zp.wei
 * @date 2023/9/19 15:10
 */
@Data
public class TokenVO {


    /**
     * 有效期
     */
    private Long expireTime;

    /**
     * token
     */
    private String token;

    /**
     * token前缀
     */
    private String prefix;


}
