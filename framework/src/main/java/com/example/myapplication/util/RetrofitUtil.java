package com.example.myapplication.util;

import com.blankj.utilcode.util.ToastUtils;
import com.example.myapplication.constant.NetworkURL;
import com.example.myapplication.util.factory.CustomGsonConverterFactory;
import com.example.myapplication.util.interceptor.CustomInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


/**
 * Retrofit工具类
 */
public class RetrofitUtil {

//    private Retrofit sRetrofit;

    public List<Retrofit> mRetrofitList;

    private RetrofitUtil() {
//        sRetrofit = initRetrofit();
        mRetrofitList = initRetrofits();
    }

    private static class Inner {
        private static final RetrofitUtil INSTANCE = new RetrofitUtil();
    }

    /**
     * 获取 静态内部类单例
     *
     * @return 静态内部类单例
     */
    public static RetrofitUtil getInstance() {
        return Inner.INSTANCE;
    }

    /**
     * 创建 Retrofit 实例
     *
     * @return Retrofit实例
     */
    private List<Retrofit> initRetrofits() {

        final OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                //网络数据查看拦截器
                .addInterceptor(new CustomInterceptor());

        final Gson gson = new GsonBuilder()
                .disableHtmlEscaping() //禁止转码
                .setLenient()  //允许超长字符串
                .create();

        List<Retrofit> retrofits = new ArrayList<>();
        for (int i = 0; i < NetworkURL.mUrlList.size(); i++) {
            retrofits.add(new Retrofit.Builder()
                    .baseUrl(NetworkURL.mUrlList.get(i))
                    .client(builder.build())
                    .addConverterFactory(CustomGsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build());
        }
        return retrofits;
    }

    /**
     * 创建 Api 请求类
     *
     * @param api Api请求原始反射类
     * @param <T> 泛型
     * @return Api请求类
     */
    public <T> T create(int position, final Class<T> api) {
        try {
            return mRetrofitList.get(position).create(api);
        } catch (IndexOutOfBoundsException e) {
            ToastUtils.showShort("url配置不正确或pos设置不正确");
        }
        return null;
    }
}