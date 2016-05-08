package com.example.rocklct.bangumi.mybangumi.util.ImageLoader;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import com.example.rocklct.bangumi.mybangumi.BangumiApp;
import com.example.rocklct.bangumi.mybangumi.R;
import com.example.rocklct.bangumi.mybangumi.util.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by rocklct on 2016/4/18.
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

    // 休眠线程
    private void waitIfPaused() {
        AtomicBoolean pause = imageLoaderInfo.paused;
        if (pause.get()) {
            //第一层同步获取pause变量的锁
            synchronized (imageLoaderInfo.pauseLock) {
                //看看状态是否是pause,是的话阻塞
                if (pause.get()) {
                    try {
                        imageLoaderInfo.pauseLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private boolean getBitmapFromNetwork() {
        try {
            URL mUrl = new URL(url);
            Log.d("testhttp", url);
            HttpsURLConnection conn = (HttpsURLConnection) mUrl.openConnection();
//            HttpURLConnection conn = (HttpURLConnection) mUrl.openConnection();
            conn.setReadTimeout(20 * 1000);
            conn.setConnectTimeout(5 * 1000);
            InputStream inputStream = conn.getInputStream();
            if(inputStream == null){
                Log.d("testhttp","nullohno");
            }
            File file = fileCache.getFile(url);
            //将读取到的图片流放进文件缓存当中
            Util.readStream(inputStream, new FileOutputStream(file));
            bitmap = Util.decodeBitmapFile(file, imageView.getWidth(), imageView.getHeight());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("testhttp", String.valueOf((bitmap)));
        return bitmap != null;
    }


    private boolean getBitmapFromFile() {
        File file = imageLoaderInfo.imageCache.fileCache.getFile(imageLoaderInfo.url);
        if (file == null || !file.exists()) {
            return false;
        }
        try {
            bitmap = Util.decodeBitmapFile(file, imageLoaderInfo.view.getWidth(), imageLoaderInfo.view.getHeight());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return bitmap != null;
    }

    @Override
    public void run() {
        waitIfPaused();
        ReentrantLock lock = imageLoaderInfo.lock;
        lock.lock();
        //加锁读取
        try {
            //如果可以从文件里读则先读然后放进缓存
            if (getBitmapFromFile()) {
                ImageLoader.getmInstance().putMemoryCache(url, bitmap);
                handler.post(new DisplayImageTask(bitmap, imageLoaderInfo));
            } else if (getBitmapFromNetwork()) {
                Log.d("testhttp","loadnetworksucc");
                //实在没办法则从网络拉取图片，然后存进缓存。
                ImageLoader.getmInstance().putMemoryCache(url, bitmap);
                handler.post(new DisplayImageTask(bitmap, imageLoaderInfo));
            } else{
                Context c = BangumiApp.getmInstance();
                Resources resources = c.getResources();
                Bitmap bitmap = BitmapFactory.decodeResource(resources, R.drawable.bangumiicon2);
                handler.post(new DisplayImageTask(bitmap,imageLoaderInfo));
            }
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

}
