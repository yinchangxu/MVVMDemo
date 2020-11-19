package com.demo.mvvm.viewmodel;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.demo.mvvm.BR;
import com.demo.mvvm.R;
import com.demo.mvvm.entity.DemoEntity;
import com.demo.mvvm.entity.ResultEntity;
import com.demo.mvvm.repository.DemoRepository;
import com.example.myapplication.base.entity.TitleBarBean;
import com.example.myapplication.base.viewmodel.BaseViewModel;
import com.example.myapplication.listener.OnResultListener;

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

    private TitleBarBean titleBarBean = new TitleBarBean();

    public ObservableField<TitleBarBean> titleBar = new ObservableField<>(titleBarBean);

    //文本
    public ObservableField<String> text = new ObservableField<>("啦啦啦");

    //图片
    public ObservableField<Object> image = new ObservableField<>(R.mipmap.ic_launcher);

    //recycleview
    public final ObservableList<DemoEntity> items = new ObservableArrayList<>();
    public final ItemBinding<DemoEntity> itemBinding = ItemBinding.of(BR.demoEntity, R.layout.item);

    public DemoViewModel(DemoRepository repository) {
        super(repository);
        titleBarBean.tvCenter.set("示例");
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

        for (int i = 0; i < 5; i++) {
            DemoEntity demoEntity = new DemoEntity();
            demoEntity.username = String.valueOf(i);
            demoEntity.id = String.valueOf(i);
            demoEntity.nickname = String.valueOf(i);
            demoEntity.password = String.valueOf(i);
            items.add(demoEntity);
        }

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
