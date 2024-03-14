package com.buni.buniframework.util;

import java.util.Random;

/**
 * 随机数工具类
 *
 * @author zp.wei
 * @date 2021/12/10 13:08
 */
public class RandomUtil {

    /**
     * 随机字符数量
     */
    private static final int SIZE = 6;

    /**
     * 随机字符集
     */
    private static final char[] CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
            'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};


    /**
     * 生成随机数
     *
     * @return
     */
    public static String random() {
        String randomStr = "";
        Random random = new Random();
        for (int i = 0; i < SIZE; i++) {
            // 取随机字符索引
            int n = random.nextInt(CHARS.length);
            randomStr = randomStr.concat(String.valueOf(CHARS[n]));
        }
        return randomStr;
    }


}
