package com.buni.bunifileapi.service;

import com.buni.bunifileapi.config.FeignRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Component
@FeignClient(value = "buni-file", configuration = FeignRequestInterceptor.class)
public interface FileFeignService {

    /**
     * 文件上传
     *
     * @return 文件预览地址
     */
    @PostMapping("/v1/upload")
    String upload(@RequestParam("file") MultipartFile file);


}
