package com.example.myapplication.util;

import android.webkit.WebSettings;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.NetworkUtils;

/**
 * Function: WebView工具类
 * Author: ShiJingFeng
 * Date: 2019/10/7 9:35
 * Description:
 */
public class WebViewUtil {

    /**
     * 设置默认 WebView设置
     * @param webSettings WebView设置
     */
    public static void setDefaultWebSettings(@NonNull WebSettings webSettings) {
        //支持js交互
        webSettings.setJavaScriptEnabled(true);
        //支持通过JS打开新窗口
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        //设置字符编码
        webSettings.setDefaultTextEncodingName("utf-8");
        //提高渲染的优先级
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        //将图片加载放在最后来加载渲染
//        webSettings.setBlockNetworkImage(true);
        //支持缩放，适配到当前屏幕
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(false);
        //不显示WebView缩放按钮
        webSettings.setDisplayZoomControls(false);
        webSettings.setLoadsImagesAutomatically(true);
        //设置此属性，可任意比例缩放
        webSettings.setUseWideViewPort(true);
        //设置WebView为全屏显示
        webSettings.setLoadWithOverviewMode(true);
        if (NetworkUtils.isConnected()) {
            //网络可用时，不使用缓存
            webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        } else {
            //网络不可用时，设置优先从缓存中获取数据
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
    }

}
