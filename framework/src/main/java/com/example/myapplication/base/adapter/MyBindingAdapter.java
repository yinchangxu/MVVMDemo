package com.example.myapplication.base.adapter;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
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
import com.example.myapplication.util.DisplayMetricsUtil;
import com.example.myapplication.util.ImageLoaderUtil;

import java.util.List;

public class MyBindingAdapter {

    @BindingAdapter("android:src")
    public static void setSrc(ImageView view, Bitmap bitmap) {
        view.setImageBitmap(bitmap);
    }

    @BindingAdapter("android:src")
    public static void setSrc(ImageView view, int resId) {
        view.setImageResource(resId);
    }

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

    @BindingAdapter("android:src")
    public static void setSrc(ImageView view, String url) {
        ImageLoaderUtil.getInstance().display(view.getContext(), view, url, ImageLoaderUtil.ImageType.DEFAULT);
    }

//    @BindingAdapter("android:src")
//    public static void setSrc(ImageView view, Uri uri) {
//        view.setImageURI(uri);
//    }

    @BindingAdapter("setWidth")
    public static void setWidth(View view, double width) {
        if (view == null || width < 0) return;
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params != null) {
            params.width = (int) (width);
        }
    }

    @BindingAdapter(value = "backgroundColor", requireAll = false)
    public static void setBackgroundColor(View view, int color) {
//        if (color instanceof Integer) {
        view.setBackgroundColor(view.getContext().getColor(color));
//        }else if (color instanceof String){
//            view.setBackgroundColor(Color.parseColor((String) color));
//        }
    }

    @BindingAdapter(value = "backgroundColor", requireAll = false)
    public static void setBackgroundColor(View view, String color) {
//        if (color instanceof Integer) {
//        view.setBackgroundColor(view.getContext().getColor((Integer) color));
//        }else if (color instanceof String){
        view.setBackgroundColor(Color.parseColor((String) color));
//        }
    }

    @BindingAdapter(value = "backgroundDrawable", requireAll = false)
    public static void setBackgroundDrawable(ImageView view, int drawable) {
        view.setBackgroundResource(drawable);
    }

    @BindingAdapter(value = "setTintColor", requireAll = false)
    public static void setTintColor(ImageView imageView, int colorId) {
        imageView.setColorFilter(imageView.getContext().getColor(colorId));
    }

    @BindingAdapter(value = "setTextColor", requireAll = false)
    public static void setTintColor(TextView textView, int colorId) {
        textView.setTextColor(textView.getContext().getColor(colorId));
    }

    @BindingAdapter(value = "setTextSize", requireAll = false)
    public static void setTextSize(TextView textView, int size) {
        textView.setTextSize(size);
    }

//    @BindingAdapter(value = "imageUrl", requireAll = true)
//    public static void setSrc(ImageView imageView, String url) {
//        Glide.with(imageView.getContext()).load(url)
//                .placeholder(R.mipmap.ic_launcher)
//                .into(imageView);
//        ImageLoaderUtil.getInstance().display(imageView.getContext(), imageView, url, R.mipmap.ic_launcher, R.mipmap.ic_launcher, ImageLoaderUtil.ImageType.DEFAULT);
//    }

//    @BindingAdapter(value = {"imageUrl", "placeHolder", "error"}, requireAll = false)
//    public static void loadImage(ImageView imageView, String url, Drawable holderDrawable, Drawable errorDrawable) {
//        Glide.with(imageView.getContext())
//                .load(url)
//                .placeholder(holderDrawable)
//                .error(errorDrawable)
//                .into(imageView);
//    }

//    @BindingAdapter("setDatas")
//    public static void setDatas(RecyclerView recyclerView, List<BaseEntity> datas) {
//        CommonRecycleViewAdapter commonRecycleViewAdapter=new CommonRecycleViewAdapter()
//    }

}
