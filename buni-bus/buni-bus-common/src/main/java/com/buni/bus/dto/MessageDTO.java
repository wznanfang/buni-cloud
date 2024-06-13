package com.buni.bus.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zp.wei
 * @date 2024/6/7 16:37
 */
@Data
@Schema(description = "消息传输对象DTO")
public class MessageDTO implements Serializable {

    @Schema(description = "消息内容")
    @NotBlank(message = "消息内容不能为空")
    private String message;

    @Schema(description = "延迟时间")
    @NotNull(message = "延迟时间不能为空")
    private Long delayTime;

}
