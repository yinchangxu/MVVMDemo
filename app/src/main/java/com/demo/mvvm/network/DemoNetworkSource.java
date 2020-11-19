package com.demo.mvvm.network;

import androidx.annotation.NonNull;

import com.demo.mvvm.constants.NetworkConstant;
import com.demo.mvvm.entity.DemoEntity;
import com.demo.mvvm.entity.ResultEntity;
import com.demo.mvvm.network.api.ServerApi;
import com.demo.mvvm.network.interfaces.IDemoNetworkSource;
import com.example.myapplication.base.source.BaseNetworkSource;
import com.example.myapplication.listener.OnResultListener;
import com.example.myapplication.util.RetrofitUtil;

import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 文件名: DemoNetworkSource
 * 日期: 2020/11/19 9:36
 * 描述: 网络数据操作实现类
 */
public class DemoNetworkSource extends BaseNetworkSource implements IDemoNetworkSource {

    private ServerApi mServerApi;

    private DemoNetworkSource() {
        this.mServerApi = RetrofitUtil.getInstance().create(ServerApi.class);
    }

    public static DemoNetworkSource create() {
        return new DemoNetworkSource();
    }

    @Override
    public void demo1(@NonNull Map<String, Object> postMap, @NonNull OnResultListener<ResultEntity<DemoEntity>, String> listener) {
        mServerApi
                .demo1(postMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((ResultEntity<DemoEntity> result) -> {
                    if (result.code == NetworkConstant.SUCCESS) {
                        listener.onSuccess(result);
                    } else {
                        listener.onFailure(result.msg, null);
                    }
                }, (Throwable throwable) -> {
                    listener.onFailure("请求失败", throwable);
                });
    }

    @Override
    public void demo2(@NonNull Map<String, Object> postMap, @NonNull OnResultListener<ResultEntity<List<DemoEntity>>, String> listener) {
        mServerApi
                .demo2(postMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((ResultEntity<List<DemoEntity>> result) -> {
                    if (result.code == NetworkConstant.SUCCESS) {
                        listener.onSuccess(result);
                    } else {
                        listener.onFailure(result.msg, null);
                    }
                }, (Throwable throwable) -> {
                    listener.onFailure("请求失败", throwable);
                });
    }
}
