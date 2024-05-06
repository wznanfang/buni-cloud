package com.buni.buniframework.config.desensitization;

import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author zp.wei
 * @date 2024/4/28 16:37
 */
@Component
public class SensitiveInfoSerializer extends JsonSerializer<String> implements ContextualSerializer {

    private boolean useMasking = false;
    private DesensitizationType type;
    private int prefixLen;
    private int suffixLen;


    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (useMasking && value != null) {
            switch (type) {
                case CUSTOMIZE_RULE:
                    gen.writeString(StrUtil.hide(value, prefixLen, suffixLen));
                    break;
                case ID_CARD:
                    gen.writeString(DesensitizedUtil.idCardNum(value, prefixLen, suffixLen));
                    break;
                case MOBILE_PHONE:
                    gen.writeString(DesensitizedUtil.mobilePhone(value));
                    break;
                case ADDRESS:
                    gen.writeString(DesensitizedUtil.address(value, suffixLen));
                    break;
                case EMAIL:
                    gen.writeString(DesensitizedUtil.email(value));
                    break;
                default:
                    gen.writeString(DesensitizedUtil.firstMask(value));
            }
        } else {
            gen.writeObject(value);
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) {
        if (property != null) {
            Desensitization desensitization = property.getAnnotation(Desensitization.class);
            if (desensitization != null) {
                this.type = desensitization.type();
                this.prefixLen = desensitization.prefixLen();
                this.suffixLen = desensitization.suffixLen();
                useMasking = true;
            }
        }
        return this;
    }
}

