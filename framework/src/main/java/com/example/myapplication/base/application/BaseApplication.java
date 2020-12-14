package com.example.myapplication.base.application;

import android.app.Application;
import android.content.Context;


import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;
import com.example.myapplication.constant.NetworkURL;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

/**
 * 文件名: AppBaseActivity
 * 作者: yin
 * 日期: 2020/4/16 23:42
 * 描述: 工程总Application
 */
public abstract class BaseApplication extends Application {

    protected static Application sInstance;

    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((Context context, RefreshLayout layout) -> {
            //全局设置主题颜色
            layout.setPrimaryColorsId(android.R.color.white, android.R.color.black);
            //.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            return new ClassicsHeader(context);
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((Context context, RefreshLayout layout) -> {
            //指定为经典Footer，默认是 BallPulseFooter
            return new ClassicsFooter(context).setDrawableSize(20);
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        NetworkURL.SERVER_URL = initBaseUrl();
        init();
    }

    protected abstract String initBaseUrl();

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        //初始化MultiDex
//        MultiDex.install(this);
    }

    /**
     * 初始化
     */
    private void init() {
        initUtils();
    }


    /**
     * 初始化万能工具类
     */
    private void initUtils() {
        //初始化万能工具类 (神器)
        Utils.init(this);
        //万能工具类 -> 日志相关
        LogUtils.getConfig()
                .setLogSwitch(true);  //设置 Log 总开关
    }

    /**
     * 获取 Application;
     *
     * @return Application
     */
    public static Application getInstance() {
        if (sInstance == null) {
            throw new NullPointerException("Application还没有初始化");
        }
        return sInstance;
    }


}
