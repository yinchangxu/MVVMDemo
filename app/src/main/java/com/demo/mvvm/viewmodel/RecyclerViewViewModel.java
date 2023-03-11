package com.demo.mvvm.viewmodel;

import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.demo.mvvm.BR;
import com.demo.mvvm.R;
import com.demo.mvvm.entity.RecyclerViewEntity;
import com.demo.mvvm.entity.ResultEntity;
import com.demo.mvvm.repository.RecyclerViewRepository;
import com.example.myapplication.base.entity.TitleBarBean;
import com.example.myapplication.base.viewmodel.BaseViewModel;
import com.example.myapplication.listener.OnResultListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.tatarka.bindingcollectionadapter2.ItemBinding;


/**
 * 文件名: RecyclerViewViewModel
 * 日期: 2023/03/11 15:23
 * 描述: ViewModel
 */
public class RecyclerViewViewModel extends BaseViewModel<RecyclerViewRepository> {

    //导航栏bean类
    private TitleBarBean titleBarBean = new TitleBarBean();

    //顶部导航栏
    public ObservableField<TitleBarBean> titleBar = new ObservableField<>(titleBarBean);

    public RecyclerViewViewModel(RecyclerViewRepository repository) {
        super(repository);
        //导航栏中心文字
        titleBarBean.tvCenter.set("RecyclerView");
        //导航栏左侧图标点击事件
        titleBarBean.onBack(new TitleBarBean.OnItemEventListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
    }

    //recycleview
    public final ObservableList<RecyclerViewEntity> items = new ObservableArrayList<>();
    public final ItemBinding<RecyclerViewEntity> itemBinding = ItemBinding.of(BR.recyclerViewEntity, R.layout.item);

    public OnLoadMoreListener loadMoreListener = new OnLoadMoreListener() {
        @Override
        public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            showLoadingDialog("加载中...");
            //假数据
            for (int i = 0; i < 5; i++) {
                RecyclerViewEntity recyclerViewEntity = new RecyclerViewEntity();
                recyclerViewEntity.username = String.valueOf(i);
                recyclerViewEntity.id = String.valueOf(i);
                recyclerViewEntity.nickname = String.valueOf(i);
                recyclerViewEntity.password = String.valueOf(i);
                items.add(recyclerViewEntity);
            }
            hideLoadingDialog();
            refreshLayout.finishLoadMore();

        }
    };

    public OnRefreshListener onRefreshListener = new OnRefreshListener() {
        @Override
        public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            items.clear();
            showLoadingDialog("加载中...");
            //假数据
            for (int i = 0; i < 30; i++) {
                RecyclerViewEntity recyclerViewEntity = new RecyclerViewEntity();
                recyclerViewEntity.username = String.valueOf(i);
                recyclerViewEntity.id = String.valueOf(i);
                recyclerViewEntity.nickname = String.valueOf(i);
                recyclerViewEntity.password = String.valueOf(i);
                items.add(recyclerViewEntity);
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    hideLoadingDialog();
                }
            }, 1000);
            refreshLayout.finishRefresh();

        }
    };

    @Override
    protected void onCreate() {
        super.onCreate();
        showLoadingDialog("123");
        //假数据
        for (int i = 0; i < 30; i++) {
            RecyclerViewEntity recyclerViewEntity = new RecyclerViewEntity();
            recyclerViewEntity.username = String.valueOf(i);
            recyclerViewEntity.id = String.valueOf(i);
            recyclerViewEntity.nickname = String.valueOf(i);
            recyclerViewEntity.password = String.valueOf(i);
            items.add(recyclerViewEntity);
        }
        hideLoadingDialog();


        //返回json中data数据为{}
        Map<String, Object> postMap1 = new HashMap<>();
        postMap1.put("username", "123");
        postMap1.put("password", "123");
        mRepository.demo1(1, postMap1, new OnResultListener<ResultEntity<RecyclerViewEntity>, String>() {
            @Override
            public void onSuccess(ResultEntity<RecyclerViewEntity> data) {
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
        mRepository.demo2(1, postMap2, new OnResultListener<ResultEntity<List<RecyclerViewEntity>>, String>() {
            @Override
            public void onSuccess(ResultEntity<List<RecyclerViewEntity>> data) {
                super.onSuccess(data);
            }

            @Override
            public void onFailure(String data, Throwable e) {
                super.onFailure(data, e);
            }
        });

    }
}
