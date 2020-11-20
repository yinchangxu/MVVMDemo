package com.example.myapplication.base.adapter;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ObjectUtils;
import com.example.myapplication.R;
import com.example.myapplication.base.entity.BaseEntity;
import com.example.myapplication.base.util.PhotoFromPhotoAlbum;
import com.example.myapplication.util.DisplayMetricsUtil;
import com.example.myapplication.util.ImageLoaderUtil;

import java.util.List;

public class MyBindingAdapter {

    @BindingAdapter("android:src")
    public static void setSrc(ImageView view, Object object) {
        if (object instanceof Integer) {
            view.setImageResource((Integer) object);
        } else if (object instanceof Uri) {
            view.setImageURI((Uri) object);
        } else if (object instanceof String) {
            ImageLoaderUtil.getInstance().display(view.getContext(), view, (String) object, ImageLoaderUtil.ImageType.DEFAULT);
        } else if (object instanceof Bitmap) {
            view.setImageBitmap((Bitmap) object);
        }
    }

    @BindingAdapter("setWidth")
    public static void setWidth(View view, double width) {
        if (view == null || width < 0) return;
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params != null) {
            params.width = (int) (width);
        }
    }

    @BindingAdapter(value = "android:background", requireAll = false)
    public static void setBackground(View view, Object object) {
        if (object instanceof Integer) {
            view.setBackgroundResource((Integer) object);
        } else if (object instanceof Uri) {
            String path=PhotoFromPhotoAlbum.getRealPathFromUri(view.getContext(),(Uri)object);
            view.setBackgroundDrawable(new BitmapDrawable(BitmapFactory.decodeFile(path)));
        } else if (object instanceof Bitmap) {
            view.setBackground(new BitmapDrawable((Bitmap) object));
        }
    }

    @BindingAdapter(value = "android:backgroundTint", requireAll = false)
    public static void setTintColor(ImageView imageView, int colorId) {
        imageView.setColorFilter(imageView.getContext().getColor(colorId));
    }


    @BindingAdapter(value = "android:textColor", requireAll = false)
    public static void setTextColor(TextView textView, int colorId) {
        textView.setTextColor(textView.getContext().getColor(colorId));
    }

    @BindingAdapter(value = "android:textSize", requireAll = false)
    public static void setTextSize(TextView textView, int size) {
        textView.setTextSize(size);
    }

}
