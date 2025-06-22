package com.buni.ai.service;

import com.buni.ai.vo.qianfan.TalkVO;

/**
 * AI聊天服务接口
 * 
 * @author zp.wei
 * @date 2024/12/19
 */
public interface AiChatService {

    /**
     * 使用默认AI模型进行对话
     * 
     * @param talkVO 对话参数
     * @return 对话结果
     */
    String chat(TalkVO talkVO);

    /**
     * 使用OpenAI进行对话
     * 
     * @param talkVO 对话参数
     * @return 对话结果
     */
    String chatWithOpenAI(TalkVO talkVO);

    /**
     * 使用千帆进行对话
     * 
     * @param talkVO 对话参数
     * @return 对话结果
     */
    String chatWithQianfan(TalkVO talkVO);

    /**
     * 使用讯飞星火进行对话
     * 
     * @param talkVO 对话参数
     * @return 对话结果
     */
    String chatWithSpark(TalkVO talkVO);

    /**
     * 使用Ollama进行对话（本地模型）
     * 
     * @param talkVO 对话参数
     * @return 对话结果
     */
    String chatWithOllama(TalkVO talkVO);
} 