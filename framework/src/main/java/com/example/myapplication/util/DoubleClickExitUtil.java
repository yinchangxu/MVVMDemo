package com.example.myapplication.util;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.blankj.utilcode.util.ActivityUtils;

/**
 * Function: 双击退出Util
 * Author: ShiJingFeng
 * Date: 2019/11/14 14:21
 * Description:
 */
public class DoubleClickExitUtil {

    /** 退出应用 */
    private static boolean mExitApp = false;

    public static void execute(Context context) {
        if (mExitApp) {
            //关闭所有的Activity
            ActivityUtils.finishAllActivities();
        } else {
            mExitApp = true;
            Toast.makeText(context,"再按一次退出应用",Toast.LENGTH_SHORT).show();
            //超过两秒没有再次点击，则不退出App
            new Handler().postDelayed(() -> mExitApp = false, 2000);
        }
    }

}
