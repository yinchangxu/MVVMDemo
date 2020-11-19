package com.example.myapplication.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import androidx.annotation.NonNull;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * Function: Base64工具类
 * Author: ShiJingFeng
 * Date: 2019/10/9 14:11
 * Description:
 */
public class Base64Util {

//    public static byte[] encode(@NonNull byte[] byteArray) {
//        return Base64.encode(byteArray, Base64.DEFAULT);
//    }

    /**
     * Base64加密
     * @param byteArray 原字节数组数据
     * @return 加密后的字节数组数据
     */
    public static String encode(@NonNull byte[] byteArray) {
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    /**
     * 将图片转换为Base64格式字符串
     * @param bitmap bitmap
     * @return Base64格式字符串
     */
    public static String encode(@NonNull Bitmap bitmap) {
        return encode(bitmap, 100);
    }

    /**
     * 将图片转换为Base64格式字符串 (指定图片质量)
     * @param bitmap bitmap
     * @param quality 图片质量
     * @return Base64格式字符串
     */
    public static String encode(@NonNull Bitmap bitmap, int quality) {
        byte[] byteArray;

        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, byteArrayOutputStream);
            byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    /**
     * 根据本地图片路径 转为 Base64格式字符串
     * @param localFilePath 本地文件路径
     * @return Base64格式字符串
     */
    public static String encode(@NonNull String localFilePath) {
        return encode(localFilePath, 100);
    }

    /**
     * 根据本地图片路径 转为 Base64格式字符串 (指定图片质量)
     * @param localFilePath 本地文件路径
     * @param quality 图片质量
     * @return Base64格式字符串
     */
    public static String encode(@NonNull String localFilePath, int quality) {
        final File localFile = new File(localFilePath);

        if (localFile.exists()) {
            final Bitmap bitmap = BitmapFactory.decodeFile(localFilePath);

            return encode(bitmap, quality);
        } else {
            return "";
        }
    }

    /**
     * 将Base64格式字符串转换为位图
     * @param encodeString 图片的Base64格式字符串
     * @return 位图
     */
    public static byte[] decode(@NonNull String encodeString) {
        return Base64.decode(encodeString, Base64.DEFAULT);
    }
}
