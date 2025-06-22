package com.buni.ai.config;

import com.buni.ai.properties.SparkProperties;
import io.github.briqt.spark4j.SparkClient;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 讯飞星火配置类
 * 
 * @author zp.wei
 * @date 2024/12/19
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "buni.ai.spark", name = {"appId", "apiKey", "apiSecret"})
public class SparkConfig {

    @Resource
    private SparkProperties sparkProperties;

    @Bean("sparkClient")
    public SparkClient sparkClient() {
        try {
            log.info("正在创建SparkClient，appId: {}", sparkProperties.getAppId());
            SparkClient client = new SparkClient();
            client.appid = sparkProperties.getAppId();
            client.apiKey = sparkProperties.getApiKey();
            client.apiSecret = sparkProperties.getApiSecret();
            log.info("SparkClient创建成功");
            return client;
        } catch (Exception e) {
            log.error("SparkClient创建失败: {}", e.getMessage(), e);
            throw new RuntimeException("SparkClient创建失败", e);
        }
    }
} 