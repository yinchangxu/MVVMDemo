//package com.example.myapplication.widget.callback;
//
//import android.animation.ObjectAnimator;
//import android.animation.ValueAnimator;
//import android.content.Context;
//import android.view.View;
//import android.view.animation.LinearInterpolator;
//
//import com.blankj.utilcode.util.LogUtils;
//import com.example.myapplication.R;
//import com.kingja.loadsir.callback.Callback;
//
///**
// * Function:
// * Author: ShiJingFeng
// * Date: 2019/11/11 10:26
// * Description:
// */
//public class LoadingCallback extends Callback {
//
//    private ObjectAnimator mAnimator;
//
//    @Override
//    protected int onCreateView() {
//        return R.layout.callback_loading;
//    }
//
//    @Override
//    protected void onViewCreate(Context context, View view) {
//        super.onViewCreate(context, view);
//        LogUtils.log(LogUtils.E, "测试", "LoadingCallback onViewCreate()");
//        startAnimator(view.findViewById(R.id.iv_callback_loading_img));
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        LogUtils.log(LogUtils.E, "测试", "LoadingCallback onDetach()");
//        destroyAnimator();
//    }
//
//    /**
//     * 开始旋转动画
//     */
//    private void startAnimator(View view) {
//        mAnimator = ObjectAnimator.ofFloat(view, "rotation", 0, 360);
//        mAnimator.setInterpolator(new LinearInterpolator());
//        mAnimator.setDuration(1000);
//        mAnimator.setRepeatCount(ValueAnimator.INFINITE);
//        mAnimator.start();
//    }
//
//    /**
//     * 销毁旋转动画
//     */
//    private void destroyAnimator() {
//        if (mAnimator != null) {
//            mAnimator.cancel();
//            mAnimator = null;
//        }
//    }
//}
