package com.demo.mvvm.network.api;

import com.demo.mvvm.entity.DemoEntity;
import com.demo.mvvm.entity.ResultEntity;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 文件名: ServerApi
 * 作者: yin
 * 日期: 2020/11/19 9:17
 * 描述: 网络请求回调接口
 */
public interface ServerApi {


    /**
     * data为对象的写法
     *
     *
     *
     *           "success":true,
     *           "data":{
     *               "username":"123",
     *               "password":"123"
     *           },
     *           "code":200
     *      }
     *
     *
     * @param postMap 数据
     * @return Single
     */
    @FormUrlEncoded
    @POST("api/demo/demo")
    Single<ResultEntity<DemoEntity>> demo1(@FieldMap Map<String, Object> postMap);

    /**
     * data为数组的写法
     *
     * @param postMap 数据
     * @return Single
     */
    @FormUrlEncoded
    @POST("api/demo/demo")
    Single<ResultEntity<List<DemoEntity>>> demo2(@FieldMap Map<String, Object> postMap);

}
