package com.demo.mvvm.repository;

import androidx.annotation.NonNull;

import com.demo.mvvm.entity.DemoEntity;
import com.demo.mvvm.entity.ResultEntity;
import com.demo.mvvm.network.interfaces.IDemoNetworkSource;
import com.example.myapplication.base.repository.BaseRepository;
import com.example.myapplication.listener.OnResultListener;

import java.util.List;
import java.util.Map;


/**
 * 文件名: BaseRepository
 * 日期: 2020/11/19 9:48
 * 描述: Demo网络仓库
 */

public class DemoRepository extends BaseRepository implements IDemoNetworkSource {


    private DemoRepository() {
    }

    public static DemoRepository create() {
        return new DemoRepository();
    }

    @Override
    public void demo1(int position, @NonNull Map<String, Object> postMap, @NonNull OnResultListener<ResultEntity<DemoEntity>, String> listener) {
        if (mNetworkSource != null) {
            IDemoNetworkSource demoNetworkSource = mNetworkSource.get();
            demoNetworkSource.demo1(position, postMap, listener);
        } else {
            throw new IllegalArgumentException("网络源不能为空");
        }
    }

    @Override
    public void demo2(int position, @NonNull Map<String, Object> postMap, @NonNull OnResultListener<ResultEntity<List<DemoEntity>>, String> listener) {
        if (mNetworkSource != null) {
            IDemoNetworkSource demoNetworkSource = mNetworkSource.get();
            demoNetworkSource.demo2(position, postMap, listener);
        } else {
            throw new IllegalArgumentException("网络源不能为空");
        }
    }
}
