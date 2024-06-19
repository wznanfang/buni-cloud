package com.buni.framework.config.desensitization;

/**
 * 数据脱敏类型
 *
 * @author zp.wei
 * @date 2024/4/28 16:36
 */
public enum DesensitizationType {

    // 自定义规则
    CUSTOMIZE_RULE,
    // 默认的
    DEFAULT,
    // 姓名
    CHINESE_NAME,
    // 身份证号
    ID_CARD,
    // 手机号
    MOBILE_PHONE,
    // 座机
    FIXED_PHONE,
    // 地址
    ADDRESS,
    // 电子邮件
    EMAIL,
    // 密码
    PASSWORD,
    // 银行卡
    BANK_CARD,
    // 中国大陆车牌
    CAR_LICENSE,
    // ipv4
    IPV4,
    // ipv6
    IPV6,
    ;


}
