package com.buni.user.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.buni.framework.config.enumsconverter.BaseEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @author zp.wei
 * @date 2023/9/20 16:56
 */
@Getter
//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum BooleanEnum implements BaseEnum {

    NO(0, "否"),
    YES(1, "是"),
    ;

    @EnumValue
    @JsonValue
    private Integer code;
    private String value;

    BooleanEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }


}
