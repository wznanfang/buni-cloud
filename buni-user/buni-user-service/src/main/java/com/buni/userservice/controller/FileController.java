package com.buni.userservice.controller;

import com.buni.bunifileapi.service.FileFeignService;
import com.buni.buniframework.util.Result;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RequestMapping("/v1")
@RestController
@AllArgsConstructor
public class FileController {

    @Resource
    private FileFeignService fileFeignService;


    /**
     * minio数据上传
     *
     * @param file 文件
     * @return 链接
     */
    @PostMapping("/file")
    public Result<String> fileUpload(@RequestParam("file") MultipartFile file) {
        String url = fileFeignService.upload(file);
        return Result.ok(url);
    }

}
