package com.demo.mvvm.viewmodel.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.demo.mvvm.network.MainNetworkSource;
import com.demo.mvvm.repository.MainRepository;
import com.demo.mvvm.viewmodel.MainViewModel;
import com.example.myapplication.base.util.CastUtil;

/**
 * 文件名: MainModelFactory
 * 日期: 2023/03/11 15:22
 * 描述: ViewModel Factory
 */
public class MainModelFactory extends ViewModelProvider.NewInstanceFactory {

    /**
     * 简单工厂模式创建对象
     *
     * @return 对象
     */
    public static MainModelFactory create() {
        return new MainModelFactory();
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        MainRepository mainRepository = MainRepository.create()
                .setNetworkSource(new MainNetworkSource());

        return CastUtil.cast(new MainViewModel(mainRepository));
    }
}
