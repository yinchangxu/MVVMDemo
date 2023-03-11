package com.demo.mvvm.viewmodel.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.demo.mvvm.network.RecyclerViewNetworkSource;
import com.demo.mvvm.repository.RecyclerViewRepository;
import com.demo.mvvm.viewmodel.RecyclerViewViewModel;
import com.example.myapplication.base.util.CastUtil;

/**
 * 文件名: RecyclerViewModelFactory
 * 日期: 2023/03/11 15:22
 * 描述: ViewModel Factory
 */
public class RecyclerViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    /**
     * 简单工厂模式创建对象
     *
     * @return 对象
     */
    public static RecyclerViewModelFactory create() {
        return new RecyclerViewModelFactory();
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        RecyclerViewRepository recyclerViewRepository = RecyclerViewRepository.create()
                .setNetworkSource(new RecyclerViewNetworkSource());

        return CastUtil.cast(new RecyclerViewViewModel(recyclerViewRepository));
    }
}
