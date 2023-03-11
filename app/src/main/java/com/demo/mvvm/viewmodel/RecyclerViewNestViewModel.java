package com.demo.mvvm.viewmodel;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.demo.mvvm.BR;
import com.demo.mvvm.R;
import com.demo.mvvm.entity.LikeEntity;
import com.demo.mvvm.entity.RecyclerViewNestEntity;
import com.demo.mvvm.repository.RecyclerViewNestRepository;
import com.example.myapplication.base.entity.TitleBarBean;
import com.example.myapplication.base.viewmodel.BaseViewModel;

import java.util.List;

import me.tatarka.bindingcollectionadapter2.ItemBinding;


/**
 * 文件名: RecyclerViewNestViewModel
 * 日期: 2023/03/11 18:51
 * 描述: ViewModel
 */
public class RecyclerViewNestViewModel extends BaseViewModel<RecyclerViewNestRepository> {

    //导航栏bean类
    private TitleBarBean titleBarBean = new TitleBarBean();

    //顶部导航栏
    public ObservableField<TitleBarBean> titleBar = new ObservableField<>(titleBarBean);

    public final ObservableList<RecyclerViewNestEntity<List<LikeEntity>>> items = new ObservableArrayList<>();
    public final ItemBinding<RecyclerViewNestEntity<List<LikeEntity>>> itemBinding = ItemBinding.of(BR.recyclerViewNestEntity, R.layout.item2);

    public RecyclerViewNestViewModel(RecyclerViewNestRepository repository) {
        super(repository);
        //导航栏中心文字
        titleBarBean.tvCenter.set("RecyclerView嵌套");
        //导航栏左侧图标点击事件
        titleBarBean.onBack(new TitleBarBean.OnItemEventListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
    }


    @Override
    protected void onCreate() {
        super.onCreate();
        //假数据
        for (int i = 0; i < 3; i++) {
            RecyclerViewNestEntity<List<LikeEntity>> recyclerViewNestEntity = new RecyclerViewNestEntity<>();
            recyclerViewNestEntity.username = String.valueOf(i);

            for (int j = 0; j < 4; j++) {
                LikeEntity likeEntity = new LikeEntity();
                likeEntity.like1 = "1";
                likeEntity.like2 = "2";
                recyclerViewNestEntity.items.add(likeEntity);
            }
            items.add(recyclerViewNestEntity);
        }
    }

}
