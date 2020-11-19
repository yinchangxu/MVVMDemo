package com.example.myapplication.base.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.base.viewmodel.BaseViewModel;


/**
 * 文件名: MvvmActivity
 * 作者: yin
 * 日期: 2020/4/16 23:42
 * 描述: MVVM架构Activity基类
 */
public abstract class MvvmActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity {

    /**
     * DataBinding
     */
    protected V mDataBinding;
    /**
     * ViewModel
     */
    protected VM mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAAC();
    }

    /**
     * 获取视图ID
     *
     * @return 视图ID
     */
    protected abstract int getLayoutId();

    /**
     * 获取ViewModel
     *
     * @return ViewModel
     */
    protected abstract VM getViewModel();

    /**
     * 初始化 DataBinding 变量ID 和 变量实体类 Map
     *
     * @return DataBinding 变量SparseArray
     */
    protected abstract SparseArray<Object> getVariableSparseArray();

    /**
     * 初始化AAC组件
     */
    private void initAAC() {
        mDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        mViewModel = getViewModel();
        final SparseArray<Object> variableSA = getVariableSparseArray();
        if (variableSA != null && variableSA.size() > 0) {
            for (int i = 0; i < variableSA.size(); ++i) {
                final int variableId = variableSA.keyAt(i);
                final Object variableValue = variableSA.valueAt(i);
                mDataBinding.setVariable(variableId, variableValue);
            }
        }
        //让ViewModel拥有View的生命周期感应
        if (mViewModel != null) {
            getLifecycle().addObserver(mViewModel);
        }
    }

//    /**
//     * 创建ViewModel
//     *
//     * @param cls Activity Class
//     * @return ViewModel子类
//     */
//    public VM createViewModel(@NonNull Class<VM> cls) {
//        return new ViewModelProvider(this).get(cls);
//    }


    /**
     * 工厂模式创建ViewModel
     *
     * @param cls     Activity Class
     * @param factory 工厂
     * @return ViewModel子类
     */
    public VM createViewModel(@NonNull Class<VM> cls, @NonNull ViewModelProvider.NewInstanceFactory factory) {
        return new ViewModelProvider(this, factory).get(cls);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁DataBinding
        if (mDataBinding != null) {
            mDataBinding.unbind();
        }
    }

}
