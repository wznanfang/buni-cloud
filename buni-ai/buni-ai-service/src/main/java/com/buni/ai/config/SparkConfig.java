package com.buni.ai.config;

import com.buni.ai.properties.SparkProperties;
import io.github.briqt.spark4j.SparkClient;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zp.wei
 * @date 2024/7/31 9:55
 */
@Configuration
public class SparkConfig {

    @Resource
    private SparkProperties sparkProperties;


    @Bean(name = "sparkclient")
    public SparkClient sparkclient() {
        SparkClient sparkclient = new SparkClient();
        sparkclient.appid = sparkProperties.getAppid();
        sparkclient.apiKey = sparkProperties.getApiKey();
        sparkclient.apiSecret = sparkProperties.getApiSecret();
        return sparkclient;
    }


}
