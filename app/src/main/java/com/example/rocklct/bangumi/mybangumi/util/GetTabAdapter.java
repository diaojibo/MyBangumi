package com.example.rocklct.bangumi.mybangumi.util;

import android.support.v4.app.FragmentManager;

import com.example.rocklct.bangumi.mybangumi.ui.adapter.TabPagerAdapter;
import com.example.rocklct.bangumi.mybangumi.ui.fragment.AnimationJournal;
import com.example.rocklct.bangumi.mybangumi.ui.fragment.AnimationTop;

/**
 * Created by rocklct on 2016/3/22.
 */

//此方法用于返回对应的Adapter用来绑定pager和tabs
public class GetTabAdapter {
    private TabPagerAdapter tpa;
    private FragmentManager fm;

    public GetTabAdapter(FragmentManager fm){
       this.fm = fm;
    }

    public TabPagerAdapter getAnimationTabAdapter() {
        tpa = new TabPagerAdapter(fm);
        tpa.addFragment("排行榜",new AnimationTop());
        tpa.addFragment("日志",new AnimationJournal());
        tpa.addFragment("新番",new AnimationJournal());
        return tpa;
    }



}
