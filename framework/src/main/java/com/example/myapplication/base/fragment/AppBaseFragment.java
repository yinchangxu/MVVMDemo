package com.example.myapplication.base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

import com.example.myapplication.R;
import com.example.myapplication.base.viewmodel.BaseViewModel;
import com.example.myapplication.util.LogUtil;
import com.example.myapplication.util.ResourceUtil;
import com.example.myapplication.util.StatusBarUtil;

/**
 * 文件名: AppBaseFragment
 * 作者: yin
 * 日期: 2020/4/16 23:42
 * 描述: Fragment的基类
 */
public abstract class AppBaseFragment<V extends ViewDataBinding, VM extends BaseViewModel> extends MvvmBaseFragment<V, VM> {


    protected boolean statusBarHeight = false;

    protected boolean statusBarDark = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtil.d("页面", this.getClass().getSimpleName());
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        initStatusBar();
    }

    @Override
    protected void initView(View view) {

    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        super.initData();
        //Activity默认背景为白色
        getContentView().setBackgroundResource(R.color._FFFFFF);
        //设置状态栏高度
        if (!isSetCustomStatusBarHeight()) {
            @IdRes final int id = ResourceUtil.getResId("rl_layout_title_bar", R.id.class);
            if (id != -1) {
                final View view = getContentView().findViewById(id);
                if (view != null) {
                    view.setPadding(0, StatusBarUtil.getStatusBarHeight(getContext()), 0, 0);
                    view.setMinimumHeight(view.getHeight() + StatusBarUtil.getStatusBarHeight(getContext()));
                }
            }
        }
    }

    public void setStatusBar(){

    }

    /**
     * 初始化状态栏
     */
    private void initStatusBar() {
        //设置默认状态栏背景颜色 为透明色
        StatusBarUtil.setStatusBarColor(getActivity(), R.color._00000000);
        //设置默认状态栏字体颜色 为浅色
        StatusBarUtil.setStatusBarContentColor(getActivity(), isSetStatusBarContentDark());
    }

    /**
     * 是否自定义设置状态栏高度
     *
     * @return true 自定义设置  false 默认设置
     */
    protected boolean isSetCustomStatusBarHeight() {
        return statusBarHeight;
    }

    /**
     * 是否设置状态栏内容颜色为深色
     *
     * @return true 深色  false 浅色
     */
    protected boolean isSetStatusBarContentDark() {
        return statusBarDark;
    }

}
