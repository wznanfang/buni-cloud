package com.buni.framework.util;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import com.buni.framework.constant.CommonConstant;

public class EncryptUtil {

    public static String encrypt(String data) {
        AES aes = getAes();
        return aes.encryptBase64(data);
    }

    private static AES getAes() {
        return new AES(Mode.CTS, Padding.PKCS5Padding, CommonConstant.AES_KEY.getBytes(), CommonConstant.AES_IV.getBytes());
    }

    public static String decrypt(String data) {
        AES aes = getAes();
        return aes.decryptStr(data);
    }


}
