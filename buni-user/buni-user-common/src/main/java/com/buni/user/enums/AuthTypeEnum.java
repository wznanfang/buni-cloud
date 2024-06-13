package com.buni.user.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @author zp.wei
 * @date 2023/9/25 15:44
 */
@Getter
public enum AuthTypeEnum {

    MODEL(0, "模块"),
    MENU(1, "菜单"),
    BUTTON(2, "按钮"),
    ;

    @EnumValue
    @JsonValue
    private Integer code;
    private String value;

    AuthTypeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }


    @JsonCreator
    public static AuthTypeEnum getCode(Integer code) {
        for (AuthTypeEnum authTypeEnum : AuthTypeEnum.values()) {
            if (authTypeEnum.code.equals(code)) {
                return authTypeEnum;
            }
        }
        return null;
    }


}
