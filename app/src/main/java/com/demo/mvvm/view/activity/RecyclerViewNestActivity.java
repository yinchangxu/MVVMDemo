package com.demo.mvvm.view.activity;


import android.util.SparseArray;

import com.demo.mvvm.BR;
import com.demo.mvvm.R;
import com.demo.mvvm.databinding.ActivityRecyclerViewNestBinding;
import com.demo.mvvm.viewmodel.RecyclerViewNestViewModel;
import com.demo.mvvm.viewmodel.factory.RecyclerViewNestModelFactory;
import com.example.myapplication.base.activity.AppBaseActivity;

public class RecyclerViewNestActivity extends AppBaseActivity<ActivityRecyclerViewNestBinding, RecyclerViewNestViewModel> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recycler_view_nest;
    }

    @Override
    protected RecyclerViewNestViewModel getViewModel() {
        return createViewModel(RecyclerViewNestViewModel.class, RecyclerViewNestModelFactory.create());
    }

    @Override
    protected SparseArray<Object> getVariableSparseArray() {
        SparseArray<Object> variableSA = new SparseArray<>();
        variableSA.put(BR.recyclerViewNestViewModel, mViewModel);
        return variableSA;
    }

}