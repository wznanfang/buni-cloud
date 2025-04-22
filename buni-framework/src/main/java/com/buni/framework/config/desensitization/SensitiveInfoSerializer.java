package com.buni.framework.config.desensitization;

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
 * 脱敏
 *
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
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) {
        if (property != null && property.getAnnotation(Desensitization.class) != null) {
            Desensitization desensitization = property.getAnnotation(Desensitization.class);
            this.type = desensitization.type();
            this.prefixLen = desensitization.prefixLen();
            this.suffixLen = desensitization.suffixLen();
            useMasking = true;
        }
        return this;
    }


    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (useMasking && value != null) {
            String desensitizedValue = switch (type) {
                case CUSTOMIZE_RULE -> StrUtil.hide(value, prefixLen, suffixLen);
                case CHINESE_NAME -> DesensitizedUtil.chineseName(value);
                case ID_CARD -> DesensitizedUtil.idCardNum(value, 2, 2);
                case MOBILE_PHONE -> DesensitizedUtil.mobilePhone(value);
                case FIXED_PHONE -> DesensitizedUtil.fixedPhone(value);
                case ADDRESS -> DesensitizedUtil.address(value, 10);
                case EMAIL -> DesensitizedUtil.email(value);
                case PASSWORD -> DesensitizedUtil.password(value);
                case BANK_CARD -> DesensitizedUtil.bankCard(value);
                case CAR_LICENSE -> DesensitizedUtil.carLicense(value);
                case IPV4 -> DesensitizedUtil.ipv4(value);
                case IPV6 -> DesensitizedUtil.ipv6(value);
                default -> DesensitizedUtil.firstMask(value);
            };
            gen.writeString(desensitizedValue);
        } else {
            gen.writeObject(value);
        }
    }


}

