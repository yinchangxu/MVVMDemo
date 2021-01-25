package com.example.myapplication.util.interceptor;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
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

//            for (int i = 0; i < formBody.size(); ++i) {
//                String encodeName = formBody.encodedName(i);
//                String encodeValue = formBody.encodedValue(i);
//                String name = formBody.name(i);
//                String value = formBody.value(i);
//                strBuilder.append(name + "=" + value + "\n");
//                strBuilder.append(" encodeName: " + encodeName + "  encodeValue: " + encodeValue + "  name: " + name + "  value: " + value);
//            }
            System.out.println("请求头: " + formBody.contentType());
            System.out.println("请求体: " + "");
            System.out.println("url:" + request.url());
        } else if (requestBody instanceof MultipartBody) {
            //文件上传请求(未实验,不知道好不好使)
            MultipartBody body = (MultipartBody) requestBody;
            Map<String, String> params = new HashMap<>();
            Map<String, String> files = new HashMap<>();
            for (MultipartBody.Part part : body.parts()) {
                RequestBody body1 = part.body();
                Headers headers = part.headers();
                if (headers != null && headers.size() > 0) {
                    String[] split = headers.value(0).replace(" ", "").replace("\"", "").split(";");
                    if (split.length == 2) {
                        //文本
                        String[] keys = split[1].split("=");
                        if (keys.length > 1 && body1.contentLength() < 1024) {
                            String key = keys[1];
                            String value;
                            Buffer buffer = new Buffer();
                            body.writeTo(buffer);
                            value = buffer.readUtf8();
                            params.put(key, value);
                        }
                    } else if (split.length == 3) {
                        //文件
                        String fileKey = "";
                        String fileName = "";
                        String[] keys = split[1].split("=");
                        String[] names = split[2].split("=");
                        if (keys.length > 1) fileKey = keys[1];
                        if (names.length > 1) fileName = names[1];
                        files.put(fileKey, fileName);
                    }
                }

            }
            System.out.println("文本参数-->" + params);
            System.out.println("文件参数-->" + files);
        }

        Response response = chain.proceed(request);
        ResponseBody responseBody = response.body();
        assert responseBody != null;
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.getBuffer();
        Charset UTF8 = StandardCharsets.UTF_8;

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
            Charset charset = StandardCharsets.UTF_8;
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
        assert responseBody != null;
        long contentLength = responseBody.contentLength();
        BufferedSource source = responseBody.source();
        try {
            source.request(Long.MAX_VALUE); // Buffer the entire body.
        } catch (IOException e) {
            e.printStackTrace();
        }
        Buffer buffer = source.buffer();
        Charset charset = StandardCharsets.UTF_8;
        if (contentLength != 0) {
            str = buffer.clone().readString(charset);
        }
        return str;
    }
}
