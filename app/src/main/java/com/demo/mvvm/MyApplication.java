package com.demo.mvvm;

import android.content.Context;

import com.example.myapplication.base.application.BaseApplication;

public class MyApplication extends BaseApplication {

    private static MyApplication context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    //服务器地址
    @Override
    protected String initBaseUrl() {
        return "http://www.xxx.com";
    }

}
