package com.example.myapplication.base.viewmodel;

import android.net.Uri;
import android.os.Bundle;
import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.annotation.BindEventBus;
import com.example.myapplication.base.repository.BaseRepository;
import com.example.myapplication.livedata.SingleLiveEvent;


import org.greenrobot.eventbus.EventBus;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 文件名: AppBaseActivity
 * 作者: yin
 * 日期: 2020/4/16 23:42
 * 描述: ViewModel基类
 */
public abstract class BaseViewModel<R extends BaseRepository> extends ViewModel implements LifecycleObserver {

    protected R mRepository;
    /**
     * UI改变 LiveData管理器
     */
    private UIChangeLiveDataManager mUiChangeLiveDataManager;
    /**
     * Disposable容器
     */
    private CompositeDisposable mCompositeDisposable;
    /**
     * 连续双击退出应用
     */
//    protected boolean mExitApp;

    public BaseViewModel(R repository) {
        this.mRepository = repository;
        registerEventBus();
    }

    /**
     * 注册EventBus
     */
    private void registerEventBus() {
        //绑定EventBus
        if (this.getClass().isAnnotationPresent(BindEventBus.class)
                && !EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    /**
     * 解注册EventBus
     */
    private void unregisterEventBus() {
        //解绑EventBus
        if (this.getClass().isAnnotationPresent(BindEventBus.class)
                && EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    /**
     * 获取 UI改变 LiveData管理器
     *
     * @return UI改变 LiveData管理器
     */
    public UIChangeLiveDataManager getUiChangeLiveDataManager() {
        if (mUiChangeLiveDataManager == null) {
            mUiChangeLiveDataManager = new UIChangeLiveDataManager();
        }
        return mUiChangeLiveDataManager;
    }

    /**
     * 显示加载中Dialog
     *
     * @param hint 提示文本
     */
    public void showLoadingDialog(@NonNull String hint) {
        getUiChangeLiveDataManager().getShowLoadingDialogEvent().postValue(hint);
    }

    /**
     * 隐藏加载中Dialog
     */
    public void hideLoadingDialog() {
        getUiChangeLiveDataManager().getHideLoadingDialogEvent().call();
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }


    /**
     * 通过Class跳转界面 (有结果返回)
     *
     * @param cls         要跳转到的Activity
     * @param requestCode 请求码
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     *
     * @param cls    要跳转到的Activity
     * @param bundle 携带的数据
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        final SparseArray<Object> paramSA = new SparseArray<>(2);

        paramSA.put(ParamConstant.CLASS, cls);
        if (bundle != null) {
            paramSA.put(ParamConstant.BUNDLE, bundle);
        }

        getUiChangeLiveDataManager().getStartActivityEvent().postValue(paramSA);
    }

    /**
     * 通过String执行动作
     **/
    public void startActivity(String str) {
        startActivity(str, null);
    }

    /**
     * 含有Uri通过String执行动作
     **/
    public void startActivity(String str, Uri uri) {
        final SparseArray<Object> paramSA = new SparseArray<>(2);

        paramSA.put(ParamConstant.ACTION, str);
        if (uri != null) {
            paramSA.put(ParamConstant.DATA, uri);
        }
        getUiChangeLiveDataManager().getStartActionEvent().postValue(paramSA);

    }

    /**
     * 含有Bundle通过Class跳转界面
     *
     * @param cls         要跳转到的Activity
     * @param bundle      要携带的数据
     * @param requestCode 请求码
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        final SparseArray<Object> paramSA = new SparseArray<>(3);

        paramSA.put(ParamConstant.CLASS, cls);
        if (bundle != null) {
            paramSA.put(ParamConstant.BUNDLE, bundle);
        }
        paramSA.put(ParamConstant.REQUEST_CODE, requestCode);

        getUiChangeLiveDataManager().getStartActivityEvent().postValue(paramSA);
    }

    /**
     * 销毁Activity
     */
    public void finish() {
        getUiChangeLiveDataManager().getFinishEvent().call();
    }

    /**
     * 添加Disposable
     *
     * @param disposable Disposable
     */
    public void addDisposable(@NonNull Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    /**
     * 清空Disposable
     */
    private void clearDisposable() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
            mCompositeDisposable = null;
        }
    }

    /**
     * 指定间隔时间内 按下两次返回键退出App
     */
//    public void doubleDownExitApp() {
//        if (mExitApp) {
//            //关闭所有的Activity
//            ActivityUtils.finishAllActivities();
//        } else {
//            mExitApp = true;
////            ToastUtils.showShort(MyApplication.get,"再按一次退出应用");
//            //超过两秒没有再次点击，则不退出App
//            new Handler().postDelayed(() -> mExitApp = false, 2000);
//        }
//    }

    /**
     * ViewModel销毁回调
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        //解注册EventBus
        unregisterEventBus();
        //取消所有异步任务
        clearDisposable();
        //销毁Model
        if (mRepository != null) {
            mRepository.onCleared();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    protected void onAny(LifecycleOwner owner, Lifecycle.Event event) {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    protected void onCreate() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    protected void onStart() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    protected void onResume() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    protected void onPause() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    protected void onStop() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected void onDestroy() {
    }

    /**
     * UI改变 LiveData管理器
     */
    public static class UIChangeLiveDataManager {

        private SingleLiveEvent<String> showLoadingDialogEvent;
        private SingleLiveEvent<Object> hideLoadingDialogEvent;
        private SingleLiveEvent<SparseArray<Object>> startActivityEvent;
        private SingleLiveEvent<Object> finishEvent;
        private SingleLiveEvent<SparseArray<Object>> startActionEvent;

        /**
         * 获取 显示加载中Dialog SingleLiveEvent
         *
         * @return 显示加载中Dialog SingleLiveEvent
         */
        public SingleLiveEvent<String> getShowLoadingDialogEvent() {
            if (showLoadingDialogEvent == null) {
                showLoadingDialogEvent = new SingleLiveEvent<>();
            }
            return showLoadingDialogEvent;
        }

        /**
         * 获取 隐藏加载中Dialog SingleLiveEvent
         *
         * @return 隐藏加载中Dialog SingleLiveEvent
         */
        public SingleLiveEvent<Object> getHideLoadingDialogEvent() {
            if (hideLoadingDialogEvent == null) {
                hideLoadingDialogEvent = new SingleLiveEvent<>();
            }
            return hideLoadingDialogEvent;
        }

        /**
         * 获取 跳转Activity SingleLiveEvent
         *
         * @return 跳转Activity SingleLiveEvent
         */
        public SingleLiveEvent<SparseArray<Object>> getStartActivityEvent() {
            if (startActivityEvent == null) {
                startActivityEvent = new SingleLiveEvent<>();
            }
            return startActivityEvent;
        }

        /**
         * 获取 关闭销毁Activity SingleLiveEvent
         *
         * @return 关闭销毁Activity SingleLiveEvent
         */
        public SingleLiveEvent<Object> getFinishEvent() {
            if (finishEvent == null) {
                finishEvent = new SingleLiveEvent<>();
            }
            return finishEvent;
        }

        /**
         * 执行 动作Action SingleLiveEvent
         *
         * @return 跳转Activity SingleLiveEvent
         */
        public SingleLiveEvent<SparseArray<Object>> getStartActionEvent() {
            if (startActionEvent == null) {
                startActionEvent = new SingleLiveEvent<>();
            }
            return startActionEvent;
        }

    }

    /**
     * 静态常量
     */
    public static class ParamConstant {

        /**
         * Class
         */
        public static final int CLASS = 1;
        /**
         * Bundle数据
         */
        public static final int BUNDLE = 2;
        /**
         * 请求码
         */
        public static final int REQUEST_CODE = 3;
        /**
         * Activity Class 全限定名称
         */
        public static final int CANONICAL_NAME = 4;
        /**
         * Bundle数据
         */
        public static final int ACTION = 5;
        /**
         * Bundle数据
         */
        public static final int DATA = 6;

    }

}
