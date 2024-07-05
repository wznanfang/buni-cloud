package com.buni.framework.config.enumsconverter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Integer类型无需转换
 *
 * @author zp.wei
 * @date 2024/7/3 13:59
 */
@Deprecated
public class IntegerConverterFactory implements ConverterFactory<Integer, BaseEnum> {

    private static final Map<Class, Converter> CONVERTERS = new HashMap<>();

    /**
     * 获取一个从 Integer 转化为 T 的转换器
     *
     * @param targetType 转换后的类型
     * @return 返回一个转化器
     */
    @Override
    public <T extends BaseEnum> Converter<Integer, T> getConverter(Class<T> targetType) {
        return CONVERTERS.computeIfAbsent(targetType, key -> new StringConverter<>(targetType));
    }


}
