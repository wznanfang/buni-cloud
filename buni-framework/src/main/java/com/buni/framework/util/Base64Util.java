package com.buni.framework.util;

import org.apache.commons.codec.binary.Base64;

/**
 * Base64工具类
 *
 * @author zp.wei
 */
public class Base64Util {

    /**
     * 编码
     *
     * @param str 要编码的字符串
     * @return string
     */
    public static String encode(String str) {
        return Base64.encodeBase64String(str.getBytes());
    }


    /**
     * @param str str
     * @return byte
     */
    public static byte[] encodeStr(String str) {
        return Base64.encodeBase64(str.getBytes());
    }

    /**
     * 编码
     *
     * @param bytes byte
     * @return string
     */
    public static String encode(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

    /**
     * 编码
     *
     * @param bytes byte
     * @return 返回byte
     */
    public static byte[] encodeByte(byte[] bytes) {
        return Base64.encodeBase64(bytes);
    }


    /**
     * 解码
     *
     * @param str 要解码的字符串
     * @return string
     */
    public static String decode(String str) {
        return new String(Base64.decodeBase64(str));
    }


    /**
     * @param str str
     * @return byte
     */
    public static byte[] decodeStr(String str) {
        return Base64.decodeBase64(str);
    }


    /**
     * 解码
     *
     * @param bytes byte
     * @return string
     */
    public static String decode(byte[] bytes) {
        return new String(Base64.decodeBase64(bytes));
    }


    /**
     * 解码
     *
     * @param bytes byte
     * @return byte
     */
    public static byte[] decodeByte(byte[] bytes) {
        return Base64.decodeBase64(bytes);
    }


}
