package com.buni.framework.config.enumsconverter;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;

/**
 * Integer类型无需转换
 *
 * @author zp.wei
 * @date 2024/7/3 13:53
 */
@Deprecated
public class IntegerConverter<T extends BaseEnum> implements Converter<Integer, T> {

    private Map<Integer, T> enumMap = new HashMap<>();


    public IntegerConverter(Class<T> enumType) {
        T[] enums = enumType.getEnumConstants();
        for (T e : enums) {
            enumMap.put(e.getCode(), e);
        }
    }


    @Override
    public T convert(Integer source) {
        T t = enumMap.get(source);
        if (ObjectUtil.isNull(t)) {
            throw new IllegalArgumentException("无法匹配对应的枚举类型");
        }
        return t;
    }

}
