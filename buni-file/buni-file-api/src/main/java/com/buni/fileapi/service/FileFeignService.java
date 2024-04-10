package com.buni.fileapi.service;

import com.buni.fileapi.config.FeignRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
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
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String upload(@RequestParam("file") MultipartFile file);


}
