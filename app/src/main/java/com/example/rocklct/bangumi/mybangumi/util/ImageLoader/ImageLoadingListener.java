package com.example.rocklct.bangumi.mybangumi.util.ImageLoader;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by rocklct on 2016/4/18.
 */
public interface ImageLoadingListener {
    void onLoadComplete(String url, ImageView view, Bitmap loadedImage);
}
