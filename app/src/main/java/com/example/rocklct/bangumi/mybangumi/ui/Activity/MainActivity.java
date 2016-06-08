package com.example.rocklct.bangumi.mybangumi.ui.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.rocklct.bangumi.mybangumi.R;
import com.example.rocklct.bangumi.mybangumi.util.GetTabAdapter;
import com.example.rocklct.bangumi.mybangumi.util.MySuggestionProvider;

public class MainActivity extends AppCompatActivity
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
        mAdapter = new GetTabAdapter(getSupportFragmentManager()).getAnimationTabAdapter();
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
        // Inflate the menu; this adds mItems to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main , menu);
        final MenuItem menuItem = menu.findItem(R.id.action_search);

        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        final SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(true);
        searchView.setQueryRefinementEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //save record of searching
                SearchRecentSuggestions suggestions = new SearchRecentSuggestions(getApplicationContext(),
                        MySuggestionProvider.AUTHORITY,MySuggestionProvider.MODE);
                suggestions.saveRecentQuery(query,null);

                Intent intent = new Intent(getApplicationContext(),SearchActvity.class);
                intent.putExtra("query",query);
                startActivity(intent);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.animation){
            startActivity(new Intent(MainActivity.this,MainActivity.class));
            return false;
        }
        if(id == R.id.book){
            startActivity(new Intent(MainActivity.this,BookMainActivity.class));
            return false;
        }

        if(id == R.id.music){
            startActivity(new Intent(MainActivity.this,MusicMainActivity.class));
            return false;
        }
        if(id == R.id.game){
            startActivity(new Intent(MainActivity.this,GameMainActivity.class));
            return false;
        }
        if(id == R.id.real){
            startActivity(new Intent(MainActivity.this,RealMainActivity.class));
            return false;
        }

        return false;
    }


}
