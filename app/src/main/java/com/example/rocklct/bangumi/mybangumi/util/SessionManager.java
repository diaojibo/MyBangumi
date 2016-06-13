package com.example.rocklct.bangumi.mybangumi.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by rocklct on 2016/6/13.
 */

public class SessionManager {

    public static String PREF_NAME = "BangumiPref";
    public static final String KEY_IS_LOGIN = "isLogin";
    public static final String KEY_AUTH = "auth";
    public static final String KEY_AUTH_ENCODE = "authEncode";
    public static final String KEY_USER_ID = "userId";
    public static final String KEY_USER_NICKNAME = "userNickName";
    public static final String KEY_USER_AVATAR = "userAvatar";

    private SharedPreferences pref;

    public SessionManager(Context ctx) {
        this.pref = ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public boolean isLogin() {
        return pref.getBoolean(KEY_IS_LOGIN, false);
    }


    public void setIsLogin(boolean isLogin) {
        pref.edit().putBoolean(KEY_IS_LOGIN, isLogin).apply();
    }

    public void setAuth(String auth) {
        pref.edit().putString(KEY_AUTH, auth).apply();
    }

    public String getAuth() {
        return pref.getString(KEY_AUTH, "");
    }

    public String getAuthEncode() {
        return pref.getString(KEY_AUTH_ENCODE, "");
    }

    public void setAuthEncode(String authEncode) {
        pref.edit().putString(KEY_AUTH_ENCODE, authEncode).apply();
    }

    public String getUserId() {
        return pref.getString(KEY_USER_ID, "");
    }

    public void setUserId(String userid) {
        pref.edit().putString(KEY_USER_ID, userid).apply();
    }

    public String getUserNickname() {
        return pref.getString(KEY_USER_NICKNAME, "");
    }

    public void setUserNickname(String nickname) {
        pref.edit().putString(KEY_USER_NICKNAME, nickname).apply();
    }

    public String getUserAvatar() {
        return pref.getString(KEY_USER_AVATAR, "");
    }

    public void setUserAvatar(String avatar) {
        pref.edit().putString(KEY_USER_AVATAR, avatar).apply();
    }

    public void logout() {
        pref.edit()
                .putBoolean(KEY_IS_LOGIN, false)
                .remove(KEY_AUTH)
                .remove(KEY_AUTH_ENCODE)
                .remove(KEY_USER_ID)
                .remove(KEY_USER_NICKNAME)
                .remove(KEY_USER_AVATAR)
                .apply();
    }

}
