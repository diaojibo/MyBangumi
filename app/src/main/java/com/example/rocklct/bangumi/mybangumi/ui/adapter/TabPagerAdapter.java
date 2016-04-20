package com.example.rocklct.bangumi.mybangumi.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by rocklct on 2016/3/19.
 */
//实例FragmentPagerAdapter
public class TabPagerAdapter extends FragmentPagerAdapter {

    //两个容器分别传入应要显示的tab标签和Fragment
    private final ArrayList<String> tab_title_list = new ArrayList<>();
    private final ArrayList<Fragment> fragmentList = new ArrayList<>();
    private FragmentManager mFragmentManaget;

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    //这个方法用于在外面添加tab标签名和对应的Fragment给adapter
    public void addFragment(String title, Fragment fragment) {
        fragmentList.add(fragment);
        tab_title_list.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tab_title_list.get(position);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
