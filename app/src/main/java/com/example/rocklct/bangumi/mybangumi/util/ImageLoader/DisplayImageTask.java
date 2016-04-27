package com.example.rocklct.bangumi.mybangumi.util.ImageLoader;

import android.graphics.Bitmap;
import android.util.Log;

/**
 * Created by rocklct on 2016/4/18.
 */
public class DisplayImageTask implements Runnable {

    private Bitmap bitmap;
    private ImageLoaderInfo imageLoaderInfo;
    public DisplayImageTask(Bitmap bitmap,ImageLoaderInfo imageLoaderInfo){
        this.bitmap = bitmap;
        this.imageLoaderInfo = imageLoaderInfo;
    }

    @Override
    public void run() {
        Log.d("testhttp","rundisplaytask");
        if(imageLoaderInfo.url.equals(imageLoaderInfo.view.getTag())){
            imageLoaderInfo.view.setImageBitmap(bitmap);
            if(imageLoaderInfo.listener!=null){
                imageLoaderInfo.listener.onLoadComplete(imageLoaderInfo.url,imageLoaderInfo.view,bitmap);
            }
        }
    }
}
