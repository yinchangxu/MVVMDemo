package com.example.myapplication.util;

import androidx.annotation.NonNull;

import java.util.Map;

/**
 * Function: 字符操作工具类
 * Author: ShiJingFeng
 * Date: 2019/10/6 15:27
 * Description:
 */
public class CharacterUtil {

    /**
     * 向URL字符串中添加参数
     * @param url
     * @param key
     * @param value
     * @return
     */
    public static String addUrlParam(@NonNull String url, @NonNull String key, @NonNull String value) {
        String newUrl;

        if (url.contains("?")) {
            newUrl = url + "&"+ key + "=" + value;
        } else {
            newUrl = url + "?" + key + "=" + value;
        }

        return newUrl;
    }

    /**
     * 向URL字符串中添加多个参数
     * @param url
     * @param paramMap
     * @return
     */
    public static String addUrlParam(@NonNull String url, @NonNull Map<String, Object> paramMap) {
        StringBuilder newUrlBuilder = new StringBuilder(url);

        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            final String key = entry.getKey();
            final Object value = entry.getValue();

            if (newUrlBuilder.toString().contains("?")) {
                newUrlBuilder.append("&");
                newUrlBuilder.append(key);
                newUrlBuilder.append("=");
                newUrlBuilder.append(value);
            } else {
                newUrlBuilder.append("?");
                newUrlBuilder.append(key);
                newUrlBuilder.append("=");
                newUrlBuilder.append(value);
            }
        }

        return newUrlBuilder.toString();
    }
}
