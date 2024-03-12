package com.buni.buniframework.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.TreeMap;

/**
 * 字典码排序
 *
 * @author zp.wei
 * @date 2021/6/28 12:08
 */

public class AsciiUtil {

    /**
     * 获取ASCII字典码拼接字符串
     *
     * @param params
     * @return
     */
    private String getAscii(Map<String, String> params) {
        Map<String, String> map = new TreeMap<>();
        map.putAll(params);
        //以key1 = value1&key2 = value2拼接参数
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> s : map.entrySet()) {
            String key = s.getKey();
            String value = s.getValue();
            //过滤空值
            if (StringUtils.isBlank(value)) {
                continue;
            }
            //拼接
            stringBuilder.append(key).append("=").append(value).append("&");
        }
        if (!map.isEmpty()) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }


}
