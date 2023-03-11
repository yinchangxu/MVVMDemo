package com.demo.mvvm.entity;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import com.demo.mvvm.BR;
import com.demo.mvvm.R;
import com.example.myapplication.base.entity.BaseEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

public class RecyclerViewNestEntity<T> extends BaseEntity{

    @SerializedName("username")
    public String username;

    @SerializedName("like")
    public T like;

    public final ObservableList<LikeEntity> items = new ObservableArrayList<>();

    public final ItemBinding<LikeEntity> itemBinding = ItemBinding.of(BR.likeEntity, R.layout.item2_child);

}
