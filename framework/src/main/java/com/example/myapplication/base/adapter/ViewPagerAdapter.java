package com.example.myapplication.base.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myapplication.base.fragment.AppBaseFragment;

import java.util.List;

/**
 * @作者：Administrator
 * @时间：2020/5/25 15:14
 * @描述：viewpager+fragment
 **/
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<AppBaseFragment> list;

    public ViewPagerAdapter(FragmentManager fm, List<AppBaseFragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
