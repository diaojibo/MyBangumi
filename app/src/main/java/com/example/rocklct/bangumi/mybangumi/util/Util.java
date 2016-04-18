package com.example.rocklct.bangumi.mybangumi.util;

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
}
