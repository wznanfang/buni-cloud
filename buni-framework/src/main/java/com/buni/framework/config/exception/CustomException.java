package com.buni.framework.config.exception;

import lombok.Data;

/**
 * 自定义异常
 *
 * @author zp.wei
 * @date 2023/9/21 13:46
 */
@Data
public class CustomException extends RuntimeException {

    private final Integer code;
    private final String message;


    public CustomException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }


}
