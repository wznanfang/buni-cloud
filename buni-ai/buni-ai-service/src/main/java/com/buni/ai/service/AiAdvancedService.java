package com.buni.ai.service;

import com.buni.ai.vo.qianfan.TalkVO;
import org.springframework.core.io.Resource;
import reactor.core.publisher.Flux;

/**
 * 高级AI服务接口
 * 
 * @author zp.wei
 * @date 2024/12/19
 */
public interface AiAdvancedService {

    /**
     * 流式对话
     * 
     * @param talkVO 对话参数
     * @return 流式响应
     */
    Flux<String> streamChat(TalkVO talkVO);

    /**
     * 带系统提示的对话
     * 
     * @param systemPrompt 系统提示
     * @param talkVO 对话参数
     * @return 对话结果
     */
    String chatWithSystemPrompt(String systemPrompt, TalkVO talkVO);

    /**
     * 文档问答（简化版）
     * 
     * @param document 文档资源
     * @param question 问题
     * @return 答案
     */
    String documentQa(Resource document, String question);

    /**
     * 多轮对话（保持上下文）
     * 
     * @param sessionId 会话ID
     * @param talkVO 对话参数
     * @return 对话结果
     */
    String multiTurnChat(String sessionId, TalkVO talkVO);

    /**
     * 生成图片描述
     * 
     * @param imageUrl 图片URL
     * @return 图片描述
     */
    String imageDescription(String imageUrl);

    /**
     * 代码生成
     * 
     * @param requirement 需求描述
     * @param language 编程语言
     * @return 生成的代码
     */
    String codeGeneration(String requirement, String language);
} 