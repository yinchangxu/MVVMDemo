package com.demo.mvvm.viewmodel;

import android.view.View;

import androidx.databinding.ObservableField;

import com.demo.mvvm.repository.MainRepository;
import com.demo.mvvm.view.activity.DemoActivity;
import com.demo.mvvm.view.activity.RecyclerViewActivity;
import com.demo.mvvm.view.activity.RecyclerViewNestActivity;
import com.example.myapplication.base.entity.TitleBarBean;
import com.example.myapplication.base.viewmodel.BaseViewModel;


/**
 * 文件名: MainViewModel
 * 日期: 2023/03/11 15:23
 * 描述: ViewModel
 */
public class MainViewModel extends BaseViewModel<MainRepository> {

    //导航栏bean类
    private TitleBarBean titleBarBean = new TitleBarBean();

    //顶部导航栏
    public ObservableField<TitleBarBean> titleBar = new ObservableField<>(titleBarBean);

    public MainViewModel(MainRepository repository) {
        super(repository);
        //导航栏中心文字
        titleBarBean.tvCenter.set("首页");
        //隐藏左侧返回按钮
        titleBarBean.ivLeftVisibility.set(View.INVISIBLE);

    }


    public void click1() {
        startActivity(DemoActivity.class);
    }

    public void click2() {
        startActivity(RecyclerViewActivity.class);
    }

    public void click3() {
        startActivity(RecyclerViewNestActivity.class);
    }

    @Override
    protected void onCreate() {
        super.onCreate();

    }
}
