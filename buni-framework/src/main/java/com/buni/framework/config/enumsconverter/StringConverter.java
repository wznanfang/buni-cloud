package com.buni.framework.config.enumsconverter;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zp.wei
 * @date 2024/7/3 14:00
 */
@Slf4j
public class StringConverter<T extends Enum<T> & BaseEnum> implements Converter<String, T> {

    private final Map<String, T> enumMap = new ConcurrentHashMap<>();

    public StringConverter(Class<? extends Enum<?>> enumType) {
        T[] enums = (T[]) enumType.getEnumConstants();
        for (T e : enums) {
            enumMap.put(e.getCode().toString(), e);
            enumMap.put(e.name(), e);
        }
    }

    @Override
    public T convert(String source) {
        if (ObjUtil.isEmpty(source)) {
            return null;
        }

        T t = enumMap.get(source);
        if (t == null) {
            t = enumMap.get(source.toLowerCase());
        }
        if (t == null) {
            log.error("枚举转换失败: {}", source);
        }
        return t;
    }
}
