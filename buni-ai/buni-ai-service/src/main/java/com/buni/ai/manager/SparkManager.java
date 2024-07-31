package com.buni.ai.manager;

import com.buni.ai.constant.CommonConstant;
import com.buni.ai.vo.spark.TalkVO;
import io.github.briqt.spark4j.SparkClient;
import io.github.briqt.spark4j.constant.SparkApiVersion;
import io.github.briqt.spark4j.model.SparkMessage;
import io.github.briqt.spark4j.model.SparkSyncChatResponse;
import io.github.briqt.spark4j.model.request.SparkRequest;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zp.wei
 * @date 2024/7/31 9:41
 */
@Slf4j
@Component
public class SparkManager {

    @Resource
    @Qualifier("sparkclient")
    private SparkClient sparkclient;

    /**
     * 保持应用上下文的消息存储
     */
    private List<SparkMessage> messages = new ArrayList<>();


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
        SparkSyncChatResponse chatResponse = sparkclient.chatSync(sparkRequest);
        String responseContent = chatResponse.getContent();
        log.info("答：{}", responseContent);
        return responseContent;
    }

}
