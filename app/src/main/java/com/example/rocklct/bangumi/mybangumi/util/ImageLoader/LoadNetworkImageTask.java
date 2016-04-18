package com.example.rocklct.bangumi.mybangumi.util.ImageLoader;

import android.graphics.Bitmap;
import android.os.Handler;
import android.widget.ImageView;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Administrator on 2016/4/18.
 */
public class LoadNetworkImageTask implements Runnable {
    private String url;
    private ImageView imageView;
    private ImageLoadingListener listener;
    private Bitmap bitmap;
    private Handler handler;
    private ImageLoaderInfo imageLoaderInfo;
    private MemoryCache memoryCache;
    private FileCache fileCache;

    public LoadNetworkImageTask(ImageLoaderInfo imageLoaderInfo, Handler handler) {
        this.imageView = imageLoaderInfo.view;
        this.url = imageLoaderInfo.url;
        this.listener = imageLoaderInfo.listener;
        this.handler = handler;
        this.imageLoaderInfo = imageLoaderInfo;
        this.fileCache = imageLoaderInfo.imageCache.fileCache;
        this.memoryCache = imageLoaderInfo.imageCache.memoryCache;

    }

    private void waitIfPaused(){
        AtomicBoolean pause = imageLoaderInfo.paused;
        if(pause.get()){
            synchronized (imageLoaderInfo.pauseLock){
                if(pause.get()){
                    try{
                        imageLoaderInfo.pauseLock.wait();
                    }catch (InterruptedException e){

                    }
                }
            }
        }
    }


    @Override
    public void run() {


    }
}
