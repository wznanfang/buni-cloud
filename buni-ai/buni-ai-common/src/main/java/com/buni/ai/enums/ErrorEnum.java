package com.buni.ai.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author zp.wei
 * @date 2023/9/21 9:29
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorEnum {

    AI_TALK_ERROR(600001, "AI对话异常，请稍后再试！"),
    ;

    private Integer code;

    private String message;



}
