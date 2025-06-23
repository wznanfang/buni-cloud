package com.buni.ai.adapter;

import com.buni.ai.properties.SparkProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 讯飞星火ChatModel适配器（真实API调用）
 * 
 * @author zp.wei
 * @date 2024/12/19
 */
@Slf4j
@Component("sparkChatModel")
public class SparkChatModelAdapter {

    private final SparkProperties sparkProperties;
    private final RestTemplate restTemplate = new RestTemplate();

    public SparkChatModelAdapter(SparkProperties sparkProperties) {
        this.sparkProperties = sparkProperties;
        log.info("SparkChatModelAdapter初始化完成，配置: {}", getConfigInfo());
    }

    /**
     * 真实的讯飞星火对话方法
     */
    public String chat(String message) {
        try {
            log.info("开始调用讯飞星火API，消息: {}", message);
            
            if (!isAvailable()) {
                return "讯飞星火配置不完整，请检查appId、apiKey和apiSecret配置";
            }

            // 构建讯飞星火API请求
            String url = "https://spark-api-open.xf-yun.com/v1/chat/completions";
            
            // 构建请求头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", generateAuthorization());
            headers.set("Host", "spark-api.xf-yun.com");
            headers.set("Date", getGMTTime());

            // 构建请求体
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("header", Map.of(
                "app_id", sparkProperties.getAppId()
            ));
            
            Map<String, Object> parameter = new HashMap<>();
            parameter.put("chat", Map.of(
                "domain", "4.0Ultra",
                "temperature", 0.7,
                "max_tokens", 1000
            ));
            requestBody.put("parameter", parameter);
            
            Map<String, Object> payload = new HashMap<>();
            Map<String, Object> messageObj = new HashMap<>();
            messageObj.put("role", "user");
            messageObj.put("content", message);
            payload.put("message", Map.of("text", List.of(messageObj)));
            requestBody.put("payload", payload);

            // 发送请求
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
            ResponseEntity<Map> response = restTemplate.postForEntity(url, entity, Map.class);
            
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                Map<String, Object> body = response.getBody();
                if (body.containsKey("header") && body.containsKey("payload")) {
                    Map<String, Object> header = (Map<String, Object>) body.get("header");
                    Map<String, Object> payload_response = (Map<String, Object>) body.get("payload");
                    
                    if (header.containsKey("code") && (Integer) header.get("code") == 0) {
                        if (payload_response.containsKey("choices")) {
                            Map<String, Object> choices = (Map<String, Object>) payload_response.get("choices");
                            if (choices.containsKey("text")) {
                                List<Map<String, Object>> texts = (List<Map<String, Object>>) choices.get("text");
                                if (!texts.isEmpty()) {
                                    String content = (String) texts.get(0).get("content");
                                    log.info("讯飞星火API调用成功，回复: {}", content);
                                    return content;
                                }
                            }
                        }
                    } else {
                        String errorMsg = "讯飞星火API错误: " + header.get("message");
                        log.error(errorMsg);
                        return errorMsg;
                    }
                }
            }

            return "讯飞星火API响应异常，请稍后重试";
            
        } catch (Exception e) {
            log.error("讯飞星火对话异常：", e);
            return "讯飞星火服务异常：" + e.getMessage() + "，请稍后重试";
        }
    }

    /**
     * 检查服务状态
     */
    public boolean isAvailable() {
        return sparkProperties.getAppId() != null && 
               sparkProperties.getApiKey() != null && 
               sparkProperties.getApiSecret() != null;
    }

    /**
     * 获取配置信息
     */
    public String getConfigInfo() {
        return String.format("appId: %s, apiKey: %s, apiSecret: %s", 
                sparkProperties.getAppId(),
                sparkProperties.getApiKey() != null ? "已配置" : "未配置",
                sparkProperties.getApiSecret() != null ? "已配置" : "未配置");
    }

    /**
     * 生成Authorization头
     */
    private String generateAuthorization() {
        try {
            String host = "spark-api.xf-yun.com";
            String date = getGMTTime();
            String algorithm = "hmac-sha256";
            String headers = "host date request-line";
            String signatureOrigin = "host: " + host + "\n" + "date: " + date + "\n" + "POST /v3.1/chat HTTP/1.1";
            String signatureSha = hmacsha256(signatureOrigin, sparkProperties.getApiSecret());
            String authorizationOrigin = String.format("api_key=\"%s\", algorithm=\"%s\", headers=\"%s\", signature=\"%s\"",
                    sparkProperties.getApiKey(), algorithm, headers, signatureSha);
            return "Authorization: " + authorizationOrigin;
        } catch (Exception e) {
            log.error("生成Authorization失败: {}", e.getMessage());
            return "";
        }
    }

    /**
     * 获取GMT时间
     */
    private String getGMTTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        return sdf.format(new Date());
    }

    /**
     * HMAC-SHA256签名
     */
    private String hmacsha256(String data, String key) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        mac.init(secretKeySpec);
        byte[] hash = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hash);
    }
} 