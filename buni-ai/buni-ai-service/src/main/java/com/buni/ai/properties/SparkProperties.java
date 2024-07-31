package com.buni.ai.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zp.wei
 * @date 2024/4/03 20:23
 */
@Data
@Component
@ConfigurationProperties(prefix = "buni.ai.spark")
public class SparkProperties {

    /**
     * id
     */
    private String appid;

    /**
     * key
     */
    private String apiKey;

    /**
     * secret
     */
    private String apiSecret;




}
