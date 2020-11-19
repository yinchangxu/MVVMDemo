package com.demo.mvvm.entity;

import com.example.myapplication.base.entity.BaseEntity;
import com.example.myapplication.base.util.CastUtil;
import com.google.gson.annotations.SerializedName;

public class ResultEntity<T> extends BaseEntity {


    /**
     *{
     *     "success":true,
     *     "data":{
     *         "username":"123",
     *         "password":"123"
     *     },
     *     "code":200
     * }
     */

    /**
     * {
     *     "success":true,
     *     "data":[
     *         {
     *             "username":"123",
     *             "password":"123"
     *         },
     *         {
     *             "username":"123",
     *             "password":"123"
     *         }
     *     ],
     *     "code":200
     * }
     */

    @SerializedName("code")
    public int code;
    @SerializedName("msg")
    public String msg;
    @SerializedName("data")
    public T data;

    public T getData() {
        return CastUtil.cast(data);
    }

}
