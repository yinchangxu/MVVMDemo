package com.example.myapplication.util.interceptor;

import android.text.TextUtils;



import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Function: 改变BaseUrl OkHttp拦截器
 * Author: ShiJingFeng
 * Date: 2019/10/12 13:33
 * Description:
 */
public class ChangeBaseUrlInterceptor implements Interceptor {

    @NotNull
    @Override
    public Response intercept(@NotNull Interceptor.Chain chain) throws IOException {
        //获取request
        Request request = chain.request();
        //从request中获取原有的HttpUrl实例oldHttpUrl
        HttpUrl oldHttpUrl = request.url();
        //获取request的创建者builder
        Request.Builder builder = request.newBuilder();
        //从request中获取headers，通过给定的键url_name
        String baseUrlHeader = request.header("baseUrl");

//        String baseUrl;

//        if (!TextUtils.isEmpty(baseUrlHeader)) {
//            switch (baseUrlHeader) {
//                default:
//                    baseUrl = NetworkConstant.COMPANY_SERVICE_BASE_URL;
//                    break;
//            }
//        }
        //TODO 待完善
        return chain.proceed(request);
    }
}
