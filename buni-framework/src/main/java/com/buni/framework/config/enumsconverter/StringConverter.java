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
public class StringConverter<T extends BaseEnum> implements Converter<String, T> {

    private Map<String, T> enumMap = new ConcurrentHashMap<>();


    /**
     * 构造方法
     *
     * @param enumType
     */
    public StringConverter(Class<T> enumType) {
        T[] enums = enumType.getEnumConstants();
        for (T e : enums) {
            enumMap.put(e.getCode().toString(), e);
        }
    }


    /**
     * 转换
     *
     * @param source
     * @return
     */
    @Override
    public T convert(String source) {
        T t = enumMap.get(source);
        if (ObjectUtil.isNull(t)) {
            log.error("枚举转换异常:{}", source);
            return null;
        }
        return t;
    }

}
