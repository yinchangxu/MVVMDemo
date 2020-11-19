package com.example.myapplication.util;


import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

import com.blankj.utilcode.util.ImageUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideContext;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.myapplication.R;

/**
 * 图片加载工具类
 */
public class ImageLoaderUtil {

    public class ImageType {
        //默认图片
        public static final int DEFAULT = 0;
        //圆形图片
        public static final int CIRCULAR = 1;
        //圆角图片
        public static final int FILLET = 2;
    }

    ;

    private static volatile ImageLoaderUtil imageLoaderUtils;

    private ImageLoaderUtil() {
    }

    /**
     * DSL 双重检查 单例
     *
     * @return 单例
     */
    public synchronized static ImageLoaderUtil getInstance() {
        if (imageLoaderUtils == null) {
            synchronized (ImageLoaderUtil.class) {
                if (imageLoaderUtils == null) {
                    imageLoaderUtils = new ImageLoaderUtil();
                }
            }
        }
        return imageLoaderUtils;
    }

//    /**
//     * 显示图片
//     *
//     * @param context   Context
//     * @param imageView ImageView控件
//     * @param path      路径
//     */
//    public void display(Context context, ImageView imageView, String path, int type) {
//        if (imageView == null) {
//            throw new IllegalArgumentException("argument error");
//        }
//        if (type == ImageType.CIRCULAR) {
//            Glide.with(context)
//                    .load(path)
//                    .dontAnimate()
//                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
//                    .into(imageView);
//        } else if (type == ImageType.FILLET) {
//            Glide.with(context)
//                    .load(path)
//                    .dontAnimate()
//                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))//圆角半径
//                    .into(imageView);
//        } else {
//            Glide.with(context)
//                    .load(path)
//                    .dontAnimate()
//                    .into(imageView);
//        }
//    }

//    /**
//     * 显示图片
//     *
//     * @param context   Context
//     * @param imageView ImageView控件
//     * @param path      路径
//     * @param error     错误占位符
//     */
//    public void display(Context context, ImageView imageView, String path, int error, int type) {
//        if (imageView == null) {
//            throw new IllegalArgumentException("argument error");
//        }
//        if (type == ImageType.CIRCULAR) {
//            Glide.with(context)
//                    .load(path)
//                    .dontAnimate()
//                    .error(error)
//                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
//                    .into(imageView);
//        } else if (type == ImageType.FILLET) {
//            Glide.with(context)
//                    .load(path)
//                    .dontAnimate()
//                    .error(error)
//                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))//圆角半径
//                    .into(imageView);
//        } else {
//            Glide.with(context)
//                    .load(path)
//                    .dontAnimate()
//                    .error(error)
//                    .into(imageView);
//        }
//
//    }


    /**
     * 显示图片
     *
     * @param context     Context
     * @param imageView   ImageView控件
     * @param path        路径
     */
    public void display(Context context, ImageView imageView, String path, int type) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        if (type == ImageType.CIRCULAR) {
            Glide.with(context)
                    .load(path)
                    .dontAnimate()
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(imageView);
        } else if (type == ImageType.FILLET) {
            Glide.with(context)
                    .load(path)
                    .dontAnimate()
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))//圆角半径
                    .into(imageView);
        } else {
            Glide.with(context)
                    .load(path)
                    .dontAnimate()
                    .into(imageView);
        }
    }

    /**
     * 显示图片
     *
     * @param context     Context
     * @param imageView   ImageView控件
     * @param path        路径
     * @param placeholder 加载占位符
     * @param error       错误占位符
     */
    public void display(Context context, ImageView imageView, String path, int placeholder, int error, int type) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        if (type == ImageType.CIRCULAR) {
            Glide.with(context)
                    .load(path)
                    .dontAnimate()
                    .placeholder(placeholder)
                    .error(error)
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(imageView);
        } else if (type == ImageType.FILLET) {
            Glide.with(context)
                    .load(path)
                    .dontAnimate()
                    .placeholder(placeholder)
                    .error(error)
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))//圆角半径
                    .into(imageView);
        } else {
            Glide.with(context)
                    .load(path)
                    .dontAnimate()
                    .placeholder(placeholder)
                    .error(error)
                    .into(imageView);
        }
    }


    /**
     * 显示图片
     *
     * @param context          Context
     * @param imageView        ImageView控件
     * @param drawableResource 项目中的Drawable资源
     */
//    public void display(Context context, ImageView imageView, @DrawableRes int drawableResource, int type) {
//        if (imageView == null) {
//            throw new IllegalArgumentException("argument error");
//        }
//        if (type == ImageType.CIRCULAR) {
//            Glide.with(context)
//                    .load(drawableResource)
//                    .dontAnimate()
//                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
//                    .into(imageView);
//        } else if (type == ImageType.FILLET) {
//            Glide.with(context)
//                    .load(drawableResource)
//                    .dontAnimate()
//                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))//圆角半径
//                    .into(imageView);
//        } else {
//            Glide.with(context)
//                    .load(drawableResource)
//                    .dontAnimate()
//                    .into(imageView);
//        }
//    }

    /**
     * 显示图片
     *
     * @param context          Context
     * @param imageView        ImageView控件
     * @param drawableResource 项目中的Drawable资源
     * @param placeholder      加载占位符
     * @param error            错误占位符
     */
    public void display(Context context, ImageView imageView, @DrawableRes int drawableResource, int placeholder, int error, int type) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        if (type == ImageType.CIRCULAR) {
            Glide.with(context)
                    .load(drawableResource)
                    .dontAnimate()
                    .placeholder(placeholder)
                    .error(error)
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(imageView);
        } else if (type == ImageType.FILLET) {
            Glide.with(context)
                    .load(drawableResource)
                    .dontAnimate()
                    .placeholder(placeholder)
                    .error(error)
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))//圆角半径
                    .into(imageView);
        } else {
            Glide.with(context)
                    .load(drawableResource)
                    .dontAnimate()
                    .placeholder(placeholder)
                    .error(error)
                    .into(imageView);
        }
    }

    /**
     * 显示视频缩略图
     *
     * @param context       Context
     * @param imageView     ImageView控件
     * @param videoFilePath 视频本地路径
     */
    public void displayVideoThumb(Context context, ImageView imageView, String videoFilePath, int type) {
        displayVideoThumb(context, imageView, videoFilePath, 0, 0, type);
    }

    /**
     * 显示视频缩略图
     *
     * @param context       Context
     * @param imageView     ImageView控件
     * @param videoFilePath 视频本地路径
     * @param placeholder   加载中占位符
     * @param error         错误占位符
     */
    public void displayVideoThumb(Context context, ImageView imageView, String videoFilePath, int placeholder, int error, int type) {
        if (type == ImageType.CIRCULAR) {
            Glide.with(context)
                    .load(videoFilePath)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .centerCrop()
                    .placeholder(placeholder)
                    .error(error)
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(imageView);
        } else if (type == ImageType.FILLET) {
            Glide.with(context)
                    .load(videoFilePath)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .centerCrop()
                    .placeholder(placeholder)
                    .error(error)
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))//圆角半径
                    .into(imageView);
        } else {
            Glide.with(context)
                    .load(videoFilePath)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .centerCrop()
                    .placeholder(placeholder)
                    .error(error)
                    .into(imageView);
        }
    }

    /**
     * 显示视频缩略图
     *
     * @param context       Context
     * @param imageView     ImageView
     * @param videoFilePath 视频文件地址
     * @param placeholder   加载中占位符
     * @param error         错误占位符
     * @param target        目标回调接口
     */
    public void displayVideoThumb(Context context, ImageView imageView, String videoFilePath, int placeholder, int error, SimpleTarget<Bitmap> target, int type) {
        if (type == ImageType.CIRCULAR) {
            Glide.with(context)
                    .asBitmap()
                    .load(videoFilePath)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .centerCrop()
                    .placeholder(placeholder)
                    .error(error)
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(target);
        } else if (type == ImageType.FILLET) {
            Glide.with(context)
                    .asBitmap()
                    .load(videoFilePath)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .centerCrop()
                    .placeholder(placeholder)
                    .error(error)
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))//圆角半径
                    .into(target);
        } else {
            Glide.with(context)
                    .asBitmap()
                    .load(videoFilePath)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .centerCrop()
                    .placeholder(placeholder)
                    .error(error)
                    .into(target);
        }
    }
}

