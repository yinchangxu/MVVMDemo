package com.example.myapplication.util;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * Json转换工具类
 */
public class JsonUtils {

    /**
     * 将对象准换为json字符串
     * @param object 要序列化的对象
     * @param <T> 对象类型
     * @return Json序列化字符串
     */
    public static <T> String serialize(T object) {
        final Gson gson = new GsonBuilder()
                .disableHtmlEscaping()  //禁止转码
                .setLenient()  //允许超长字符串
                .create();
        return gson.toJson(object);
    }

    /**
     * 将json字符串转换为对象
     * @param json Json字符串
     * @param type 对象类型
     * @param <T> 对象泛型类型
     * @return 对象
     */
    public static <T> T deserialize(@NonNull String json, @NonNull Type type) {
        final Gson gson = new GsonBuilder()
                .disableHtmlEscaping()  //禁止转码
                .setLenient()  //允许超长字符串
                .create();
        return gson.fromJson(json, type);
    }

    /**
     * 将json字符串转换为对象
     * @param json Json字符串
     * @param clz 对象类型
     * @param <T> 对象泛型类型
     * @return 对象
     */
    public static <T> T deserialize(@NonNull String json, @NonNull Class<T> clz) {
        final Gson gson = new GsonBuilder()
                .disableHtmlEscaping()  //禁止转码
                .setLenient()  //允许超长字符串
                .create();
        return gson.fromJson(json, clz);
    }

}
