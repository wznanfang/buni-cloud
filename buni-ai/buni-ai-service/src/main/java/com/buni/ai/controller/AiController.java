package com.buni.ai.controller;

import com.buni.ai.service.AiChatService;
import com.buni.ai.vo.qianfan.TalkVO;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * AI对话控制器
 * 
 * @author zp.wei
 * @date 2024/12/19
 */
@Tag(name = "AI对话服务")
@ApiSort(1)
@AllArgsConstructor
@RestController
@RequestMapping("v1/ai")
public class AiController {

    private final AiChatService aiChatService;

    @Operation(summary = "默认AI对话")
    @PostMapping("/chat")
    public Map<String, Object> chat(@RequestBody TalkVO talkVO) {
        Map<String, Object> result = new HashMap<>();
        try {
            String response = aiChatService.chat(talkVO);
            result.put("code", 200);
            result.put("message", "success");
            result.put("data", response);
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", e.getMessage());
        }
        return result;
    }

    @Operation(summary = "Spring AI千帆对话")
    @PostMapping("/chat/spring-ai-qianfan")
    public Map<String, Object> chatWithSpringAiQianfan(@RequestBody TalkVO talkVO) {
        Map<String, Object> result = new HashMap<>();
        try {
            String response = aiChatService.chatWithSpringAiQianfan(talkVO);
            result.put("code", 200);
            result.put("message", "success");
            result.put("data", response);
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", e.getMessage());
        }
        return result;
    }

    @Operation(summary = "千帆对话")
    @PostMapping("/chat/qianfan")
    public Map<String, Object> chatWithQianfan(@RequestBody TalkVO talkVO) {
        Map<String, Object> result = new HashMap<>();
        try {
            String response = aiChatService.chatWithQianfan(talkVO);
            result.put("code", 200);
            result.put("message", "success");
            result.put("data", response);
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", e.getMessage());
        }
        return result;
    }

    @Operation(summary = "讯飞星火对话")
    @PostMapping("/chat/spark")
    public Map<String, Object> chatWithSpark(@RequestBody TalkVO talkVO) {
        Map<String, Object> result = new HashMap<>();
        try {
            String response = aiChatService.chatWithSpark(talkVO);
            result.put("code", 200);
            result.put("message", "success");
            result.put("data", response);
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", e.getMessage());
        }
        return result;
    }

    @Operation(summary = "千帆对话（兼容旧接口）")
    @GetMapping("/qianfan/talk")
    public Map<String, Object> qianfanTalk(@ParameterObject TalkVO talkVO) {
        Map<String, Object> result = new HashMap<>();
        try {
            String response = aiChatService.chatWithQianfan(talkVO);
            result.put("code", 200);
            result.put("message", "success");
            result.put("data", response);
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", e.getMessage());
        }
        return result;
    }
} 