package com.example.myapplication.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Message;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import static android.os.FileUtils.copy;

public class BitmapUtils {

    private static Bitmap bitmap;

    /**
     * 根据 图片URL 转换成 Bitmap
     *
     * @param url
     * @return
     */
    public static Bitmap getBitMap(final String url, final android.os.Handler handler) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                URL imageurl = null;

                try {
                    imageurl = new URL(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    HttpURLConnection conn = (HttpURLConnection) imageurl.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);

                    Message message = handler.obtainMessage();
                    message.obj = bitmap;
                    message.arg1 = 1;
                    handler.sendMessage(message);

                    is.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return bitmap;
    }

}
