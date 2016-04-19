package com.example.rocklct.bangumi.mybangumi;

import android.app.Application;

import com.example.rocklct.bangumi.mybangumi.util.ImageLoader.FileCache;
import com.example.rocklct.bangumi.mybangumi.util.ImageLoader.ImageLoader;
import com.google.gson.Gson;

import java.io.File;

/**
 * Created by rocklct on 2016/4/18.
 */
//Application保存一些全局信息
public class BangumiApp extends Application {
    private static BangumiApp mInstance;
    public ImageLoader mImageLoader;
    private int mScreenWidth,mScreenHeight;
    private FileCache mFileCache;

    public FileCache getmFileCache(){
        return mFileCache;
    }

    public int getmScreenWidth(){
        return mScreenWidth;
    }

    public int getmScreenHeight(){
        return mScreenHeight;
    }

    //单例模式获取Application
    public static BangumiApp getmInstance(){
        return mInstance;
    }

    public Gson getGson(){
        return new Gson();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mFileCache = new FileCache(this);
        //在这个Application一生成的时候就初始化ImageLoader了，并且调用了它的init方法。
        mImageLoader = ImageLoader.getmInstance();
        mImageLoader.init(this);
        mScreenHeight = getResources().getDisplayMetrics().heightPixels;
        mScreenWidth = getResources().getDisplayMetrics().widthPixels;

    }
}
