package com.example.myapplication.util.interceptor;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * Function: 自定义OkHttp拦截器
 * Author: ShiJingFeng
 * Date: 2019/10/10 9:17
 * Description:
 */
public class CustomInterceptor implements Interceptor {

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {

        Request request = chain.request();
        RequestBody requestBody = request.body();
        if (requestBody instanceof FormBody) {
            //表单请求
            FormBody formBody = (FormBody) requestBody;
            StringBuilder strBuilder = new StringBuilder();

//            for (int i = 0; i < formBody.size(); ++i) {
//                String encodeName = formBody.encodedName(i);
//                String encodeValue = formBody.encodedValue(i);
//                String name = formBody.name(i);
//                String value = formBody.value(i);
//                strBuilder.append(name + "=" + value + "\n");
//                strBuilder.append(" encodeName: " + encodeName + "  encodeValue: " + encodeValue + "  name: " + name + "  value: " + value);
//            }
            System.out.println("请求头: " + formBody.contentType());
            System.out.println("请求体: " + strBuilder.toString());
            System.out.println("url:" + request.url());
        } else if (requestBody instanceof MultipartBody) {
            //文件上传请求
            MultipartBody multipartBody = (MultipartBody) requestBody;
        }

        Response response = chain.proceed(request);
        ResponseBody responseBody = response.body();
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.getBuffer();
        Charset UTF8 = Charset.forName("UTF-8");

        System.out.println("返回数据: " + buffer.clone().readString(UTF8));

        return response;
    }

    /**
     * 打印请求消息
     *
     * @param request 请求的对象
     */
    private String getRequestInfo(Request request) {
        String str = "";
        if (request == null) {
            return str;
        }
        RequestBody requestBody = request.body();
        if (requestBody == null) {
            return str;
        }
        try {
            Buffer bufferedSink = new Buffer();
            requestBody.writeTo(bufferedSink);
            Charset charset = Charset.forName("utf-8");
            str = bufferedSink.readString(charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 打印返回消息
     *
     * @param response 返回的对象
     */
    private String getResponseInfo(Response response) {
        String str = "";
        if (response == null || !response.isSuccessful()) {
            return str;
        }
        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();
        BufferedSource source = responseBody.source();
        try {
            source.request(Long.MAX_VALUE); // Buffer the entire body.
        } catch (IOException e) {
            e.printStackTrace();
        }
        Buffer buffer = source.buffer();
        Charset charset = Charset.forName("utf-8");
        if (contentLength != 0) {
            str = buffer.clone().readString(charset);
        }
        return str;
    }
}
