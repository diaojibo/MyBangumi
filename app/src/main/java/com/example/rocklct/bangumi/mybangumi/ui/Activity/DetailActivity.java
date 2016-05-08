package com.example.rocklct.bangumi.mybangumi.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;

import com.example.rocklct.bangumi.mybangumi.R;
import com.example.rocklct.bangumi.mybangumi.ui.adapter.TabPagerAdapter;
import com.example.rocklct.bangumi.mybangumi.util.GetTabAdapter;
import com.example.rocklct.bangumi.mybangumi.util.ImageLoader.ImageLoader;

/**
 * Created by rocklct on 2016/4/19.
 */
public class DetailActivity extends AbstractSwipeActivity {

    private String id;
    private String imgurl;
    private String title;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ImageView item_image;
    private ImageLoader mImageLoader;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initData();
        initView();
        Log.d("httptest2", "cao" + id);
    }


    public void initView(){
        mViewPager = (ViewPager) findViewById(R.id.detail_viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.detail_tabs);
        item_image = (ImageView) findViewById(R.id.detail_image);
        item_image.setAdjustViewBounds(true);
        item_image.setMaxHeight(500);
        item_image.setMaxWidth(350);
        mImageLoader = ImageLoader.getmInstance();
        mImageLoader.loadImage(imgurl.replace("/c/","/l/"),item_image);
        mViewPager.setOffscreenPageLimit(2);
        TabPagerAdapter adapter = new GetTabAdapter(getSupportFragmentManager()).getDeatilTabAdapter(id);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    public void initData() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        imgurl = intent.getStringExtra("image");
        title = intent.getStringExtra("title");
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
    }
}
