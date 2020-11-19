package com.example.myapplication.util;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.EncryptUtils;

import java.nio.charset.StandardCharsets;

/**
 * Function: 安全 工具类
 * Author: ShiJingFeng
 * Date: 2019/10/10 13:17
 * Description:
 */
public class SecurityUtil {

    private static final byte[] sKeyByteArray = "w7kqxCbW13IN3LG9".getBytes();
    private static final String sTransformation = "AES/CBC/PKCS5Padding";
    private static final byte[] sIvByteArray = "FEE7qJlMkRYiXzkB".getBytes();

    /**
     * 加密数据
     * @param str 要加密的数据
     */
    public static String encrypt(@NonNull String str) {
        final byte[] encryptByteArray = EncryptUtils.encryptAES2Base64(str.getBytes(StandardCharsets.UTF_8), sKeyByteArray, sTransformation, sIvByteArray);

        return new String(encryptByteArray, StandardCharsets.UTF_8);
    }

    /**
     * 解密数据
     * @param str 要解密的数据
     */
    public static String decrypt(@NonNull String str) {
        final byte[] decryptByteArray = EncryptUtils.decryptBase64AES(str.getBytes(StandardCharsets.UTF_8), sKeyByteArray, sTransformation, sIvByteArray);

        return new String(decryptByteArray, StandardCharsets.UTF_8);
    }

}
