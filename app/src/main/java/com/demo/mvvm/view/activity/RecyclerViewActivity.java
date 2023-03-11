package com.demo.mvvm.view.activity;

import android.util.SparseArray;

import com.demo.mvvm.BR;
import com.demo.mvvm.R;
import com.demo.mvvm.databinding.ActivityRecyclerViewBinding;
import com.demo.mvvm.viewmodel.RecyclerViewViewModel;
import com.demo.mvvm.viewmodel.factory.RecyclerViewModelFactory;
import com.example.myapplication.base.activity.AppBaseActivity;

public class RecyclerViewActivity extends AppBaseActivity<ActivityRecyclerViewBinding, RecyclerViewViewModel> {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_recycler_view;
    }

    @Override
    protected RecyclerViewViewModel getViewModel() {
        return createViewModel(RecyclerViewViewModel.class, RecyclerViewModelFactory.create());
    }

    @Override
    protected SparseArray<Object> getVariableSparseArray() {
        SparseArray<Object> variableSA = new SparseArray<>();
        variableSA.put(BR.recyclerViewViewModel, mViewModel);
        return variableSA;
    }
}