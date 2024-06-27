package com.buni.file.enums;

/**
 * @author zp.wei
 * @date 2024/4/10 9:29
 */
public enum ErrorEnum {

    FILE_SERVICE_FAIL(40000, "文件服务不可用"),
    FILE_NOT_EXIST(40001, "文件不存在"),
    CREATE_BUCKET_ERROR(40002, "创建桶失败"),
    REMOVE_BUCKET_ERROR(40003, "删除桶失败"),
    FILE_UPLOAD_ERROR(40004, "文件上传失败"),
    FILE_DELETE_ERROR(40005, "文件删除失败"),
    FILE_URL_ERROR(40006, "获取文件链接失败"),
    FILE_INFO_ERROR(40007, "获取文件信息链接失败"),
    ;

    private final Integer code;

    private final String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    private ErrorEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
