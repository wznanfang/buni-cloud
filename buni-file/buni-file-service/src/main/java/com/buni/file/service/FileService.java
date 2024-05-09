package com.buni.file.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author zp.wei
 * @date 2024/4/30 9:20
 */
public interface FileService {


    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    String upload(MultipartFile file);

    /**
     * 文件预览
     *
     * @param filename
     * @return
     */
    String preview(String filename);


}
