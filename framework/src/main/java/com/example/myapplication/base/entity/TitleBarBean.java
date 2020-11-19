package com.example.myapplication.base.entity;

import android.content.res.Resources;
import android.database.Observable;
import android.graphics.Color;
import android.view.View;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.bumptech.glide.load.engine.Resource;
import com.example.myapplication.R;
import com.example.myapplication.listener.OnItemEventListener;
import com.example.myapplication.util.DisplayMetricsUtil;

/**
 * @作者：Administrator
 * @时间：2020/5/25 11:37
 * @描述：
 **/
public class TitleBarBean {

    public interface OnItemEventListener {
        void onClick();
    }

    public interface OnRightTextClickListener {
        void onClick();
    }

    public interface OnRightLinearClickListener {
        void onClick();
    }

    private OnItemEventListener mOnItemEventListener;

    private OnRightTextClickListener mOnRightTextClickListener;

    private OnRightLinearClickListener mOnRightLinearClickListener;

    public ObservableInt titlebarVisibility = new ObservableInt(View.VISIBLE);

    public ObservableInt titlebarColor = new ObservableInt(R.color._FFFFFF);

    public ObservableInt ivLeft = new ObservableInt(R.mipmap.back);

    public ObservableInt ivLeftColor = new ObservableInt(R.color._000000);

    public ObservableInt tvCenterColor = new ObservableInt(R.color._000000);


    public View.OnClickListener ivLeftOnClick = v -> {
        if (mOnItemEventListener != null) {
            mOnItemEventListener.onClick();
        }
    };

    public View.OnClickListener tvRightOnClick = v -> {
        if (mOnRightTextClickListener != null) {
            mOnRightTextClickListener.onClick();
        }
    };

    public View.OnClickListener llRightOnClick = v -> {
        if (mOnRightLinearClickListener != null) {
            mOnRightLinearClickListener.onClick();
        }
    };

    public ObservableField<String> tvCenter = new ObservableField<>("");

    public ObservableInt tvRightVisibility = new ObservableInt(View.GONE);
    public ObservableField<String> tvRight = new ObservableField<>("");
    public ObservableInt tvRightColor = new ObservableInt(R.color._333333);
    public ObservableInt tvRightTextSize = new ObservableInt(12);

    public ObservableInt ivRightVisibility = new ObservableInt(View.GONE);
    public ObservableInt ivRight = new ObservableInt(R.mipmap.ic_launcher);

    public ObservableInt llRightVisibility = new ObservableInt(View.GONE);
    public ObservableInt llIvRight = new ObservableInt(R.mipmap.ic_launcher);
    public ObservableField<String> llTvRight = new ObservableField<>("");
    public ObservableInt llTvColor = new ObservableInt(R.color._666666);

    public void onBack(OnItemEventListener onItemEventListener) {
        mOnItemEventListener = onItemEventListener;
    }

    public void onRightTextClick(OnRightTextClickListener onRightTextClickListener) {
        mOnRightTextClickListener = onRightTextClickListener;
    }

    public void onRightLinearClick(OnRightLinearClickListener onRightLinearClickListener) {
        mOnRightLinearClickListener = onRightLinearClickListener;
    }

}
