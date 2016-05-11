package com.example.rocklct.bangumi.mybangumi.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.rocklct.bangumi.mybangumi.R;
import com.example.rocklct.bangumi.mybangumi.util.GetTabAdapter;

public class BookMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private android.support.v7.widget.Toolbar toolbar;
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.drawable.testicon);
        initTabs();
        initDrawer();
    }


    private void initDrawer() {
        //设置DrawerLayout的header部分并且填充进去
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);

        //设置监听，点击侧边栏事件
        mNavigationView.setNavigationItemSelectedListener(this);

        View nav_header = mNavigationView.inflateHeaderView(R.layout.nav_header);

        //设置toggle监听和Drawer绑定到一起
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,R.string.nav_drawer_open,R.string.nav_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }

    private void initTabs() {
        TabLayout mTabLayout = (TabLayout) findViewById(R.id.tabs);

        //取得一个自定义PagerAdapter的实例,用了自定义的类来取的对应的Adapter，要传入一个Fragment管理器
        mAdapter = new GetTabAdapter(getSupportFragmentManager()).getBookTabAdapter();
        mViewPager = (ViewPager) findViewById(R.id.myviewpager);

        //将缓存的页面数设置为相隔2
        mViewPager.setOffscreenPageLimit(2);

        //讲ViewPager和Adapter绑定起来获得数据
        mViewPager.setAdapter(mAdapter);

        //一站式同步，将TabLayout和PagerAdapter绑定起来
        mTabLayout.setupWithViewPager(mViewPager);


    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main , menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.animation){
            startActivity(new Intent(BookMainActivity.this,MainActivity.class));
            return false;
        }
        if(id == R.id.book){
            startActivity(new Intent(BookMainActivity.this,BookMainActivity.class));
            return false;
        }

        if(id == R.id.music){
            startActivity(new Intent(BookMainActivity.this,MusicMainActivity.class));
            return false;
        }
        if(id == R.id.game){
            startActivity(new Intent(BookMainActivity.this,GameMainActivity.class));
            return false;
        }
        if(id == R.id.real){
            startActivity(new Intent(BookMainActivity.this,RealMainActivity.class));
            return false;
        }



        return false;
    }
}
