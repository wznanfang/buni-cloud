package com.buni.bunifileapi.service;

import com.buni.bunifileapi.config.FeignRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "buni-file", configuration = FeignRequestInterceptor.class)
public interface FileFeignService {

    /**
     * 文件上传
     *
     * @return 文件预览地址
     */
    @PostMapping("/file/v1/upload")
    String upload(@RequestParam("file") MultipartFile file);


}
