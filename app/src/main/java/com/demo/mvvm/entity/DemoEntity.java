package com.demo.mvvm.entity;

import com.example.myapplication.base.entity.BaseEntity;
import com.google.gson.annotations.SerializedName;

public class DemoEntity extends BaseEntity {

    @SerializedName("id")
    public String id;
    @SerializedName("username")
    public String username;
    @SerializedName("nickname")
    public String nickname;
    //图片url
    @SerializedName("avatar")
    public Object avatar;
    @SerializedName("password")
    public String password;

}
