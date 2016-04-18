package com.example.rocklct.bangumi.mybangumi.util.ImageLoader;

import android.content.Context;

/**
 * Created by rocklct on 2016/3/28.
 */
public class ImageCache {
    //内存缓存和文件缓存
    MemoryCache memoryCache;
    FileCache fileCache;
    public ImageCache(Context context){
        memoryCache = new MemoryCache();
        fileCache = new FileCache(context);
    }

    public FileCache getFileCache(){
        return fileCache;
    }

    public MemoryCache getMemoryCache(){
        return memoryCache;
    }

    public String getMemoryCacheSize(){
        return memoryCache.getMemorySize();
    }

    public int getMemoryCacheCount(){
        return memoryCache.getCount();
    }

}
