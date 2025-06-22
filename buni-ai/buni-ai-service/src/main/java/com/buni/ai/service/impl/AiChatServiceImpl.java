package com.buni.ai.service.impl;

import com.buni.ai.adapter.SparkChatModelAdapter;
import com.buni.ai.service.AiChatService;
import com.buni.ai.vo.qianfan.TalkVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * AI聊天服务实现类
 * 
 * @author zp.wei
 * @date 2024/12/19
 */
@Slf4j
@Service
public class AiChatServiceImpl implements AiChatService {

    @Autowired(required = false)
    private SparkChatModelAdapter sparkChatModelAdapter;

    @Value("${buni.ai.qianfan.apiKey:}")
    private String qianfanApiKey;

    @Value("${buni.ai.qianfan.secretKey:}")
    private String qianfanSecretKey;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String chat(TalkVO talkVO) {
        log.info("使用默认AI模型进行对话，问题: {}", talkVO.getQuestion());
        try {
            // 优先使用千帆AI
            if (isQianfanAvailable()) {
                return chatWithQianfan(talkVO);
            } else if (sparkChatModelAdapter != null && sparkChatModelAdapter.isAvailable()) {
                // 如果千帆不可用，使用讯飞星火
                return chatWithSpark(talkVO);
            } else {
                return "所有AI服务都不可用，请检查配置";
            }
        } catch (Exception e) {
            log.error("默认AI模型对话异常：", e);
            return "AI对话失败: " + e.getMessage() + "，请稍后重试";
        }
    }

    @Override
    public String chatWithOpenAI(TalkVO talkVO) {
        log.warn("OpenAI功能暂未启用");
        return "OpenAI功能暂未启用，请使用千帆或讯飞星火";
    }

    @Override
    public String chatWithQianfan(TalkVO talkVO) {
        log.info("使用千帆进行对话，问题: {}", talkVO.getQuestion());
        try {
            if (!isQianfanAvailable()) {
                return "千帆AI配置不完整，请检查apiKey和secretKey配置";
            }

            // 构建千帆API请求
            String url = "https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/completions";
            
            // 获取访问令牌
            String accessToken = getQianfanAccessToken();
            if (accessToken == null) {
                return "获取千帆访问令牌失败，请检查API密钥配置";
            }

            url += "?access_token=" + accessToken;

            // 构建请求体
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("messages", new Object[]{
                Map.of("role", "user", "content", talkVO.getQuestion())
            });
            requestBody.put("temperature", 0.7);
            requestBody.put("max_tokens", 1000);

            // 发送请求
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<Map> response = restTemplate.postForEntity(url, entity, Map.class);
            
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                Map<String, Object> body = response.getBody();
                if (body.containsKey("result")) {
                    String result = (String) body.get("result");
                    log.info("千帆回答: {}", result);
                    return result;
                } else if (body.containsKey("error_msg")) {
                    String errorMsg = (String) body.get("error_msg");
                    log.error("千帆API错误: {}", errorMsg);
                    return "千帆API错误: " + errorMsg;
                }
            }

            return "千帆API响应异常，请稍后重试";
            
        } catch (Exception e) {
            log.error("千帆对话异常：", e);
            return "千帆对话失败: " + e.getMessage() + "，请稍后重试";
        }
    }

    @Override
    public String chatWithSpark(TalkVO talkVO) {
        log.info("使用讯飞星火进行对话，问题: {}", talkVO.getQuestion());
        try {
            if (sparkChatModelAdapter != null && sparkChatModelAdapter.isAvailable()) {
                String response = sparkChatModelAdapter.chat(talkVO.getQuestion());
                log.info("讯飞星火回答: {}", response);
                return response;
            } else {
                return "讯飞星火服务不可用，请检查配置";
            }
        } catch (Exception e) {
            log.error("讯飞星火对话异常：", e);
            return "讯飞星火对话失败: " + e.getMessage() + "，请稍后重试";
        }
    }

    @Override
    public String chatWithOllama(TalkVO talkVO) {
        log.warn("Ollama功能暂未启用");
        return "Ollama功能暂未启用，请使用千帆或讯飞星火";
    }

    /**
     * 检查千帆配置是否可用
     */
    private boolean isQianfanAvailable() {
        return qianfanApiKey != null && !qianfanApiKey.isEmpty() &&
               qianfanSecretKey != null && !qianfanSecretKey.isEmpty();
    }

    /**
     * 获取千帆访问令牌
     */
    private String getQianfanAccessToken() {
        try {
            String url = "https://aip.baidubce.com/oauth/2.0/token" +
                    "?grant_type=client_credentials" +
                    "&client_id=" + qianfanApiKey +
                    "&client_secret=" + qianfanSecretKey;

            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
            
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                Map<String, Object> body = response.getBody();
                if (body.containsKey("access_token")) {
                    return (String) body.get("access_token");
                }
            }
            
            log.error("获取千帆访问令牌失败: {}", response.getBody());
            return null;
        } catch (Exception e) {
            log.error("获取千帆访问令牌异常：", e);
            return null;
        }
    }
} 