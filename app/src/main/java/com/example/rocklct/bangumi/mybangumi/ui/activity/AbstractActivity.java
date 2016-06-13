package com.example.rocklct.bangumi.mybangumi.ui.activity;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.provider.SearchRecentSuggestions;
import android.support.design.widget.NavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rocklct.bangumi.mybangumi.BangumiApp;
import com.example.rocklct.bangumi.mybangumi.R;
import com.example.rocklct.bangumi.mybangumi.util.ImageLoader.ImageLoader;
import com.example.rocklct.bangumi.mybangumi.util.MySuggestionProvider;
import com.example.rocklct.bangumi.mybangumi.util.SessionManager;

/**
 * Created by rocklct on 5/28/16.
 */
public class AbstractActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    protected SessionManager mSession = BangumiApp.getmInstance().getSession();

    protected NavigationView mNavigationView;
    protected android.support.v7.widget.Toolbar toolbar;
    protected DrawerLayout mDrawerLayout;
    protected View nav_header;
    ImageLoader mImageLoader = ImageLoader.getmInstance();

    //Search_Function
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds mItems to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
                        MySuggestionProvider.AUTHORITY, MySuggestionProvider.MODE);
                suggestions.saveRecentQuery(query, null);

                Intent intent = new Intent(getApplicationContext(), SearchActvity.class);
                intent.putExtra("query", query);
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

    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Activity activity = this;

        if (id == R.id.login) {
            startActivity(new Intent(activity, LoginActivity.class));
            return false;
        }


        if (id == R.id.animation) {
            startActivity(new Intent(activity, MainActivity.class));
            return false;
        }
        if (id == R.id.book) {
            startActivity(new Intent(activity, BookMainActivity.class));
            return false;
        }

        if (id == R.id.music) {
            startActivity(new Intent(activity, MusicMainActivity.class));
            return false;
        }
        if (id == R.id.game) {
            startActivity(new Intent(activity, GameMainActivity.class));
            return false;
        }
        if (id == R.id.real) {
            startActivity(new Intent(activity, RealMainActivity.class));
            return false;
        }
        return false;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(mSession.isLogin()){
            if(mSession.isLogin()){
                TextView tv = (TextView) nav_header.findViewById(R.id.what_name);
                tv.setText(mSession.getUserNickname());
                ImageView imageView = (ImageView) nav_header.findViewById(R.id.nav_header_image);
                Log.d("tt3",mSession.getUserAvatar());
                mImageLoader.loadImage(mSession.getUserAvatar(),imageView);
            }
        }

        return false;
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        return super.onMenuOpened(featureId, menu);
    }

    protected void initDrawer() {
        //设置DrawerLayout的header部分并且填充进去
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);


        //设置监听，点击侧边栏事件
        mNavigationView.setNavigationItemSelectedListener(this);

        nav_header = mNavigationView.inflateHeaderView(R.layout.nav_header);

        //设置toggle监听和Drawer绑定到一起
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }
}
