package com.buni.user.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.buni.framework.config.enumsconverter.BaseEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author zp.wei
 * @date 2023/9/19 10:43
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum GenderEnum implements BaseEnum {

    FEMALE(0, "女"),
    MALE(1, "男"),
    UNKNOWN(2, "未知"),
    ;

    @EnumValue
    @JsonValue
    private Integer code;
    private String value;


}
