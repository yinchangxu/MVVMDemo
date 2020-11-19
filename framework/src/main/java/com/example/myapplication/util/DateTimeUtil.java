package com.example.myapplication.util;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Function: 日期时间工具类
 * Author: ShiJingFeng
 * Date: 2019/10/8 15:25
 * Description:
 */
public class DateTimeUtil {

    /**
     * 将时间毫秒值 转换为 指定格式的字符串
     * @param millisecond 时间毫秒值
     * @param pattern 格式
     * @return 指定格式的字符串
     */
    public static String getFormativeDateTimeStr(long millisecond, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.CHINESE);
        Date date = new Date(millisecond);
        return simpleDateFormat.format(date);
    }

    /**
     * 将时间毫秒值 转换为 默认格式的字符串
     * @param millisecond 时间毫秒值
     * @return 默认格式的字符串
     */
    public static String getDefaultDateStr(long millisecond) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE);
        Date date = new Date(millisecond);
        return simpleDateFormat.format(date);
    }

    /**
     * 将年月日转换为时间毫秒值
     * @param dateTime 日期时间字符串
     * @param pattern 模式
     * @return 毫秒值
     */
    public static long getMillisecondFromDateTime(@NonNull String dateTime, @NonNull String pattern) {
        if (TextUtils.isEmpty(dateTime)) {
            return 0;
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            return format.parse(dateTime).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
