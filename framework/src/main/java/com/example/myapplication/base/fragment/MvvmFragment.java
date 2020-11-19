package com.example.myapplication.base.fragment;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.base.viewmodel.BaseViewModel;

/**
 * 文件名: MvvmFragment
 * 作者: yin
 * 日期: 2020/4/16 23:42
 * 描述: MVVM架构Fragment基类
 */
public abstract class MvvmFragment<V extends ViewDataBinding, VM extends BaseViewModel> extends Fragment {

    private String tag;

    /**
     * DataBinding
     */
    protected V mDataBinding;
    /**
     * ViewModel
     */
    protected VM mViewModel;

    protected View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        initAAC();
        initParam();
        initView(view);
        initData();
        initAction();
        view = mDataBinding.getRoot();
        return view;
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

    protected abstract void initView(View view);

    protected abstract void initData();

    protected abstract void initAction();

    protected abstract void initParam();

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
    public void onDestroy() {
        super.onDestroy();
        //销毁DataBinding
        if (mDataBinding != null) {
            mDataBinding.unbind();
        }
    }

    public void setFragmentTag(String tag) {
        this.tag = tag;
    }

    public String getFragmentTag() {
        return tag;
    }


    /**
     * 模拟 Activity key down 事件
     *
     * @param keyCode key down code
     * @param event   key down event
     * @return 是否消费 true 消费 false 不消费
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

}
