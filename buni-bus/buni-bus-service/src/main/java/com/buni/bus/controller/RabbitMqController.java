package com.buni.bus.controller;

import com.alibaba.nacos.api.model.v2.Result;
import com.buni.bus.dto.MessageDTO;
import com.buni.bus.service.RabbitMqService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zp.wei
 * @date 2024/6/7 10:32
 */
@Tag(name = "mq消息管理")
@RestController
@RequestMapping("/rabbitMq")
public class RabbitMqController {

    @Resource
    private RabbitMqService rabbitMqService;


    @Operation(summary = "默认交换机发送消息", description = "使用默认交换机，默认交换队列和路由键")
    @PostMapping("/defaultSendMessage")
    public Result<Boolean> defaultSend(String message) {
        rabbitMqService.directDefaultSend(message);
        return Result.success();
    }


    @Operation(summary = "发送消息", description = "支持延时消息")
    @PostMapping("/sendMessage")
    public Result<Boolean> sendMessage(@RequestBody @Valid MessageDTO messageDTO) {
        rabbitMqService.sendMessage(messageDTO);
        return Result.success();
    }


}
