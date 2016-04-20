package com.example.rocklct.bangumi.mybangumi.util.ImageLoader;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.example.rocklct.bangumi.mybangumi.constants.Setting;

import java.io.File;
import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;

/**
 * Created by rocklct on 2016/3/28.
 */
public class ImageLoader {

    private ImageCache mImageCache;

    //加volatile关键字保证其在内存中读写，能最快被读取出最新信息但无法保证原子性
    private volatile static ImageLoader mInstance;

    //网络请求线程池
    private Executor mExecutor;

    //解析bitmap线程池
    private Executor mDecodeExecutor;

    //分发任务的线程池
    private Executor mDispatchExecutor;

    private android.os.Handler mHandler;

    //AtomicBoolean用以保证原子性操作
    private final AtomicBoolean mPaused = new AtomicBoolean(false);
    private final Object mPauseLock = new Object();

    //滑动时是否加载图片
    private boolean isSwipeLoadImage = true;

    //弱引用 重入锁
    private Map<String, ReentrantLock> mUrlLocks = new WeakHashMap<>();

    private ImageLoader() {

    }

    public boolean isSwipeLoadImage() {
        return isSwipeLoadImage;
    }

    public void setIsSwipeLoadImage(boolean isSwipeLoadImage) {
        this.isSwipeLoadImage = isSwipeLoadImage;
    }

    public synchronized void init(Context context) {
        mImageCache = new ImageCache(context);
        //消费者队列，队列为空时取值会阻塞，满的时候添加会阻塞
        BlockingQueue<Runnable> taskQueue = new LinkedBlockingDeque<>();

        //初始化三个线程池
        mExecutor = new ThreadPoolExecutor(3, 3, 0L, TimeUnit.MILLISECONDS, taskQueue, new DefaultThreadFactory(3, "thread-pool"));
        mDecodeExecutor = new ThreadPoolExecutor(3, 3, 0L, TimeUnit.MILLISECONDS, taskQueue, new DefaultThreadFactory(3, "thread-pool"));
        mDispatchExecutor = Executors.newCachedThreadPool(new DefaultThreadFactory(Thread.NORM_PRIORITY, "thread-pool-dispatch"));
        mHandler = new android.os.Handler();

    }

    //双锁模式来执行单例模式获取实例
    public static ImageLoader getmInstance() {
        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoader();
                }
            }
        }
        return mInstance;
    }

    public void putMemoryCache(String url, Bitmap bitmap) {
        mImageCache.memoryCache.put(url, bitmap);
    }

    public void clearMemoryCache() {
        mImageCache.memoryCache.clear();
    }

    public void clearFileCache() {
        mImageCache.fileCache.clear();
    }

    public long getFileCacheSize() {
        return mImageCache.getFileCache().getSize();
    }

    public String getMemoryCacheSize() {
        return mImageCache.getMemoryCacheSize();
    }

    public void pause() {
        mPaused.set(true);
    }

    public void resume() {
        mPaused.set(false);
        synchronized (mPauseLock) {
            mPauseLock.notifyAll();
        }
    }

    public void loadImage(String url, ImageView imageView) {
        loadImage(url,imageView,null);
    }

    public void loadImage(final String url, ImageView imageView, ImageLoadingListener listener) {
        Log.d("url", url);

        //是否允许使用移动网络
        if (Setting.isMobileConn() && !Setting.isAllowMobile()) {
            return;
        }

        imageView.setTag(url);

        //获取重入锁,防止任务重复执行
        ReentrantLock lock = getUrlLock(url);
        final ImageLoaderInfo info = new ImageLoaderInfo(url, listener, imageView, mImageCache,
                lock, mPaused, mPauseLock);

        //这是二级缓存，先从缓存中寻找图片
        Bitmap bitmap = mImageCache.memoryCache.get(url);
        if (bitmap != null){
            imageView.setImageBitmap(bitmap);
        }else{
            imageView.setImageBitmap(null);
            //内存缓存中没有则调用任务分发线程池
            mDispatchExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    File file = mImageCache.fileCache.getFile(url);
                    if(!file.exists()){
                        //如果没有文件缓存，就调用网络线程获取图片
                        mExecutor.execute(new LoadNetworkImageTask(info,mHandler));

                    }else {
                        mDecodeExecutor.execute(new LoadNetworkImageTask(info,mHandler));
                    }
                }
            });
        }


    }


    public ReentrantLock getUrlLock(String url) {
        //获取重入锁,根据不同的url获得
        ReentrantLock lock = mUrlLocks.get(url);

        //如果该url尚未创建锁,则创建一个
        if (lock == null) {
            lock = new ReentrantLock();
            mUrlLocks.put(url, lock);
        }
        return lock;
    }


    //线程工厂，作为线程池的参数
    public static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);

        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;
        private final int threadPriority;


        public DefaultThreadFactory(int threadPriority, String threadNamePrefix) {
            this.threadPriority = threadPriority;

            //找出父线程？
            group = Thread.currentThread().getThreadGroup();
            namePrefix = threadNamePrefix + poolNumber.getAndIncrement() + "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            t.setPriority(threadPriority);
            return t;
        }
    }


}
