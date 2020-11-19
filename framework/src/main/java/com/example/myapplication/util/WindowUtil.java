package com.example.myapplication.util;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

/**
 * Window 工具类
 */
public class WindowUtil {

    /**
     * 设置窗口外部背景透明度
     */
    public static void setWindowOutsideBackground(@NonNull Activity activity, float bgAlpha) {
        Window window = activity.getWindow();

        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        WindowManager.LayoutParams params = window.getAttributes();
        params.alpha = bgAlpha;

        window.setAttributes(params);
    }

}
