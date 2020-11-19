package com.example.myapplication.util;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ScreenshotUtil {

    /**
     * 截取指定activity显示内容
     * 需要读写权限
     */
    public static void saveScreenshotFromActivity(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = view.getDrawingCache();
        saveImageToGallery(bitmap, activity);
        //回收资源
        view.setDrawingCacheEnabled(false);
        view.destroyDrawingCache();
    }

    /**
     * 截取指定View显示内容
     * 需要读写权限
     */
    public static void saveScreenshotFromView(View view, Activity context) {
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = view.getDrawingCache();
        saveImageToGallery(bitmap, context);
        //回收资源
        view.setDrawingCacheEnabled(false);
        view.destroyDrawingCache();
    }

    /**
     * 保存图片至相册
     * 需要读写权限
     */
    public static void saveImageToGallery(Bitmap bmp, Activity context) {
        File appDir = new File(getDCIM());
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + getDCIM())));
        Toast.makeText(context,"截图已保存至" + file.getAbsolutePath(),Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取相册路径
     */
    private static String getDCIM() {
        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return "";
        }
        String path = Environment.getExternalStorageDirectory().getPath() + "/dcim/";
        if (new File(path).exists()) {
            return path;
        }
        path = Environment.getExternalStorageDirectory().getPath() + "/DCIM/";
        File file = new File(path);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                return "";
            }
        }
        return path;
    }

}
