package com.example.myapplication.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Function: Activity或Fragment标记为需要申请权限
 * Author: ShiJingFeng
 * Date: 2019/11/15 13:50
 * Description:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface NeedPermissions {
}
