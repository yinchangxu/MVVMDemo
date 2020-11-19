package com.example.myapplication.base.repository;

import androidx.annotation.NonNull;

import com.example.myapplication.base.source.BaseLocalSource;
import com.example.myapplication.base.source.BaseNetworkSource;
import com.example.myapplication.base.util.CastUtil;

/**
 * 文件名: BaseRepository
 * 作者: yin
 * 日期: 2020/4/16 23:42
 * 描述: 网络仓库基类
 */
public abstract class BaseRepository {

    protected BaseLocalSource mLocalSource;
    protected BaseNetworkSource mNetworkSource;

    /**
     * 设置网络源
     *
     * @param networkSource 网络源
     * @param <T>           泛型
     * @return Repository
     */
    public final <T extends BaseRepository> T setNetworkSource(@NonNull BaseNetworkSource networkSource) {
        this.mNetworkSource = networkSource;
        return CastUtil.cast(this);
    }

    /**
     * 设置本地源
     *
     * @param localSource 本地源
     * @param <T>         泛型
     * @return Repository
     */
    public final <T extends BaseRepository> T setLocalSource(@NonNull BaseLocalSource localSource) {
        this.mLocalSource = localSource;
        return CastUtil.cast(this);
    }

    /**
     * 获取子类类型实例
     *
     * @param <T> 泛型
     * @return 子类类型实例
     */
    public final <T extends BaseRepository> T get() {
        return CastUtil.cast(this);
    }

    /**
     * 销毁回调
     */
    public void onCleared() {
        if (mLocalSource != null) {
            mLocalSource.onCleared();
        }
        if (mNetworkSource != null) {
            mNetworkSource.onCleared();
        }
    }

}
