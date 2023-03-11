package com.demo.mvvm.viewmodel;

import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.blankj.utilcode.util.ToastUtils;
import com.demo.mvvm.R;
import com.demo.mvvm.repository.DemoRepository;
import com.example.myapplication.base.entity.TitleBarBean;
import com.example.myapplication.base.viewmodel.BaseViewModel;

/**
 * 文件名: DemoViewModel
 * 日期: 2020/11/19 10:17
 * 描述: ViewModel
 */
public class DemoViewModel extends BaseViewModel<DemoRepository> {

    //导航栏bean类
    private TitleBarBean titleBarBean = new TitleBarBean();

    //顶部导航栏
    public ObservableField<TitleBarBean> titleBar = new ObservableField<>(titleBarBean);

    //文本文字
    public ObservableField<String> text = new ObservableField<>("啦啦啦");

    //文字颜色
    public ObservableInt textColor = new ObservableInt(R.color._000000);

    //文本大小
    public ObservableInt textSize = new ObservableInt(14);

    //背景
    public ObservableField<Object> background = new ObservableField<>(R.color._FF7F29);

    //资源图片
    public ObservableField<Object> image1 = new ObservableField<>(R.mipmap.ic_launcher);

    //网络图片
    public ObservableField<Object> image2 = new ObservableField<>("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606381411448&di=ad8e79e78aa1b468829f8641dccade57&imgtype=0&src=http%3A%2F%2F9.pic.9ht.com%2Fup%2F2018-5%2F2018530172841.jpg");

    //按钮点击事件
    public View.OnClickListener click1 = v -> {
        click2();
    };

    public void click2() {
        ToastUtils.showShort("点击");
    }

    //单选
    public ObservableBoolean check1 = new ObservableBoolean(false);

    //多选
    public ObservableBoolean check2 = new ObservableBoolean(false);


    public DemoViewModel(DemoRepository repository) {
        super(repository);
        //导航栏中心文字
        titleBarBean.tvCenter.set("示例");
        //导航栏左侧图标点击事件
        titleBarBean.onBack(new TitleBarBean.OnItemEventListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
    }

}
