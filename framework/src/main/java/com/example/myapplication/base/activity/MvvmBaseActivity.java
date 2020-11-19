package com.example.myapplication.base.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;
import androidx.databinding.ViewDataBinding;

import com.example.myapplication.annotation.BindEventBus;
import com.example.myapplication.annotation.NeedPermissions;
import com.example.myapplication.base.viewmodel.BaseViewModel;
import com.example.myapplication.doubleclick.AspectTest;
import com.example.myapplication.widget.dialog.LoadingDialog;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static com.example.myapplication.constant.CodeConstant.RESULT_CAMERA;
import static com.example.myapplication.constant.CodeConstant.RESULT_CLIP;
import static com.example.myapplication.constant.CodeConstant.RESULT_PHOTO;

/**
 * 文件名: MvvmBaseActivity
 * 作者: yin
 * 日期: 2020/4/16 23:42
 * 描述: 用于MVVM或AAC模式的 Activity基类
 */

public abstract class MvvmBaseActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends MvvmActivity<V, VM> {

    /**
     * Disposable容器
     */
    private CompositeDisposable mCompositeDisposable;
    /**
     * RxPermission (响应式权限申请框架)
     */
    protected RxPermissions mRxPermissions;
    /**
     * 页面跳转携带的数据Bundle
     */
    protected Bundle mBundle;
    /**
     * 相机Uri
     */
    protected Uri mCameraUri;
    /**
     * 相机图片缓存文件地址
     */
    protected String mCameraTempFilePath;
    /**
     * 裁剪图片缓存文件地址
     */
    protected String mClipTempFilePath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initParam();
        initData();
        initAction();
        initObserver();
        initSave(savedInstanceState);
    }

    /**
     * 初始化参数
     */
    protected void initParam() {
        if (getIntent() != null) {
            mBundle = getIntent().getExtras();
        }
    }

    /**
     * 初始化数据
     */
    protected void initData() {
        //创建RxPermission对象
        if (this.getClass().isAnnotationPresent(NeedPermissions.class)) {
            mRxPermissions = new RxPermissions(this);
        }
        //注册EventBus
        if (this.getClass().isAnnotationPresent(BindEventBus.class)
                && !EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    /**
     * 初始化事件
     */
    protected void initAction() {
    }

    /**
     * 初始化 LiveData Observer
     */
    protected void initObserver() {
        //显示加载中提示对话框
        mViewModel.getUiChangeLiveDataManager().getShowLoadingDialogEvent().observe(this, s -> {
            LoadingDialog.getInstance()
                    .setHintText(s)
                    .show(this);
        });
        //隐藏加载中提示对话框
        mViewModel.getUiChangeLiveDataManager().getHideLoadingDialogEvent().observe(this, o -> {
            LoadingDialog.getInstance().hide();
        });
        //切换Activity
        mViewModel.getUiChangeLiveDataManager().getStartActivityEvent().observe(this, paramSA -> {
            final Class<?> cls = (Class<?>) paramSA.get(BaseViewModel.ParamConstant.CLASS);
            final Bundle bundle = (Bundle) paramSA.get(BaseViewModel.ParamConstant.BUNDLE);
            final Integer requestCode = (Integer) paramSA.get(BaseViewModel.ParamConstant.REQUEST_CODE);

            if (requestCode == null) {
                startActivity(cls, bundle);
            } else {
                startActivityForResult(cls, bundle, requestCode);
            }
        });
        //关闭销毁Activity
        mViewModel.getUiChangeLiveDataManager().getFinishEvent().observe(this, o -> {
            finish();
        });
        mViewModel.getUiChangeLiveDataManager().getStartActionEvent().observe(this, paramSA -> {
            String action = (String) paramSA.get(BaseViewModel.ParamConstant.ACTION);
            Uri uri = (Uri) paramSA.get(BaseViewModel.ParamConstant.DATA);
            startActivity(action, uri);
        });
    }

    protected void initSave(Bundle savedInstanceState) {

    }

    ;

    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
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
        Intent intent = new Intent();
        intent.setAction(str);
        if (uri != null) {
            intent.setData(uri);
        }
        startActivity(intent);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 打开相机
     */
    public void openCamera(String path) {
        final File saveFile = new File(path, "shoot_" + System.currentTimeMillis() + ".jpg");

        mCameraTempFilePath = saveFile.getAbsolutePath();

        final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mCameraUri = FileProvider.getUriForFile(this, getApplicationInfo().processName + ".FileProvider", saveFile);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        } else {
            mCameraUri = Uri.fromFile(saveFile);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mCameraUri);
        startActivityForResult(intent, RESULT_CAMERA);
    }

//    /**
//     * 先检查权限然后打开相机
//     */
//    public void openCameraWithCheckPermission(String path) {
//        addDisposable(
//                mRxPermissions
//                        .requestEachCombined(CAMERA)
//                        .subscribe(permission -> {
//                            if (permission.granted) {
//                                openCamera(path);
//                            } else if (permission.shouldShowRequestPermissionRationale) {
//                                Toast.makeText(this,"相机权限授予失败",Toast.LENGTH_SHORT).show();
//                            } else {
//                                new AlertDialog.Builder(this)
//                                        .setMessage("需要您去设置页面，「权限管理」，开启「相机」权限")
//                                        .setPositiveButton("去设置", (DialogInterface dialog, int which) -> {
//                                            Intent settingIntent = new Intent();
//
//                                            settingIntent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                                            settingIntent.setData(Uri.fromParts("package", getPackageName(), null));
//                                            settingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//                                            startActivity(settingIntent);
//                                        })
//                                        .setOnDismissListener(null)
//                                        .setCancelable(false)
//                                        .show();
//                            }
//                        })
//        );
//    }

    /**
     * 打开相册
     */
    public void openPhoto() {
        Intent intent = new Intent();

        intent.setAction(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");

        startActivityForResult(Intent.createChooser(intent, "选择图片"), RESULT_PHOTO);
    }

//    /**
//     * 先检查权限然后打开相册
//     */
//    public void openPhotoWithCheckPermission() {
//        addDisposable(
//                mRxPermissions
//                        .requestEachCombined(READ_EXTERNAL_STORAGE)
//                        .subscribe(permission -> {
//                            if (permission.granted) {
//                                openPhoto();
//                            } else if (permission.shouldShowRequestPermissionRationale) {
//                                Toast.makeText(this,"外部存储权限授予失败",Toast.LENGTH_SHORT).show();
//                            } else {
//                                new AlertDialog.Builder(this)
//                                        .setMessage("需要您去设置页面，「权限管理」，开启「外部存储」权限")
//                                        .setPositiveButton("去设置", (DialogInterface dialog, int which) -> {
//                                            Intent settingIntent = new Intent();
//
//                                            settingIntent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                                            settingIntent.setData(Uri.fromParts("package", getPackageName(), null));
//                                            settingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//                                            startActivity(settingIntent);
//                                        })
//                                        .setOnDismissListener(null)
//                                        .setCancelable(false)
//                                        .show();
//                            }
//                        })
//        );
//    }

    /**
     * 对图片进行裁剪
     *
     * @param data 原始图片URI
     */
    public void startImageClip(String path, Uri data) {
        final Intent intent = new Intent("com.android.camera.action.CROP");
        final File saveClipImageFile = new File(path, "clip_" + System.currentTimeMillis() + ".jpg");

        mClipTempFilePath = saveClipImageFile.getAbsolutePath();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.setDataAndType(data, "image/*");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(saveClipImageFile));
//        intent.putExtra("aspectX", 1);  // 裁剪框 X轴比例
//        intent.putExtra("aspectY", 1);  //裁剪框 Y轴比例
//        intent.putExtra("outputX", 200);  // 输出图片 X轴大小
//        intent.putExtra("outputY", 200);  //输出图片 Y轴大小
//        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());  //设置图片格式
        intent.putExtra("crop", "true");  //是否剪切
        intent.putExtra("scale", true);   //设置可缩放
        intent.putExtra("scaleUpIfNeeded", true);  // c去黑边
        intent.putExtra("return-data", false);  //禁止startActivityOnResult返回Bitmap

        startActivityForResult(intent, RESULT_CLIP);
    }

    /**
     * 获取 ContentView
     *
     * @return ContentView
     */
    public View getContentView() {
        return findViewById(android.R.id.content);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁加载中Dialog
        LoadingDialog.getInstance().destory();
        //销毁权限申请框架
        mRxPermissions = null;
        //清空Disposable
        clearDisposable();
//        //解注册EventBus
        if (this.getClass().isAnnotationPresent(BindEventBus.class)
                && EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

}