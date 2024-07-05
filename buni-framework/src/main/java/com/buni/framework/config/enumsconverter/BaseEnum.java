package com.buni.framework.config.enumsconverter;

/**
 * @author zp.wei
 * @date 2024/7/3 13:50
 */
public interface BaseEnum {


    /**
     * 枚举值
     *
     * @return
     */
    Integer getCode();

    /**
     * 枚举描述
     *
     * @return
     */
    String getValue();

}
