package com.buni.ai.manager;

import com.buni.ai.constant.CommonConstant;
import com.buni.ai.enums.ErrorEnum;
import com.buni.ai.vo.spark.TalkVO;
import com.buni.framework.config.exception.CustomException;
import io.github.briqt.spark4j.SparkClient;
import io.github.briqt.spark4j.constant.SparkApiVersion;
import io.github.briqt.spark4j.model.SparkMessage;
import io.github.briqt.spark4j.model.SparkSyncChatResponse;
import io.github.briqt.spark4j.model.request.SparkRequest;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zp.wei
 * @date 2024/7/31 9:41
 */
@Slf4j
public class SparkManager {

    @Resource
    @Qualifier("sparkclient")
    private SparkClient sparkclient;

    /**
     * 保持应用上下文的消息存储
     */
    private static final List<SparkMessage> messages = new ArrayList<>();


    /**
     * 发送消息
     *
     * @param talkVO
     * @return
     */
    public String talk(TalkVO talkVO) {
        log.info("问：{}", talkVO.getQuestion());
        messages.add(SparkMessage.userContent(talkVO.getQuestion()));
        SparkRequest sparkRequest = SparkRequest.builder()
                .uid(talkVO.getUid())
                .messages(messages)
                .maxTokens(CommonConstant.TOKENS)
                .temperature(CommonConstant.TEMPERATURE)
                .apiVersion(SparkApiVersion.V3_5).build();
        try {
            SparkSyncChatResponse chatResponse = sparkclient.chatSync(sparkRequest);
            String responseContent = chatResponse.getContent();
            log.info("答：{}", responseContent);
            messages.add(SparkMessage.assistantContent(responseContent));
            return responseContent;
        } catch (Exception e) {
            log.error("讯飞星火AI对话异常：", e.fillInStackTrace());
            throw new CustomException(ErrorEnum.AI_TALK_ERROR.getCode(), ErrorEnum.AI_TALK_ERROR.getMessage());
        }
    }


}
