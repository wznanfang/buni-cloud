package com.buni.ai.controller;

import com.buni.ai.adapter.SparkChatModelAdapter;
import com.buni.ai.service.AiChatService;
import com.buni.ai.vo.qianfan.TalkVO;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * AI功能测试控制器
 * 
 * @author zp.wei
 * @date 2024/12/19
 */
@Tag(name = "AI功能测试")
@ApiSort(3)
@AllArgsConstructor
@RestController
@RequestMapping("v1/ai/test")
public class AiTestController {

    private final AiChatService aiChatService;
    private final SparkChatModelAdapter sparkChatModelAdapter;

    @Operation(summary = "测试默认AI对话")
    @PostMapping("/default")
    public Map<String, Object> testDefaultChat(@RequestBody TalkVO talkVO) {
        Map<String, Object> result = new HashMap<>();
        try {
            String response = aiChatService.chat(talkVO);
            result.put("code", 200);
            result.put("message", "success");
            result.put("data", response);
            result.put("service", "默认AI（智能路由）");
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", e.getMessage());
        }
        return result;
    }

    @Operation(summary = "测试千帆AI对话")
    @PostMapping("/qianfan")
    public Map<String, Object> testQianfanChat(@RequestBody TalkVO talkVO) {
        Map<String, Object> result = new HashMap<>();
        try {
            String response = aiChatService.chatWithQianfan(talkVO);
            result.put("code", 200);
            result.put("message", "success");
            result.put("data", response);
            result.put("service", "千帆AI");
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", e.getMessage());
        }
        return result;
    }

    @Operation(summary = "测试讯飞星火对话")
    @PostMapping("/spark")
    public Map<String, Object> testSparkChat(@RequestBody TalkVO talkVO) {
        Map<String, Object> result = new HashMap<>();
        try {
            String response = aiChatService.chatWithSpark(talkVO);
            result.put("code", 200);
            result.put("message", "success");
            result.put("data", response);
            result.put("service", "讯飞星火");
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", e.getMessage());
        }
        return result;
    }

    @Operation(summary = "检查AI服务状态")
    @GetMapping("/status")
    public Map<String, Object> checkStatus() {
        Map<String, Object> result = new HashMap<>();
        try {
            Map<String, Object> status = new HashMap<>();
            
            // 检查讯飞星火状态
            if (sparkChatModelAdapter != null) {
                status.put("spark", Map.of(
                    "available", sparkChatModelAdapter.isAvailable(),
                    "config", sparkChatModelAdapter.getConfigInfo()
                ));
            } else {
                status.put("spark", Map.of("available", false, "config", "未配置"));
            }
            
            result.put("code", 200);
            result.put("message", "success");
            result.put("data", status);
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", e.getMessage());
        }
        return result;
    }

    @Operation(summary = "简单测试对话")
    @PostMapping("/simple")
    public Map<String, Object> simpleTest(@RequestParam String question) {
        Map<String, Object> result = new HashMap<>();
        try {
            TalkVO talkVO = new TalkVO();
            talkVO.setQuestion(question);
            
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
} 