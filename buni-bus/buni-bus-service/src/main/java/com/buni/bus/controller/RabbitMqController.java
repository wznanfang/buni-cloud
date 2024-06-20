package com.buni.bus.controller;

import com.alibaba.nacos.api.model.v2.Result;
import com.buni.bus.dto.MessageDTO;
import com.buni.bus.service.RabbitMqService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * @author zp.wei
 * @date 2024/6/7 10:32
 */
@Tag(name = "mq消息管理")
@RestController
@RequestMapping("/v1")
public class RabbitMqController {

    @Resource
    private RabbitMqService rabbitMqService;


    @Operation(summary = "默认交换机发送消息", description = "使用默认交换机，默认交换队列和路由键")
    @GetMapping("/defaultSendMessage")
    public Result<Boolean> defaultSend(@RequestParam(value = "message") String message) {
        rabbitMqService.directDefaultSend(message);
        return Result.success();
    }


    @Operation(summary = "延时交换机发送消息", description = "支持延时消息")
    @PostMapping("/sendMessage")
    public Result<Boolean> sendMessage(@RequestBody @Valid MessageDTO messageDTO) {
        rabbitMqService.sendMessage(messageDTO);
        return Result.success();
    }


    @Operation(summary = "扇形交换机发送消息",description = "支持延时消息")
    @PostMapping("/fanoutMessage")
    public Result<Boolean> fanoutMessage(@RequestBody @Valid MessageDTO messageDTO) {
        rabbitMqService.fanoutMessage(messageDTO);
        return Result.success();
    }


}
