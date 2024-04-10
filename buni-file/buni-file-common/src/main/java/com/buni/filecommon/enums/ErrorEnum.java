package com.buni.filecommon.enums;

/**
 * @author zp.wei
 * @date 2024/4/10 9:29
 */
public enum ErrorEnum {

    FILE_SERVICE_FAIL(40001, "文件服务不可用"),
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
