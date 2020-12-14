package com.demo.mvvm;

import com.example.myapplication.base.application.BaseApplication;

public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    //服务器地址
    @Override
    protected String initBaseUrl() {
        return "http://www.xxx.com";
    }

}
