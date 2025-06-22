package com.buni.ai.controller;

import com.buni.ai.service.AiAdvancedService;
import com.buni.ai.vo.qianfan.TalkVO;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;

/**
 * 高级AI功能控制器
 * 
 * @author zp.wei
 * @date 2024/12/19
 */
@Tag(name = "高级AI功能")
@ApiSort(2)
@AllArgsConstructor
@RestController
@RequestMapping("v1/ai/advanced")
public class AiAdvancedController {

    private final AiAdvancedService aiAdvancedService;

    @Operation(summary = "流式对话")
    @PostMapping(value = "/stream-chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> streamChat(@RequestBody TalkVO talkVO) {
        return aiAdvancedService.streamChat(talkVO).map(content -> ServerSentEvent.<String>builder().data(content).build());
    }

    @Operation(summary = "带系统提示的对话")
    @PostMapping("/chat-with-system")
    public Map<String, Object> chatWithSystemPrompt(
            @Parameter(description = "系统提示") @RequestParam String systemPrompt,
            @RequestBody TalkVO talkVO) {
        Map<String, Object> result = new HashMap<>();
        try {
            String response = aiAdvancedService.chatWithSystemPrompt(systemPrompt, talkVO);
            result.put("code", 200);
            result.put("message", "success");
            result.put("data", response);
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", e.getMessage());
        }
        return result;
    }

    @Operation(summary = "文档问答")
    @PostMapping("/document-qa")
    public Map<String, Object> documentQa(
            @Parameter(description = "PDF文档") @RequestParam("document") MultipartFile document,
            @Parameter(description = "问题") @RequestParam String question) {
        Map<String, Object> result = new HashMap<>();
        try {
            Resource resource = document.getResource();
            String response = aiAdvancedService.documentQa(resource, question);
            result.put("code", 200);
            result.put("message", "success");
            result.put("data", response);
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", "文档处理失败：" + e.getMessage());
        }
        return result;
    }

    @Operation(summary = "多轮对话")
    @PostMapping("/multi-turn-chat")
    public Map<String, Object> multiTurnChat(
            @Parameter(description = "会话ID") @RequestParam String sessionId,
            @RequestBody TalkVO talkVO) {
        Map<String, Object> result = new HashMap<>();
        try {
            String response = aiAdvancedService.multiTurnChat(sessionId, talkVO);
            result.put("code", 200);
            result.put("message", "success");
            result.put("data", response);
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", e.getMessage());
        }
        return result;
    }

    @Operation(summary = "图片描述生成")
    @PostMapping("/image-description")
    public Map<String, Object> imageDescription(
            @Parameter(description = "图片URL") @RequestParam String imageUrl) {
        Map<String, Object> result = new HashMap<>();
        try {
            String response = aiAdvancedService.imageDescription(imageUrl);
            result.put("code", 200);
            result.put("message", "success");
            result.put("data", response);
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", e.getMessage());
        }
        return result;
    }

    @Operation(summary = "代码生成")
    @PostMapping("/code-generation")
    public Map<String, Object> codeGeneration(
            @Parameter(description = "需求描述") @RequestParam String requirement,
            @Parameter(description = "编程语言") @RequestParam String language) {
        Map<String, Object> result = new HashMap<>();
        try {
            String response = aiAdvancedService.codeGeneration(requirement, language);
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