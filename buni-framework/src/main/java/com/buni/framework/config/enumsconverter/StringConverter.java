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
     * 建立映射
     *
     * @param enumType
     */
    public StringConverter(Class<T> enumType) {
        T[] enums = enumType.getEnumConstants();
        for (T e : enums) {
            // 按 code 建立映射
            enumMap.put(e.getCode().toString(), e);
            // 按枚举名建立映射（忽略大小写）
            enumMap.put(e.name().toLowerCase(), e);
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
        if (ObjectUtil.isEmpty(source)) {
            return null;
        }
        // 先尝试 code 匹配
        T t = enumMap.get(source);
        if (ObjectUtil.isNull(t)) {
            // 尝试枚举名匹配（忽略大小写）
            t = enumMap.get(source.toLowerCase());
        }
        if (ObjectUtil.isNull(t)) {
            log.error("枚举转换失败: {}", source);
        }
        return t;
    }

}
