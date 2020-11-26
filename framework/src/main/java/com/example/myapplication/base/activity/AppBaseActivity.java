package com.example.myapplication.base.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.ViewDataBinding;

import com.blankj.utilcode.util.ActivityUtils;
import com.example.myapplication.R;
import com.example.myapplication.annotation.NeedPermissions;
import com.example.myapplication.base.viewmodel.BaseViewModel;
import com.example.myapplication.util.LogUtil;
import com.example.myapplication.util.ResourceUtil;
import com.example.myapplication.util.StatusBarUtil;


/**
 * 文件名: AppBaseActivity
 * 作者: yin
 * 日期: 2020/4/16 23:42
 * 描述: Activity基类
 */
@NeedPermissions
public abstract class AppBaseActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends MvvmBaseActivity<V, VM> {

    protected boolean statusBarHeight = false;

    protected boolean statusBarDark = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d("页面", this.getClass().getSimpleName());
    }

    @Override
    protected void onStart() {
        super.onStart();
        initStatusBar();
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        super.initData();
        //Activity默认背景为白色
        getContentView().setBackgroundResource(R.color._FFFFFF);
        //设置状态栏高度
        if (!isSetCustomStatusBarHeight()) {
            @IdRes final int id = ResourceUtil.getResId("rl_layout_title_bar", R.id.class);
            if (id != -1) {
                final View view = findViewById(id);
                if (view != null) {
                    view.setPadding(0, StatusBarUtil.getStatusBarHeight(this), 0, 0);
                    view.setMinimumHeight(view.getHeight() + StatusBarUtil.getStatusBarHeight(this));
                }
            }
        }
    }

    /**
     * 初始化状态栏
     */
    private void initStatusBar() {
        //设置默认状态栏背景颜色 为透明色
        StatusBarUtil.setStatusBarColor(this, R.color._00000000);
        //设置默认状态栏字体颜色 为浅色
        StatusBarUtil.setStatusBarContentColor(this, isSetStatusBarContentDark());
    }

    /**
     * 是否自定义设置状态栏高度
     *
     * @return true 自定义设置  false 默认设置
     */
    protected boolean isSetCustomStatusBarHeight() {
        return statusBarHeight;
    }

    /**
     * 是否设置状态栏内容颜色为深色
     *
     * @return true 深色  false 浅色
     */
    protected boolean isSetStatusBarContentDark() {
        return statusBarDark;
    }


    /**
     * 连续双击退出应用
     */
    protected boolean mExitApp;

    /**
     * 指定间隔时间内 按下两次返回键退出App
     */
    public void doubleDownExitApp() {
        if (mExitApp) {
            //关闭所有的Activity
            ActivityUtils.finishAllActivities();
        } else {
            mExitApp = true;
            Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
            //超过两秒没有再次点击，则不退出App
            new Handler().postDelayed(() -> mExitApp = false, 2000);
        }
    }

    protected void openPhotoWithPermission() {
        addDisposable(
                mRxPermissions
                        .requestEachCombined(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .subscribe(permission -> {
                            if (permission.granted) {
                                openPhoto();
                            } else if (permission.shouldShowRequestPermissionRationale) {
                                Toast.makeText(this, "外部存储权限授予失败", Toast.LENGTH_SHORT).show();
                            } else {
                                new AlertDialog.Builder(this)
                                        .setMessage("需要您去设置页面，「权限管理」，开启「外部存储」权限")
                                        .setPositiveButton("去设置", (DialogInterface dialog, int which) -> {
                                            Intent settingIntent = new Intent();

                                            settingIntent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                            settingIntent.setData(Uri.fromParts("package", getPackageName(), null));
                                            settingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                                            startActivity(settingIntent);
                                        })
                                        .setOnDismissListener(null)
                                        .setCancelable(false)
                                        .show();
                            }
                        })
        );
    }

}
