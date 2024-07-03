package com.buni.framework.config.exception;

import com.buni.framework.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

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
     * 当参数校验抛出错误时进行拦截后返回前端错误信息
     *
     * @param e 错误信息
     * @return
     */
    @ExceptionHandler(BindException.class)
    public Result<BindException> handleError(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        return Result.error(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
    }


    /**
     * 全局自定义异常处理
     *
     * @param exception 错误信息
     * @return Result
     */
    @ExceptionHandler(value = {CustomException.class})
    public Result<CustomException> customExceptionHandler(CustomException exception) {
        log.error("----------错误信息:", exception);
        return Result.error(exception);
    }


}

