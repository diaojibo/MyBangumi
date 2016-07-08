package com.example.rocklct.bangumi.mybangumi.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.rocklct.bangumi.mybangumi.R;
import com.example.rocklct.bangumi.mybangumi.util.GetTabAdapter;

public class MainActivity extends AbstractActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_drawer);
//        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.mytoolbar);
//        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.drawable.testicon);
        initTabs();
        initDrawer();
    }




    private void initTabs() {
        TabLayout mTabLayout = (TabLayout) findViewById(R.id.tabs);

        //取得一个自定义PagerAdapter的实例,用了自定义的类来取的对应的Adapter，要传入一个Fragment管理器
        mAdapter = new GetTabAdapter(getSupportFragmentManager()).getAnimationTabAdapter();
        mViewPager = (ViewPager) findViewById(R.id.myviewpager);

        //将缓存的页面数设置为相隔2
        mViewPager.setOffscreenPageLimit(2);

        //讲ViewPager和Adapter绑定起来获得数据
        mViewPager.setAdapter(mAdapter);

        //一站式同步，将TabLayout和PagerAdapter绑定起来
        mTabLayout.setupWithViewPager(mViewPager);


    }








}
