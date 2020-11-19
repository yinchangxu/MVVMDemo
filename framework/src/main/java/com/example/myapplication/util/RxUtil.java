package com.example.myapplication.util;

import android.view.View;

import com.jakewharton.rxbinding3.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;
import kotlin.Unit;

/**
 * Function: View防止连击工具类
 * Author: ShiJingFeng
 * Date: 2019/11/14 13:46
 * Description:
 */
public class RxUtil {

    /**
     * 连击间隔2秒
     * @param activity BaseActivity
     * @param view View
     * @param onNext Consumer<Unit>
     */
//    public static void throttleClick(BaseActivity activity, View view, Consumer<Unit> onNext) {
//        activity.addDisposable(RxView.clicks(view)
//                .throttleFirst(2, TimeUnit.SECONDS)
//                .subscribe(onNext)
//        );
//    }

    /**
     * 连击间隔2秒
     * @param activity BaseActivity
     * @param view View
     * @param ms 间隔毫秒值
     * @param onNext Consumer<Unit>
     */
//    public static void throttleClick(BaseActivity activity, View view, int ms, Consumer<Unit> onNext) {
//        activity.addDisposable(RxView.clicks(view)
//                .throttleFirst(ms, TimeUnit.MILLISECONDS)
//                .subscribe(onNext)
//        );
//    }

}
