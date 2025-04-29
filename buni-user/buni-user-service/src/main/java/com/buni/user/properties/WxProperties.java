package com.buni.user.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author zp.wei
 * @date 2023/9/19 14:28
 */
@Data
@Order(1)
@Component
@ConfigurationProperties(prefix = "buni.wx")
public class WxProperties {

    private String appId;

    private String appSecret;

    private String url;

    private String appIdParam;

    private String appSecretParam;

    private String jsCodeParam;

    private String grantTypeParam;

}
