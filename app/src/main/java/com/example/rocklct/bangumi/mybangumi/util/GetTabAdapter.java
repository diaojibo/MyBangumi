package com.example.rocklct.bangumi.mybangumi.util;

import android.support.v4.app.FragmentManager;

import com.example.rocklct.bangumi.mybangumi.BangumiApp;
import com.example.rocklct.bangumi.mybangumi.ui.adapter.TabPagerAdapter;
import com.example.rocklct.bangumi.mybangumi.ui.fragment.AnimationBlog;
import com.example.rocklct.bangumi.mybangumi.ui.fragment.AnimationCalendar;
import com.example.rocklct.bangumi.mybangumi.ui.fragment.AnimationTop;
import com.example.rocklct.bangumi.mybangumi.ui.fragment.BlogInfoFragment;
import com.example.rocklct.bangumi.mybangumi.ui.fragment.BookBlog;
import com.example.rocklct.bangumi.mybangumi.ui.fragment.BookTop;
import com.example.rocklct.bangumi.mybangumi.ui.fragment.CommentFragment;
import com.example.rocklct.bangumi.mybangumi.ui.fragment.DetailFragment;
import com.example.rocklct.bangumi.mybangumi.ui.fragment.GameBlog;
import com.example.rocklct.bangumi.mybangumi.ui.fragment.GameTop;
import com.example.rocklct.bangumi.mybangumi.ui.fragment.MusicBlog;
import com.example.rocklct.bangumi.mybangumi.ui.fragment.MusicTop;
import com.example.rocklct.bangumi.mybangumi.ui.fragment.MyCommentFragment;
import com.example.rocklct.bangumi.mybangumi.ui.fragment.RealAllTop;
import com.example.rocklct.bangumi.mybangumi.ui.fragment.RealBlog;
import com.example.rocklct.bangumi.mybangumi.ui.fragment.RealENTop;
import com.example.rocklct.bangumi.mybangumi.ui.fragment.RealJPTop;

/**
 * Created by rocklct on 2016/3/22.
 */

//此方法用于返回对应的Adapter用来绑定pager和tabs
public class GetTabAdapter {
    private TabPagerAdapter tpa;
    private FragmentManager fm;

    public GetTabAdapter(FragmentManager fm) {
        this.fm = fm;
    }

    public TabPagerAdapter getDeatilTabAdapter(String id) {
        int number = 3;
        boolean state = false;
        SessionManager sessionManager = BangumiApp.getmInstance().getSession();
        if (sessionManager.isLogin()){
            number = 4;
            state = true;
        }
        tpa = new TabPagerAdapter(fm,number);
        tpa.addFragment("介绍", DetailFragment.newInstance(id));
        tpa.addFragment("吐槽", CommentFragment.newInstance(id));
        tpa.addFragment("评论", BlogInfoFragment.newInstance(id));
        if(state){
            tpa.addFragment("我的评价", MyCommentFragment.newInstance(id));
        }
        return tpa;
    }

    public TabPagerAdapter getAnimationTabAdapter() {
        tpa = new TabPagerAdapter(fm);
        tpa.addFragment("排行榜", new AnimationTop());
        tpa.addFragment("日志", new AnimationBlog());
        tpa.addFragment("新番", new AnimationCalendar());
        return tpa;
    }


    public TabPagerAdapter getBookTabAdapter() {
        tpa = new TabPagerAdapter(fm, 2);
        tpa.addFragment("排行榜", new BookTop());
        tpa.addFragment("日志", new BookBlog());
        return tpa;
    }

    public TabPagerAdapter getMusicTabAdapter() {
        tpa = new TabPagerAdapter(fm, 2);
        tpa.addFragment("排行榜", new MusicTop());
        tpa.addFragment("日志", new MusicBlog());
        return tpa;
    }

    public TabPagerAdapter getGameTabAdapter() {
        tpa = new TabPagerAdapter(fm, 2);
        tpa.addFragment("排行榜", new GameTop());
        tpa.addFragment("日志", new GameBlog());
        return tpa;
    }

    public TabPagerAdapter getRealTabAdapter() {
        tpa = new TabPagerAdapter(fm, 4);
        tpa.addFragment("排行榜", new RealAllTop());
        tpa.addFragment("日剧", new RealJPTop());
        tpa.addFragment("欧美剧", new RealENTop());
        tpa.addFragment("日志", new RealBlog());
        return tpa;
    }
}
