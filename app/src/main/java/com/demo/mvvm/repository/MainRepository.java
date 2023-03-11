package com.demo.mvvm.repository;

import com.demo.mvvm.network.interfaces.IMainNetworkSource;
import com.example.myapplication.base.repository.BaseRepository;


/**
 * 文件名: MainRepository
 * 日期: 2023/03/11 15:20
 * 描述: Main网络仓库
 */

public class MainRepository extends BaseRepository implements IMainNetworkSource {

    private MainRepository() {
    }

    public static MainRepository create() {
        return new MainRepository();
    }

}
