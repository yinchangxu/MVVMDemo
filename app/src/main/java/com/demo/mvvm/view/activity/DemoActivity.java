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
}