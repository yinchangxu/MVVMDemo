package com.demo.mvvm.repository;

import androidx.annotation.NonNull;

import com.demo.mvvm.entity.RecyclerViewEntity;
import com.demo.mvvm.entity.ResultEntity;
import com.demo.mvvm.network.interfaces.IRecyclerViewNetworkSource;
import com.example.myapplication.base.repository.BaseRepository;
import com.example.myapplication.listener.OnResultListener;

import java.util.List;
import java.util.Map;


/**
 * 文件名: RecyclerViewRepository
 * 日期: 2023/03/11 15:20
 * 描述: Main网络仓库
 */

public class RecyclerViewRepository extends BaseRepository implements IRecyclerViewNetworkSource {


    private RecyclerViewRepository() {
    }

    public static RecyclerViewRepository create() {
        return new RecyclerViewRepository();
    }

    @Override
    public void demo1(int position, @NonNull Map<String, Object> postMap, @NonNull OnResultListener<ResultEntity<RecyclerViewEntity>, String> listener) {
        if (mNetworkSource != null) {
            IRecyclerViewNetworkSource recyclerViewNetworkSource = mNetworkSource.get();
            recyclerViewNetworkSource.demo1(position, postMap, listener);
        } else {
            throw new IllegalArgumentException("网络源不能为空");
        }
    }

    @Override
    public void demo2(int position, @NonNull Map<String, Object> postMap, @NonNull OnResultListener<ResultEntity<List<RecyclerViewEntity>>, String> listener) {
        if (mNetworkSource != null) {
            IRecyclerViewNetworkSource recyclerViewNetworkSource = mNetworkSource.get();
            recyclerViewNetworkSource.demo2(position, postMap, listener);
        } else {
            throw new IllegalArgumentException("网络源不能为空");
        }
    }

}
