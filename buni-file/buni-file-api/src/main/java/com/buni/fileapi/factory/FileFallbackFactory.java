package com.buni.fileapi.factory;

import com.buni.buniframework.config.exception.CustomException;
import com.buni.fileapi.service.FileFeignService;
import com.buni.filecommon.enums.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 文件服务降级处理
 */
@Slf4j
@Component
public class FileFallbackFactory implements FallbackFactory<FileFeignService> {

    @Override
    public FileFeignService create(Throwable throwable) {
        log.error("文件服务调用失败:{}", throwable.getMessage());
        return new FileFeignService() {
            @Override
            public String preview(String filename) {
                throw new CustomException(ErrorEnum.FILE_SERVICE_FAIL.getCode(), ErrorEnum.FILE_SERVICE_FAIL.getMessage());
            }
        };
    }
}
