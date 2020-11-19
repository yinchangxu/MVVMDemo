package com.example.myapplication.base.source;


import com.example.myapplication.base.util.CastUtil;

/**
 * 文件名: BaseNetworkSource
 * 作者: yin
 * 日期: 2020/4/16 23:42
 * 描述: 网络资源基类
 */
public abstract class BaseNetworkSource {

    /**
     * 获取子类类型实例
     *
     * @param <T> 泛型
     * @return 子类类型实例
     */
    public <T extends BaseNetworkSource> T get() {
        return CastUtil.cast(this);
    }

    /**
     * 销毁回调
     */
    public void onCleared() {
    }
}
