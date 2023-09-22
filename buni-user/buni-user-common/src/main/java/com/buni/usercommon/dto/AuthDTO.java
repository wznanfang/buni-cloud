package com.buni.usercommon.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author zp.wei
 * @date 2023/9/22 17:03
 */
@Data
@Builder
public class AuthDTO {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 客户端标识
     */
    private String clientIdentity;

    /**
     * token
     */
    private String token;


}
