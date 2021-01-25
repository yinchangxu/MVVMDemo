package com.demo.mvvm.network.interfaces;

import androidx.annotation.NonNull;

import com.demo.mvvm.entity.DemoEntity;
import com.demo.mvvm.entity.ResultEntity;
import com.example.myapplication.listener.OnResultListener;

import java.util.List;
import java.util.Map;

/**
 * 文件名: IDemoNetworkSource
 * 日期: 2021/01/25 11:18
 * 描述: 网络数据操作接口
 */
public interface IDemoNetworkSource {


    /**
     * 解析data对象
     *
     * @param position url数据集中的url坐标
     * @param postMap  数据
     * @param listener 回调监听
     */
    void demo1(int position, @NonNull Map<String, Object> postMap, @NonNull OnResultListener<ResultEntity<DemoEntity>, String> listener);

    /**
     * 解析data数组
     *
     * @param position url数据集中的url坐标
     * @param postMap  数据
     * @param listener 回调监听
     */
    void demo2(int position, @NonNull Map<String, Object> postMap, @NonNull OnResultListener<ResultEntity<List<DemoEntity>>, String> listener);

}
