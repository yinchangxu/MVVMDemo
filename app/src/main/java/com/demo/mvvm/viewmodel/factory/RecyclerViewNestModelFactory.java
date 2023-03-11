package com.demo.mvvm.viewmodel.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.demo.mvvm.network.RecyclerViewNestNetworkSource;
import com.demo.mvvm.repository.RecyclerViewNestRepository;
import com.demo.mvvm.viewmodel.RecyclerViewNestViewModel;
import com.example.myapplication.base.util.CastUtil;

/**
 * 文件名: RecyclerViewNestModelFactory
 * 日期: 2023/03/11 18:50
 * 描述: ViewModel Factory
 */
public class RecyclerViewNestModelFactory extends ViewModelProvider.NewInstanceFactory {

    /**
     * 简单工厂模式创建对象
     *
     * @return 对象
     */
    public static RecyclerViewNestModelFactory create() {
        return new RecyclerViewNestModelFactory();
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        RecyclerViewNestRepository recyclerViewNestRepository = RecyclerViewNestRepository.create()
                .setNetworkSource(new RecyclerViewNestNetworkSource());

        return CastUtil.cast(new RecyclerViewNestViewModel(recyclerViewNestRepository));
    }
}
