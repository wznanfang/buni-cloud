package com.buni.framework.config.enumsconverter;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zp.wei
 * @date 2024/7/3 14:03
 */
@Configuration
public class EnumsConverterConfig implements WebMvcConfigurer {


    /**
     * 枚举类的转换器工厂 addConverterFactory
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new IntegerConverterFactory());
        registry.addConverterFactory(new StringConverterFactory());
    }

}
