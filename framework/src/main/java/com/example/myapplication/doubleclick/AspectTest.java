package com.example.myapplication.doubleclick;

import android.util.Log;
import android.view.View;


import com.example.myapplication.util.NoDoubleClickUtils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class AspectTest {

    private boolean canDoubleClick = false;
    private View mLastView;

    @Before("@annotation(com.example.myapplication.annotation.DoubleClick)")
    public void beforeEnableDoubleClick(JoinPoint joinPoint) throws Throwable {
        canDoubleClick = true;
    }

    @Around("execution(* android.view.View.OnClickListener.onClick(..))  && target(Object) && this(Object)")
    public void OnClickListener(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] objects = joinPoint.getArgs();
        View view = objects.length == 0 ? null : (View) objects[0];
        Log.e("tag", "OnClick:" + view);
        if (view != mLastView || canDoubleClick || !NoDoubleClickUtils.isDoubleClick()) {
            joinPoint.proceed();
            canDoubleClick = false;
        }
        mLastView = view;
    }

}
