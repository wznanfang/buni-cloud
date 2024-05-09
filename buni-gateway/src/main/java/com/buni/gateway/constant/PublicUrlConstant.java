package com.buni.gateway.constant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zp.wei
 * @date 2023/9/19 14:28
 */
@Data
@Order(1)
@Component
@ConfigurationProperties(prefix = "buni")
public class PublicUrlConstant {

    private List<String> publicUrl;

}
