package com.buni.userservice.controller;

import com.buni.fileapi.service.FileFeignService;
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
     * todo 调用feign服务，一直报错404，后续再看怎么解决
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
