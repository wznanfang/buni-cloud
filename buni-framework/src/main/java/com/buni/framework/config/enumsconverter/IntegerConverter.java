package com.buni.framework.config.enumsconverter;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Integer类型无需转换
 *
 * @author zp.wei
 * @date 2024/7/3 13:53
 */
@Slf4j
@Deprecated
public class IntegerConverter<T extends BaseEnum> implements Converter<Integer, T> {

    private Map<Integer, T> enumMap = new ConcurrentHashMap<>();


    /**
     * 构造方法
     *
     * @param enumType
     */
    public IntegerConverter(Class<T> enumType) {
        T[] enums = enumType.getEnumConstants();
        for (T e : enums) {
            enumMap.put(e.getCode(), e);
        }
    }


    /**
     * 转换
     *
     * @param source
     * @return
     */
    @Override
    public T convert(Integer source) {
        T t = enumMap.get(source);
        if (ObjectUtil.isNull(t)) {
            log.error("枚举转换异常:{}", source);
            return null;
        }
        return t;
    }

}
