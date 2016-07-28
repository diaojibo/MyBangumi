package com.example.rocklct.bangumi.mybangumi.mvp.model;

import android.annotation.SuppressLint;

/**
 * Created by rocklct on 2016/6/13.
 */

@SuppressLint("ParcelCreator")
public class LoginInfoBean extends BaseBean {
    public String id;
    public String url;
    public String username;
    public String nickname;
    public avatarBean avatar;
    public String auth;
    public String auth_encode;

    public static class avatarBean{
        public String large;
        public String medium;
        public String small;
    }

}
