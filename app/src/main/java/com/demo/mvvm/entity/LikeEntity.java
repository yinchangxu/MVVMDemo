package com.demo.mvvm.entity;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import com.demo.mvvm.BR;
import com.demo.mvvm.R;
import com.example.myapplication.base.entity.BaseEntity;
import com.google.gson.annotations.SerializedName;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

public class LikeEntity extends BaseEntity {

    @SerializedName("like1")
    public String like1;
    @SerializedName("like2")
    public String like2;

}
