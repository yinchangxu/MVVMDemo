package com.example.myapplication.util;

import androidx.annotation.NonNull;

import java.lang.reflect.Field;

/**
 * Function: 资源工具类
 * Author: ShiJingFeng
 * Date: 2019/11/25 21:05
 * Description:
 */
public class ResourceUtil {

    /**
     * 通过发射获取资源ID
     * @param variableName 资源ID 名称
     * @param cls 资源ID 类型
     * @return 资源ID
     */
    public static int getResId(@NonNull String variableName, @NonNull Class<?> cls) {
        try {
            final Field idField = cls.getDeclaredField(variableName);

            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
