package com.buni.fileapi.service;

import com.buni.fileapi.config.FeignRequestInterceptor;
import com.buni.fileapi.factory.FileFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "buni-file", configuration = FeignRequestInterceptor.class, fallbackFactory = FileFallbackFactory.class)
public interface FileFeignService {


    /**
     * 获取文件链接
     *
     * @param filename 文件名
     * @return 链接
     */
    @PostMapping(value = "/preview")
    String preview(String filename);


}
