package com.example.rocklct.bangumi.mybangumi.util.ImageLoader;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.example.rocklct.bangumi.mybangumi.util.Util;

import java.lang.ref.SoftReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rocklct on 2016/3/24.
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

    public Bitmap get(String id) {
        return mMemoryCache.get(id);
    }

    public void put(String id, Bitmap bitmap) {
        mMemoryCache.put(id, bitmap);
    }

    public int getCount(){
        return mMemoryCache.size();
    }

    //用以查看缓存现在已经使用的字节数
    public String getMemorySize(){
        //获取一个缓存的快照,且已经根据使用频度排序。
        Map<String,Bitmap> map = mMemoryCache.snapshot();
        long size = 0;
        //map.entrySet返回一个实体集合,遍历实体集合
        for(Map.Entry<String,Bitmap> entry:map.entrySet()){
            size += entry.getValue().getByteCount();
        }

        return Util.getRealSize(size);
    }

    public void clear(){
        cache.clear();
    }


}
