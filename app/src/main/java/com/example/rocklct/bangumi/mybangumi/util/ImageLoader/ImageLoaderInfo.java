package com.example.rocklct.bangumi.mybangumi.util.ImageLoader;

import android.widget.ImageView;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by rocklct on 2016/4/18.
 */
public class ImageLoaderInfo {
    public final String url;
    public final ImageLoadingListener listener;
    public final ImageView view;
    public final ImageCache imageCache;
    public final ReentrantLock lock;
    public final AtomicBoolean paused;
    public final Object pauseLock;


    public ImageLoaderInfo(String url, ImageLoadingListener listener, ImageView view, ImageCache imageCache, ReentrantLock lock, AtomicBoolean paused, Object pauseLock) {
        this.url = url;
        this.listener = listener;
        this.view = view;
        this.imageCache = imageCache;
        this.lock = lock;
        this.paused = paused;
        this.pauseLock = pauseLock;
    }
}
