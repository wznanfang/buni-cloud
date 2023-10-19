package com.buni.buniframework.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 将long型数据转为string
 *
 * @author zp.wei
 * @date 2023/9/27 13:38
 */
public class StringSerializer extends JsonSerializer<Long> {


    /**
     * 将long型数据转为string
     *
     * @param value       long型数据
     * @param gen         JsonGenerator
     * @param serializers SerializerProvider
     * @throws IOException IOException
     */
    @Override
    public void serialize(Long value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value != null) {
            gen.writeString(String.valueOf(value));
        }
    }

}
