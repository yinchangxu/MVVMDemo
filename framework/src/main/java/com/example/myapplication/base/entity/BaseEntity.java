package com.example.myapplication.base.entity;


import com.example.myapplication.base.util.CastUtil;

import java.io.Serializable;

/**
 * 文件名: BaseEntity
 * 作者: yin
 * 日期: 2020/4/16 23:42
 * 描述: 全实体类的基类
 */
public abstract class BaseEntity implements Serializable {

    /**
     * 获取子类
     *
     * @param <T> 子类类型
     * @return 子类
     */
    public <T extends BaseEntity> T get() {
        return CastUtil.cast(this);
    }

    /**
     * 获取ID
     *
     * @return ID
     */
    public String getId() {
        return "";
    }

}
