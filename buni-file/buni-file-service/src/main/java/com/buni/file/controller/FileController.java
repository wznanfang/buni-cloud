package com.buni.file.controller;

import com.buni.framework.util.Result;
import com.buni.file.service.FileService;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件管理
 */
@RestController
@AllArgsConstructor
@RequestMapping("/v1")
public class FileController {

    @Resource
    private FileService fileService;


    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    @PostMapping("upload")
    public Result<String> upload(@RequestParam("file") MultipartFile file) {
        return Result.ok(fileService.upload(file));
    }


    /**
     * 预览文件
     *
     * @param filename
     * @return
     */
    @GetMapping("/preview")
    public Result<String> preview(@RequestParam("filename") String filename) {
        return Result.ok(fileService.preview(filename));
    }

}
