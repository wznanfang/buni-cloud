package com.buni.ai.manager;

import com.baidubce.qianfan.Qianfan;
import com.baidubce.qianfan.model.chat.ChatResponse;
import com.baidubce.qianfan.model.chat.Message;
import com.buni.ai.constant.CommonConstant;
import com.buni.ai.enums.ErrorEnum;
import com.buni.ai.vo.qianfan.TalkVO;
import com.buni.framework.config.exception.CustomException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zp.wei
 * @date 2024/8/1 9:13
 */
@Slf4j
public class QianFanManager {

    @Resource
    @Qualifier("qianfan")
    private Qianfan qianfan;

    /**
     * 保持应用上下文的消息存储
     */
    private static final List<Message> messages = new ArrayList<>();


    public String talk(TalkVO talkVO) {
        log.info("问: {}", talkVO.getQuestion());
        setMessage(CommonConstant.ROLE, talkVO.getQuestion());
        try {
            ChatResponse result = qianfan.chatCompletion()
                    .model(CommonConstant.MODEL)
                    .userId(talkVO.getUid())
                    .messages(messages)
                    .execute();
            log.info("答: {}", result.getResult());
            setMessage(CommonConstant.ASSISTANT, result.getResult());
            return result.getResult();
        } catch (Exception e) {
            log.error("千帆大模型对话异常：", e.fillInStackTrace());
            throw new CustomException(ErrorEnum.AI_TALK_ERROR.getCode(), ErrorEnum.AI_TALK_ERROR.getMessage());
        }
    }

    private static void setMessage(String role, String content) {
        Message message = new Message();
        message.setRole(role);
        message.setContent(content);
        messages.add(message);
    }


}
