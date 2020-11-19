package com.example.myapplication.widget.dialog;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;

import com.example.myapplication.util.WindowUtil;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Function: 自定义 提示 PopupWindow
 * Author: ShiJingFeng
 * Date: 2019/11/5 10:32
 * Description:
 */
public class CommonDialog {

    private final Attr mAttr;
    private final Listener mListener;
    private final View mParentView;
    private PopupWindow mCommonPw;

    private CommonDialog(Attr attr, Listener listener) {
        this.mAttr = attr;
        this.mListener = listener;
        this.mParentView = this.mAttr.activity.findViewById(android.R.id.content);
    }

    /**
     * 显示 PopupWindow
     */
    public void show() {
        if (mCommonPw != null) {
            if (!mCommonPw.isShowing()) {
                WindowUtil.setWindowOutsideBackground(mAttr.activity, mAttr.windowOutsideAlpha);
                mCommonPw.update();
            }
            return;
        }

        mCommonPw = new PopupWindow();
        mCommonPw.setContentView(mAttr.contentView);
        mCommonPw.setWidth(mAttr.windowWidth);
        mCommonPw.setHeight(mAttr.windowHeight);
        mCommonPw.setOutsideTouchable(mAttr.outsideTouchable);
        mCommonPw.setTouchable(mAttr.touchable);
        mCommonPw.setFocusable(mAttr.focusable);
        mCommonPw.setAnimationStyle(mAttr.animStyle);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mCommonPw.setElevation(mAttr.elevation);
        }
        mCommonPw.setBackgroundDrawable(mAttr.drawable);
        WindowUtil.setWindowOutsideBackground(mAttr.activity, mAttr.windowOutsideAlpha);
        mCommonPw.showAtLocation(mParentView, mAttr.gravity,mAttr.x,mAttr.y);

        mCommonPw.setOnDismissListener(() -> {
            WindowUtil.setWindowOutsideBackground(mAttr.activity, 1F);
            if (mListener.onDismissListener != null) {
                mListener.onDismissListener.onDismiss();
            }
        });
    }

    /**
     * 更新 PopupWindow
     */
    public void update() {
        if (mCommonPw != null) {
            WindowUtil.setWindowOutsideBackground(mAttr.activity, mAttr.windowOutsideAlpha);
            mCommonPw.update();
        }
    }

    /**
     * 隐藏 PopupWindow
     */
    public void hide() {
        if (mCommonPw != null) {
            mCommonPw.dismiss();
        }
    }

    /**
     * 是否正在显示
     * @return true 正在显示  false 没有显示
     */
    public boolean isShowing() {
        if (mCommonPw != null) {
            return mCommonPw.isShowing();
        }
        return false;
    }

    /**
     * 获取 Content View
     * @return Content View
     */
    public View getContentView() {
        if (mCommonPw != null) {
            return mCommonPw.getContentView();
        }
        return null;
    }

    public static class Builder {

        private Attr mAttr = new Attr();
        private Listener mListener = new Listener();

        public Builder(@NonNull Activity activity) {
            mAttr.activity = activity;
        }

        /**
         * 设置 PopupWindow Content View
         * @param contentView PopupWindow Content View
         * @return Builder
         */
        public Builder setContentView(@NonNull View contentView) {
            mAttr.contentView = contentView;
            return this;
        }

        /**
         * 设置 PopupWindow Content View
         * @param layoutId PopupWindow Content View Layout Id
         * @return Builder
         */
        public Builder setContentView(@LayoutRes int layoutId) {
            if (mAttr.activity != null) {
                mAttr.contentView = LayoutInflater.from(mAttr.activity).inflate(layoutId, null);
            }
            return this;
        }

        /**
         * 设置窗口宽度
         * @param windowWidth 窗口宽度
         * @return Builder
         */
        public Builder setWindowWidth(int windowWidth) {
            this.mAttr.windowWidth = windowWidth;
            return this;
        }

        /**
         * 设置窗口高度
         * @param windowHeight 窗口高度
         * @return Builder
         */
        public Builder setWindowHeight(int windowHeight) {
            this.mAttr.windowHeight = windowHeight;
            return this;
        }

        /**
         * 设置外部是否可触摸
         * @param outsideTouchable 外部是否可触摸
         * @return Builder
         */
        public Builder setOutsideTouchable(boolean outsideTouchable) {
            this.mAttr.outsideTouchable = outsideTouchable;
            return this;
        }

        /**
         * 设置是否可触摸
         * @param touchable 是否可触摸
         * @return Builder
         */
        public Builder setTouchable(boolean touchable) {
            this.mAttr.touchable = touchable;
            return this;
        }

        /**
         * 设置是否可聚焦
         * @param focusable 是否可聚焦
         * @return Builder
         */
        public Builder setFocusable(boolean focusable) {
            this.mAttr.focusable = focusable;
            return this;
        }

        /**
         * 设置点击外部是否可取消
         * @param cancelable 点击外部是否可取消
         * @return Builder
         */
        public Builder setCancelable(boolean cancelable) {
            this.mAttr.outsideTouchable = true;
            return this;
        }

        /**
         * 设置阴影宽度
         * @param elevation 阴影宽度
         * @return Builder
         */
        public Builder setElevation(float elevation) {
            this.mAttr.elevation = elevation;
            return this;
        }

        /**
         * 设置背景资源
         * @param drawableRes 背景资源
         * @return Builder
         */
        public Builder setBackgroundDrawable(@DrawableRes int drawableRes) {
            if (mAttr.activity != null) {
                mAttr.drawable = mAttr.activity.getResources().getDrawable(drawableRes);
            }
            return this;
        }

        /**
         * 设置背景Drawable
         * @param drawable 背景Drawable
         * @return Builder
         */
        public Builder setBackgroundDrawable(@NonNull Drawable drawable) {
            this.mAttr.drawable = drawable;
            return this;
        }

        /**
         * 设置外部窗口透明度
         * @param windowOutsideAlpha 外部窗口透明度
         * @return Builder
         */
        public Builder setWindowOutsideAlpha(float windowOutsideAlpha) {
            mAttr.windowOutsideAlpha = windowOutsideAlpha;
            return this;
        }

        /**
         * 设置动画样式
         * @param animStyle 动画样式
         * @return Builder
         */
        public Builder setAnimStyle(@StyleRes int animStyle) {
            mAttr.animStyle = animStyle;
            return this;
        }

        /**
         * 设置 PopupWindow Gravity
         * @param gravity PopupWindow Gravity
         * @return Builder
         */
        public Builder setGravity(int gravity, int x, int y) {
            mAttr.gravity = gravity;
            mAttr.x = x;
            mAttr.y = y;
            return this;
        }

        /**
         * 设置 PopupWindow 取消监听器
         * @param onDismissListener PopupWindow 取消监听器
         * @return Builder
         */
        public Builder setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
            mListener.onDismissListener = onDismissListener;
            return this;
        }

        /**
         * 创建 提示PopupWindow
         * @return 提示PopupWindow
         */
        public CommonDialog create() {
            return new CommonDialog(mAttr, mListener);
        }

        /**
         * 显示 提示PopupWindow
         * @return 提示PopupWindow
         */
        public CommonDialog show() {
            final CommonDialog commonDialog = new CommonDialog(mAttr, mListener);

            commonDialog.show();

            return commonDialog;
        }
    }

    /**
     * 属性数据
     */
    private static class Attr {

        /** Activity环境 */
        private Activity activity;
        /** PopupWindow Content View */
        private View contentView;
        /** 窗口宽度 */
        private int windowWidth = WRAP_CONTENT;
        /** 窗口高度 */
        private int windowHeight = WRAP_CONTENT;
        /** 外部是否可触摸 */
        private boolean outsideTouchable = false;
        /** 是否可触摸 */
        private boolean touchable = true;
        /** 是否可聚焦 */
        private boolean focusable = false;
        /** 阴影宽度 */
        private float elevation = 0F;
        /** 背景Drawable */
        private Drawable drawable;
        /** 外部窗口透明度 */
        private float windowOutsideAlpha = 1F;
        /** 动画样式 */
        private int animStyle = 0;
        /** PopupWindow Gravity */
        private int gravity = Gravity.CENTER;
        /** X轴偏移量 */
        private int x = 0;
        /** Y轴偏移量 */
        private int y = 0;

    }

    /**
     * 监听器
     */
    private static class Listener {

        /** PopupWindow 取消回调 */
        private PopupWindow.OnDismissListener onDismissListener;

    }
}
