package com.buni.user.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.io.Serializable;

/**
 * @author zp.wei
 * @date 2023/9/19 10:43
 */
@Getter
//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SexEnum implements Serializable {

    FEMALE(0, "女"),
    MALE(1, "男"),
    ;

    @EnumValue
    @JsonValue
    private Integer code;
    private String value;

    SexEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    @JsonCreator
    public static SexEnum getCode(Integer code) {
        for (SexEnum sexEnum : SexEnum.values()) {
            if (sexEnum.code.equals(code)) {
                return sexEnum;
            }
        }
        return null;
    }


}
