package com.example.myapplication.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.ColorRes;

/**
 * function:  状态栏工具类
 * date: 2019/7/3
 */
public class StatusBarUtil {

    /**
     * 设置 状态栏背景 颜色
     *
     * @param color 颜色资源
     */
    public static void setStatusBarColor(Activity activity, @ColorRes int color) {
        final Window window = activity.getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(color));
        }
    }

    /**
     * 设置 状态栏内容 颜色
     *
     * @param dark 深色 或 浅色   true: 深色  false: 浅色
     */
    @TargetApi(Build.VERSION_CODES.M)
    public static void setStatusBarContentColor(Activity activity, final boolean dark) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            final View decor = activity.getWindow().getDecorView();
            final int flag = decor.getSystemUiVisibility();

            if (dark) {
                if ((View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR) != flag) {
                    decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                }
            } else {
                if ((View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE) != flag) {
                    decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                }
            }
        }
    }

    /**
     * 获取状态栏高度
     *
     * @param context Context
     * @return 高度
     */
    public static int getStatusBarHeight(Context context) {
        int result = 36;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        Log.e("tag", "status_bar_height=" + result);
        return result;
    }

}
