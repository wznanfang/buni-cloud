package com.buni.ai.service.impl;

import com.buni.ai.service.AiAdvancedService;
import com.buni.ai.vo.qianfan.TalkVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 高级AI服务实现类
 * 
 * @author zp.wei
 * @date 2024/12/19
 */
@Slf4j
@Service
public class AiAdvancedServiceImpl implements AiAdvancedService {

    // 会话上下文存储
    private final Map<String, List<String>> sessionContexts = new ConcurrentHashMap<>();

    @Override
    public Flux<String> streamChat(TalkVO talkVO) {
        log.info("开始流式对话，问题: {}", talkVO.getQuestion());
        try {
            // 暂时返回模拟流式响应
            return Flux.just("这是", "流式", "对话", "的", "回复：", talkVO.getQuestion());
        } catch (Exception e) {
            log.error("流式对话异常：", e);
            throw new RuntimeException("流式对话失败: " + e.getMessage());
        }
    }

    @Override
    public String chatWithSystemPrompt(String systemPrompt, TalkVO talkVO) {
        log.info("带系统提示的对话，系统提示: {}, 问题: {}", systemPrompt, talkVO.getQuestion());
        try {
            String response = "系统提示：" + systemPrompt + "\nAI回复：" + talkVO.getQuestion();
            log.info("带系统提示的对话回答: {}", response);
            return response;
        } catch (Exception e) {
            log.error("带系统提示的对话异常：", e);
            throw new RuntimeException("带系统提示的对话失败: " + e.getMessage());
        }
    }

    @Override
    public String documentQa(Resource document, String question) {
        log.info("文档问答，问题: {}", question);
        try {
            String response = "文档：" + document.getFilename() + "\n问题：" + question + "\n回答：这是文档问答的回复";
            log.info("文档问答回答: {}", response);
            return response;
        } catch (Exception e) {
            log.error("文档问答异常：", e);
            throw new RuntimeException("文档问答失败: " + e.getMessage());
        }
    }

    @Override
    public String multiTurnChat(String sessionId, TalkVO talkVO) {
        log.info("多轮对话，会话ID: {}, 问题: {}", sessionId, talkVO.getQuestion());
        try {
            // 获取或创建会话上下文
            List<String> messages = sessionContexts.computeIfAbsent(sessionId, k -> List.of());
            
            // 添加用户消息
            messages.add("用户: " + talkVO.getQuestion());
            
            // 模拟AI响应
            String response = "AI: 这是多轮对话的回复：" + talkVO.getQuestion();
            messages.add(response);
            
            // 限制上下文长度（保留最近10轮对话）
            if (messages.size() > 20) {
                messages = messages.subList(messages.size() - 20, messages.size());
                sessionContexts.put(sessionId, messages);
            }
            
            log.info("多轮对话回答: {}", response);
            return response;
        } catch (Exception e) {
            log.error("多轮对话异常：", e);
            throw new RuntimeException("多轮对话失败: " + e.getMessage());
        }
    }

    @Override
    public String imageDescription(String imageUrl) {
        log.info("生成图片描述，图片URL: {}", imageUrl);
        try {
            String response = "图片描述：这是一张图片，URL为：" + imageUrl;
            log.info("图片描述: {}", response);
            return response;
        } catch (Exception e) {
            log.error("图片描述生成异常：", e);
            throw new RuntimeException("图片描述生成失败: " + e.getMessage());
        }
    }

    @Override
    public String codeGeneration(String requirement, String language) {
        log.info("代码生成，需求: {}, 语言: {}", requirement, language);
        try {
            String response = String.format("""
                // %s代码生成
                // 需求：%s
                public class GeneratedCode {
                    public static void main(String[] args) {
                        System.out.println("Hello, %s!");
                    }
                }
                """, language, requirement, language);
            log.info("代码生成结果: {}", response);
            return response;
        } catch (Exception e) {
            log.error("代码生成异常：", e);
            throw new RuntimeException("代码生成失败: " + e.getMessage());
        }
    }
} 