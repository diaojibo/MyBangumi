package com.example.rocklct.bangumi.mybangumi.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

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
        while ((len = is.read(buffer))!=-1){
            os.write(buffer,0,len);
        }
        is.close();
        os.close();
    }


    public  static int calculateInSampleSize(BitmapFactory.Options options,int reqWidth,int reqHeight){
        final int width = options.outWidth;
        final int height = options.outHeight;

        if(reqWidth == 0){

        }
        return 0;
    }


    public static Bitmap decodeBitmapFile(File file,int reqWidth,int reqHeight) throws FileNotFoundException {
        Log.d("Bounds","width="+reqWidth+"-height="+reqHeight);
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        String pathname = file.getPath() + file.getName();
        Log.d("pathname",pathname);
        BitmapFactory.decodeStream(new FileInputStream(file),null,options);
//        options.inSampleSize
        return null;
    }

    public static boolean isZero(float score){
        final int temp = (int) score;
        return temp == 0;
    }
}
