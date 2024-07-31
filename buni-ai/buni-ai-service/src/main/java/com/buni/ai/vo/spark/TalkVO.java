package com.buni.ai.vo.spark;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zp.wei
 * @date 2024/7/31 15:26
 */
@Data
@Schema(description = "星火AI对话VO")
public class TalkVO implements Serializable {

    /**
     * 用户id
     */
    @Schema(description = "用户id")
    private String uid;

    /**
     * 问题
     */
    @Schema(description = "问题")
    private String question;
}
