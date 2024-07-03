package com.buni.framework.config.enumsconverter;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zp.wei
 * @date 2024/7/3 14:00
 */
public class StringConverter<T extends BaseEnum> implements Converter<String, T> {

    private Map<String, T> enumMap = new HashMap<>();

    public StringConverter(Class<T> enumType) {
        T[] enums = enumType.getEnumConstants();
        for (T e : enums) {
            enumMap.put(e.getCode().toString(), e);
        }
    }

    @Override
    public T convert(String source) {
        T t = enumMap.get(source);
        if (ObjectUtil.isNull(t)) {
            throw new IllegalArgumentException("无法匹配对应的枚举类型");
        }
        return t;
    }

}
