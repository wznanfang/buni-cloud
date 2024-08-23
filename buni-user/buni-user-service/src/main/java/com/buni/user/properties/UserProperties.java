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
@ConfigurationProperties(prefix = "buni")
public class UserProperties {

    private String salt;

    private String password;

}
