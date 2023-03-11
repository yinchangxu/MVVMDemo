package com.demo.mvvm.repository;

import com.demo.mvvm.network.interfaces.IRecyclerViewNestNetworkSource;
import com.example.myapplication.base.repository.BaseRepository;


/**
 * 文件名: RecyclerViewNestRepository
 * 日期: 2023/03/11 18:50
 * 描述: Main网络仓库
 */

public class RecyclerViewNestRepository extends BaseRepository implements IRecyclerViewNestNetworkSource {


    private RecyclerViewNestRepository() {
    }

    public static RecyclerViewNestRepository create() {
        return new RecyclerViewNestRepository();
    }

}
