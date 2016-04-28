package com.example.rocklct.bangumi.mybangumi.util;

import android.animation.Animator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;

import com.example.rocklct.bangumi.mybangumi.BangumiApp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;

/**
 * Created by rocklct on 2016/3/28.
 */
public class Util {


    /**
     * 转换字节成为相应的大小
     *
     * @param size
     * @return
     */
    public static String getRealSize(long size) {
        int count = 0;
        double realsize = size;
        //这里的type用以转换表示,比如K M或者G都是单位
        String type = "BKMGTP";
        while (realsize > 1024) {
            realsize = realsize / 1024;
            count++;
        }
        BigDecimal bigDecimal = new BigDecimal(realsize);
        return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP)
                .doubleValue() + String.valueOf(type.charAt(count)) + (count == 0 ? "" : "B");
    }

    public static void readStream(InputStream is, OutputStream os) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = is.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }
        is.close();
        os.close();
    }

    /**
     * 计算缩放大小
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int width = options.outWidth;
        final int height = options.outHeight;

        if (reqWidth == 0) {
            reqWidth = BangumiApp.getmInstance().getmScreenWidth();
        }
        if (reqHeight == 0) {
            reqHeight = BangumiApp.getmInstance().getmScreenHeight();
        }

        int inSampSize = 1;
        //两倍两倍缩放，知道小于要求尺寸的为止
        if (width > reqWidth || height > reqHeight) {
            final int halfWidth = width / 2;
            final int halfHeight = height / 2;
            while ((halfHeight / inSampSize) > reqHeight && (halfWidth / inSampSize > reqWidth)) {
                inSampSize *= 2;
            }
        }
        return inSampSize;
    }


    public static Bitmap decodeBitmapFile(File file, int reqWidth, int reqHeight) throws FileNotFoundException {
        Log.d("Bounds", "width=" + reqWidth + "-height=" + reqHeight);
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        String pathname = file.getPath() + file.getName();
        Log.d("pathname", pathname);
        BitmapFactory.decodeStream(new FileInputStream(file), null, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(new FileInputStream(file),null,options);
    }

    public static boolean isZero(float score) {
        final int temp = (int) score;
        return temp == 0;
    }

    /**
     * 渐变动画
     *
     * @param outView
     * @param inView
     */
    public static void loadAnima(final View outView, View inView) {
        if (outView == null || inView == null) {
            Log.i("null", "true");
            return;
        }
        if (!outView.isShown())
            return;
        int time = 200;
        inView.setAlpha(0f);
        inView.setVisibility(View.VISIBLE);
        inView.animate()
                .alpha(1f)
                .setDuration(time)
                .setListener(null);
        outView.animate()
                .alpha(0f)
                .setDuration(time)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        outView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
    }
}
