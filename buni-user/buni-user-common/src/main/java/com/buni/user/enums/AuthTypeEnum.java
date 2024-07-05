package com.buni.user.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.buni.framework.config.enumsconverter.BaseEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author zp.wei
 * @date 2023/9/25 15:44
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum AuthTypeEnum implements BaseEnum {

    MODEL(0, "模块"),
    MENU(1, "菜单"),
    BUTTON(2, "按钮"),
    ;

    @EnumValue
    @JsonValue
    private Integer code;
    private String value;


}
