package com.demo.mvvm;

import com.example.myapplication.base.application.BaseApplication;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * 设置服务器地址集合
     *
     * @return 服务器地址集
     */
    @Override
    protected List<String> initBaseUrl() {
        List<String> urlList = new ArrayList<>();
        urlList.add("http://www.xxx.com");
        urlList.add("http://www.yyy.com");
        return urlList;
    }

}
