package com.buni.framework.config.exception;

import com.buni.framework.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @author zp.wei
 * @date 2023/9/21 15:09
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 全局自定义异常处理
     *
     * @param exception 错误信息
     * @return Result
     */
    @ExceptionHandler(value = {CustomException.class})
    public Result<CustomException> myExceptionHandler(CustomException exception) {
        log.error("----------错误信息:", exception);
        return Result.error(exception);
    }


}

