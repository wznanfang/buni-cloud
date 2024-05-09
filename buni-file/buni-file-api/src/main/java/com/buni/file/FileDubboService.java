package com.buni.file;

public interface FileDubboService {


    /**
     * 获取文件链接
     *
     * @param filename 文件名
     * @return 链接
     */
    String preview(String filename);


}
