package com.example.rocklct.bangumi.mybangumi.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by rocklct on 2016/3/19.
 */
public class TabPagerAdapter extends FragmentPagerAdapter {

    private final ArrayList<String> tab_title_list = new ArrayList<>();
    private final ArrayList<Fragment> fragmentList = new ArrayList<>();
    private FragmentManager mFragmentManaget;

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

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
