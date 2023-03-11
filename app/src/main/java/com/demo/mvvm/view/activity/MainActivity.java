package com.demo.mvvm.view.activity;

import android.util.SparseArray;

import com.demo.mvvm.BR;
import com.demo.mvvm.R;
import com.demo.mvvm.databinding.ActivityMainBinding;
import com.demo.mvvm.viewmodel.MainViewModel;
import com.demo.mvvm.viewmodel.factory.MainModelFactory;
import com.example.myapplication.base.activity.AppBaseActivity;

public class MainActivity extends AppBaseActivity<ActivityMainBinding, MainViewModel> {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainViewModel getViewModel() {
        return createViewModel(MainViewModel.class, MainModelFactory.create());
    }

    @Override
    protected SparseArray<Object> getVariableSparseArray() {
        SparseArray<Object> variableSA = new SparseArray<>();
        variableSA.put(BR.mainViewModel, mViewModel);
        return variableSA;
    }
}