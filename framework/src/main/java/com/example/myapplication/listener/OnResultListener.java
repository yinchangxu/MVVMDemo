package com.example.myapplication.listener;

/**
 * function: 结果监听器
 * date: 2019/6/3
 */
public abstract class OnResultListener<S, F> {

    /**
     * 成功 回调
     * @param data 结果
     */
    public void onSuccess(S data) {}

    /**
     * 失败 回调
     * @param data 错误信息
     * @param e 错误异常
     */
    public void onFailure(F data, Throwable e) {}

}
