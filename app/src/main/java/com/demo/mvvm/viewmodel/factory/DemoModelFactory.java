package com.demo.mvvm.viewmodel.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.demo.mvvm.network.DemoNetworkSource;
import com.demo.mvvm.repository.DemoRepository;
import com.demo.mvvm.viewmodel.DemoViewModel;
import com.example.myapplication.base.util.CastUtil;

/**
 * 文件名: DemoModelFactory
 * 日期: 2020/11/19 10:17
 * 描述: ViewModel Factory
 */
public class DemoModelFactory extends ViewModelProvider.NewInstanceFactory {

    /**
     * 简单工厂模式创建对象
     *
     * @return 对象
     */
    public static DemoModelFactory create() {
        return new DemoModelFactory();
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        DemoRepository demoRepository = DemoRepository.create()
                .setNetworkSource(new DemoNetworkSource());

        return CastUtil.cast(new DemoViewModel(demoRepository));
    }
}
