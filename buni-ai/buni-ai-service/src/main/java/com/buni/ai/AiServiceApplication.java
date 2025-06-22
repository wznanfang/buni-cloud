package com.buni.ai;

import com.buni.ai.adapter.SparkChatModelAdapter;
import com.buni.ai.service.AiChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * AI服务启动类
 * 
 * @author zp.wei
 * @date 2024/12/19
 */
@Slf4j
@SpringBootApplication
public class AiServiceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AiServiceApplication.class, args);
        
        // 验证配置
        log.info("=== AI服务启动完成 ===");
        log.info("服务端口: {}", context.getEnvironment().getProperty("server.port"));
        
        // 检查千帆配置
        String qianfanApiKey = context.getEnvironment().getProperty("buni.ai.qianfan.apiKey");
        String qianfanSecretKey = context.getEnvironment().getProperty("buni.ai.qianfan.secretKey");
        log.info("千帆配置状态: apiKey={}, secretKey={}", 
                qianfanApiKey != null ? "已配置" : "未配置",
                qianfanSecretKey != null ? "已配置" : "未配置");
        
        // 检查讯飞星火配置
        String sparkAppId = context.getEnvironment().getProperty("buni.ai.spark.appId");
        String sparkApiKey = context.getEnvironment().getProperty("buni.ai.spark.apiKey");
        String sparkApiSecret = context.getEnvironment().getProperty("buni.ai.spark.apiSecret");
        log.info("讯飞星火配置状态: appId={}, apiKey={}, apiSecret={}", 
                sparkAppId != null ? "已配置" : "未配置",
                sparkApiKey != null ? "已配置" : "未配置",
                sparkApiSecret != null ? "已配置" : "未配置");
        
        // 检查SparkChatModelAdapter配置
        try {
            SparkChatModelAdapter sparkAdapter = context.getBean(SparkChatModelAdapter.class);
            log.info("SparkChatModelAdapter配置状态: {}", sparkAdapter.isAvailable() ? "可用" : "不可用");
            log.info("SparkChatModelAdapter配置信息: {}", sparkAdapter.getConfigInfo());
        } catch (Exception e) {
            log.warn("SparkChatModelAdapter配置失败: {}", e.getMessage());
        }
        
        // 检查AI聊天服务
        try {
            AiChatService aiChatService = context.getBean(AiChatService.class);
            log.info("AI聊天服务配置状态: 成功");
        } catch (Exception e) {
            log.warn("AI聊天服务配置失败: {}", e.getMessage());
        }
        
        log.info("=== 配置验证完成 ===");
        log.info("=== 真实AI对话功能已启用 ===");
        log.info("=== 支持千帆AI和讯飞星火API调用（HTTP客户端实现）===");
        log.info("=== 访问地址: http://localhost:10004/doc.html ===");
    }
}
