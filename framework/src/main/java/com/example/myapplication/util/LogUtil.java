package com.example.myapplication.util;

import android.util.Log;

import androidx.annotation.NonNull;

/**
 * Function: 日志工具类
 * Author: ShiJingFeng
 * Date: 2019/11/30 23:20
 * Description:
 */
public class LogUtil {

    private static boolean sEnable = true;

    /**
     * 是否开启日志
     * @param enable true 开启  false 关闭
     */
    public static void enable(boolean enable) {
        sEnable = enable;
    }

    /**
     * 日志级别：verbose
     * @param tag 日志标签
     * @param msg 日志内容
     */
    public static void v(@NonNull String tag, @NonNull String msg) {
        if (sEnable) {
            Log.v(tag, msg);
        }
    }

    /**
     * 日志级别：info
     * @param tag 日志标签
     * @param msg 日志内容
     */
    public static void i(@NonNull String tag, @NonNull String msg) {
        if (sEnable) {
            Log.i(tag, msg);
        }
    }

    /**
     * 日志级别：debug
     * @param tag 日志标签
     * @param msg 日志内容
     */
    public static void d(@NonNull String tag, @NonNull String msg) {
        if (sEnable) {
            Log.d(tag, msg);
        }
    }

    /**
     * 日志级别：warn
     * @param tag 日志标签
     * @param msg 日志内容
     */
    public static void w(@NonNull String tag, @NonNull String msg) {
        if (sEnable) {
            Log.w(tag, msg);
        }
    }

    /**
     * 日志级别：error
     * @param tag 日志标签
     * @param msg 日志内容
     */
    public static void e(@NonNull String tag, @NonNull String msg) {
        if (sEnable) {
            Log.e(tag, msg);
        }
    }

}
