package com.buni.framework.config.enumsconverter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zp.wei
 * @date 2024/7/3 14:01
 */
public class StringConverterFactory implements ConverterFactory<String, BaseEnum> {

    private static final Map<Class<?>, Converter<?, ?>> CONVERTERS = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        // 强制类型检查，确保 targetType 是枚举
        if (!Enum.class.isAssignableFrom(targetType)) {
            throw new IllegalArgumentException("Target type " + targetType.getName() + " is not an enum");
        }

        return (Converter<String, T>) CONVERTERS.computeIfAbsent(targetType, key -> new StringConverter<>((Class<? extends Enum<?>>) targetType));
    }
}
