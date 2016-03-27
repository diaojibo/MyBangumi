package com.example.rocklct.bangumi.mybangumi.util.ImageLoader;

import android.graphics.Bitmap;
import android.util.LruCache;

import java.lang.ref.SoftReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/24.
 */
public class MemoryCache {

    //一个比较线程安全的HashMap
    private Map<String, SoftReference<Bitmap>> cache =
            Collections.synchronizedMap(new HashMap<String, SoftReference<Bitmap>>());
    private LruCache<String, Bitmap> mMemoryCache;

    public MemoryCache() {
        //使用最大内存的十六分之一
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 16;
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount() / 1024;
            } //重写计算大小的方式
        };

    }

}
