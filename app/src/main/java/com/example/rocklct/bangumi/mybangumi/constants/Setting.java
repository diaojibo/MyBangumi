package com.example.rocklct.bangumi.mybangumi.constants;

import com.example.rocklct.bangumi.mybangumi.ui.bean.ThumbnailBean;

/**
 * Created by rocklct on 2016/4/18.
 */
public class Setting {
    private static boolean IS_ALLOW_MOBILE = false;

    private static boolean IS_MOBILE_CONN = false;

    public static void setIsAllowMobile(boolean bl){
        IS_ALLOW_MOBILE = bl;
    }

    public static boolean isAllowMobile(){
        return IS_ALLOW_MOBILE;
    }

    public static boolean isMobileConn(){
        return IS_MOBILE_CONN;
    }

    public static void setIsMobileConn(boolean isMobileConn){
       Setting.IS_MOBILE_CONN = isMobileConn;
    }

    public static String getImageUrl(ThumbnailBean bean){
        String url = "";
        url = bean.imageurl;
        return url;
    }
}
