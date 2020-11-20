package com.example.myapplication.base.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;

/**
 * @author zhaofusen
 * @describe 动态设置布局嵌套View圆角
 * @date 2019/4/15 19:45
 */
public class MaskLayout extends LinearLayout {

    /**
     * 推荐的圆角角度
     * 也可自定义圆角角度
     * 换算单位为DP
     */
    public final static int ANGLE_DP_5 = 5;
    public final static int ANGLE_DP_10 = 10;

    /**
     * 0 无圆角
     */
    public final static int VIEW_NO_CORNER_PART = 0;

    /**
     * 1 设置  Top Left,Top Right 圆角
     */
    public final static int VIEW_TOP_CORNER_PART = 1;

    /**
     * 2 设置 Bottom Left ,Bottom Right 圆角
     */
    public final static int VIEW_BOTTOM_CORNER_PART = 2;

    /**
     * 3 设置全圆角，四个角为圆角
     */
    public final static int VIEW_CORNER_ALL_PART = 3;

    private Context mContext;

    private GradientDrawable maskDrawable;
    private GradientDrawable cloneMaskDrawable;
    private PorterDuffXfermode mDuffXfermode;

    /**
     * 设置的角度值
     */
    private float[] cornerRadiusValue = {0,0,0,0,0,0,0,0};

    private Paint maskPaint;
    private Canvas mCanvas;

    public MaskLayout(@NonNull Context context) {
        this(context, null);
    }

    public MaskLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MaskLayout);
        maskDrawable = (GradientDrawable)array.getDrawable(R.styleable.MaskLayout_xhg_mask_drawable);
        array.recycle();

        maskPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        maskPaint.setFilterBitmap(true);
        mDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        maskPaint.setXfermode(mDuffXfermode);

        setWillNotDraw(false);
    }

    @Override
    public void draw(Canvas canvas) {
        if (cloneMaskDrawable != null) {
            this.mCanvas = canvas;
            int width = getMeasuredWidth();
            int height = getMeasuredHeight();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                canvas.saveLayer(0f, 0f, width, height, null);
            } else {
                canvas.saveLayer(0f, 0f, width, height, null, Canvas.ALL_SAVE_FLAG);
            }
            cloneMaskDrawable.setBounds(getPaddingLeft(), getPaddingTop(), width - getPaddingRight(), height - getPaddingBottom());
            cloneMaskDrawable.draw(canvas);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                canvas.saveLayer(0f, 0f, width, height, maskPaint);
            } else {
                canvas.saveLayer(0f, 0f, width, height, maskPaint, Canvas.ALL_SAVE_FLAG);
            }
            super.draw(canvas);
            canvas.restore();
        } else {
            super.draw(canvas);
        }
        postInvalidateDelayed(100);
//        invalidate();
    }

    /**
     * 模板圆角 number  Y
     *     Value: 0=无圆角，1=圆角头，2=圆角尾，3=圆角头尾
     */
    public void setTemplateCorner(int cornerPartType,float angleValue) {
        if (maskDrawable != null) {
            if (cloneMaskDrawable == null){
                cloneMaskDrawable = (GradientDrawable) maskDrawable.getConstantState().newDrawable();
                cloneMaskDrawable.mutate();
            }
            if (cornerPartType == VIEW_TOP_CORNER_PART){
                cornerRadiusValue = getCornerRadii(angleValue,angleValue,0,0);
//                cloneMaskDrawable.setCornerRadii(getCornerRadii(angleValue,angleValue,0,0));
            } else if (cornerPartType == VIEW_BOTTOM_CORNER_PART){
                cornerRadiusValue = getCornerRadii(0,0,angleValue,angleValue);
//                cloneMaskDrawable.setCornerRadii(getCornerRadii(0,0,angleValue,angleValue));
            } else if (cornerPartType == VIEW_CORNER_ALL_PART){
                cornerRadiusValue = getCornerRadii(angleValue,angleValue,angleValue,angleValue);
//                cloneMaskDrawable.setCornerRadii(getCornerRadii(angleValue,angleValue,angleValue,angleValue));
//                cloneMaskDrawable.setCornerRadius(dp2px(angleValue));
            } else {
                cornerRadiusValue = getCornerRadii(0,0,0,0);
//                cloneMaskDrawable.setCornerRadii(getCornerRadii(0,0,0,0));
//                cloneMaskDrawable.setCornerRadius(0);
            }
//            cloneMaskDrawable.setCornerRadii(cornerRadiusValue);

        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (cloneMaskDrawable != null){
            cloneMaskDrawable.setCornerRadii(cornerRadiusValue);
        }
    }

    /**
     * 当Holder从屏幕离开的时候进行设置数据
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (cloneMaskDrawable != null){
            cloneMaskDrawable.setCornerRadius(0);
        }
    }

    /**
     * 设置圆角
     * @param leftTop
     * @param rightTop
     * @param leftBottom
     * @param rightBottom
     * @return
     */
    private float[] getCornerRadii(float leftTop,float rightTop,
                                   float leftBottom,float rightBottom){
        //这里返回的一个浮点型的数组，一定要有8个元素，不然会报错
        return new float[]{dp2px(leftTop), dp2px(leftTop), dp2px(rightTop),dp2px(rightTop),dp2px(rightBottom), dp2px(rightBottom),dp2px(leftBottom),dp2px(leftBottom)};
    }

    /**
     * 转换
     * @param dpVal
     * @return
     */
    private float dp2px(float dpVal){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, mContext.getResources().getDisplayMetrics());
    }
}
