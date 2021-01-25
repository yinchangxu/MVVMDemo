package com.demo.mvvm.view.activity;

import android.util.SparseArray;

import com.demo.mvvm.BR;
import com.demo.mvvm.R;
import com.demo.mvvm.databinding.ActivityDemoBinding;
import com.demo.mvvm.viewmodel.DemoViewModel;
import com.demo.mvvm.viewmodel.factory.DemoModelFactory;
import com.example.myapplication.base.activity.AppBaseActivity;

/**
 * 文件名: DemoActivity
 * 日期: 2020/11/19 10:17
 * 描述: Activity
 */
public class DemoActivity extends AppBaseActivity<ActivityDemoBinding, DemoViewModel> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_demo;
    }

    @Override
    protected DemoViewModel getViewModel() {
        return createViewModel(DemoViewModel.class, DemoModelFactory.create());
    }

    @Override
    protected SparseArray<Object> getVariableSparseArray() {
        SparseArray<Object> variableSA = new SparseArray<>();
        variableSA.put(BR.demoViewModel, mViewModel);
        return variableSA;
    }

    @Override
    protected void initData() {
        super.initData();
    }

    /**
     * 设置是否自定义导航栏设置状态栏高度(默认true)
     *
     * @return true:导航栏留出状态栏高度 false: 不留状态栏高度
     */
    @Override
    protected boolean isSetCustomStatusBarHeight() {
        return true;
    }

    /**
     * 设置状态栏内容颜色(默认false)
     *
     * @return true:深色 false:浅色
     */
    @Override
    protected boolean getStatusBarTextStyle() {
        return true;
    }

    /**
     * 设置状态栏背景色(默认透明）
     *
     * @return 颜色id
     */
    @Override
    protected int getStatusBarBackgroundStyle() {
        return R.color._00000000;
    }

}