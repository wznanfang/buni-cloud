package com.buni.ai.manager;

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
     * @param content
     * @return
     */
    public String talk(String content) {
        log.info("问：{}", content);
        messages.add(SparkMessage.userContent(content));
        SparkRequest sparkRequest = SparkRequest.builder()
                .messages(messages)
                .maxTokens(4096)
                .temperature(0.2)
                .apiVersion(SparkApiVersion.V2_0).build();
        SparkSyncChatResponse chatResponse = sparkclient.chatSync(sparkRequest);
        String responseContent = chatResponse.getContent();
        log.info("答：{}", responseContent);
        // 根据返回的结果，将答案中的科大讯飞星火认知大模型等替换掉
        responseContent = responseContent.replace("科大讯飞", "不逆科技");
        responseContent = responseContent.replace("讯飞星火", "不逆");
        responseContent = responseContent.replace("讯飞星火认知", "不逆智能");
        responseContent = responseContent.replace("认知大模型", "AI大模型");
        return responseContent;
    }

}
