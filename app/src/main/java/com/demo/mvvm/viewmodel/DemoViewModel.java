package com.demo.mvvm.viewmodel;

import android.view.View;
import android.widget.Toast;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.databinding.ObservableList;

import com.blankj.utilcode.util.ToastUtils;
import com.demo.mvvm.BR;
import com.demo.mvvm.R;
import com.demo.mvvm.entity.DemoEntity;
import com.demo.mvvm.entity.ResultEntity;
import com.demo.mvvm.repository.DemoRepository;
import com.example.myapplication.base.entity.TitleBarBean;
import com.example.myapplication.base.viewmodel.BaseViewModel;
import com.example.myapplication.listener.OnResultListener;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.callback.ProgressCallback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

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

    //recycleview
    public final ObservableList<DemoEntity> items = new ObservableArrayList<>();
    public final ItemBinding<DemoEntity> itemBinding = ItemBinding.of(BR.demoEntity, R.layout.item);

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

    @Override
    protected void onCreate() {
        super.onCreate();

        LoadService loadService = LoadSir.getDefault().register(this, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                // 重新加载逻辑
            }
        });
        loadService.showCallback(ProgressCallback.class);

        //假数据
        for (int i = 0; i < 5; i++) {
            DemoEntity demoEntity = new DemoEntity();
            demoEntity.username = String.valueOf(i);
            demoEntity.id = String.valueOf(i);
            demoEntity.nickname = String.valueOf(i);
            demoEntity.password = String.valueOf(i);
            items.add(demoEntity);
        }

        //返回json中data数据为{}
        Map<String, Object> postMap1 = new HashMap<>();
        postMap1.put("username", "123");
        postMap1.put("password", "123");
        mRepository.demo1(postMap1, new OnResultListener<ResultEntity<DemoEntity>, String>() {
            @Override
            public void onSuccess(ResultEntity<DemoEntity> data) {
                super.onSuccess(data);
            }

            @Override
            public void onFailure(String data, Throwable e) {
                super.onFailure(data, e);
            }
        });

        //返回json中data数据为[]
        Map<String, Object> postMap2 = new HashMap<>();
        postMap2.put("username", "123");
        postMap2.put("password", "123");
        mRepository.demo2(postMap2, new OnResultListener<ResultEntity<List<DemoEntity>>, String>() {
            @Override
            public void onSuccess(ResultEntity<List<DemoEntity>> data) {
                super.onSuccess(data);
            }

            @Override
            public void onFailure(String data, Throwable e) {
                super.onFailure(data, e);
            }
        });

    }
}
