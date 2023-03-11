package com.demo.mvvm.repository;

import com.demo.mvvm.network.interfaces.IDemoNetworkSource;
import com.example.myapplication.base.repository.BaseRepository;

/**
 * 文件名: BaseRepository
 * 日期: 2020/11/19 9:48
 * 描述: Demo网络仓库
 */

public class DemoRepository extends BaseRepository implements IDemoNetworkSource {


    private DemoRepository() {
    }

    public static DemoRepository create() {
        return new DemoRepository();
    }

}
