package com.example.rocklct.bangumi.mybangumi.constants;

import com.example.rocklct.bangumi.mybangumi.BangumiApp;

/**
 * Created by rocklct on 2016/4/20.
 */
public class BangumiAPi {

    public static final String getjpReal = "http://bgm.tv/real/browser/platform/jp?sort=rank";
    public static final String getenReal = "http://bgm.tv/real/browser/platform/en?sort=rank";
    public static final String getTopReal = "http://bgm.tv/real/browser/platform/all?sort=rank";
    public static final String getTopBook = "http://bgm.tv/book/browser?sort=rank";
    public static final String getTopMusic = "http://bgm.tv/music/browser?sort=rank";
    public static final String getTopGame = "http://bgm.tv/game/browser?sort=rank";
    public static final String getTopAnimation = "http://bangumi.tv/anime/browser?sort=rank";
    public static final String getSimpleItemInfo = "http://api.bgm.tv/subject/";
    public static final String getCalendar = "http://api.bgm.tv/calendar";
    public static final String getDetailItem = "http://api.bgm.tv/subject/";
    public static final String getSubjectRoot = "http://bgm.tv/subject/";
    public static final String getReviewRoot = "http://bgm.tv/";
    public static final String getBangumiApiroot = "https://bgm.tv";
    public static final String getSearchItem = "http://bgm.tv/subject_search/";
    public static final String loginUrl = "http://api.bgm.tv/auth?source=onAir";
    public static final String updateComment = "http://api.bgm.tv/collection/";
//    public static final String getReview = "https://bgm.tv/subject/253/reviews";


    public static String getUpdateCommentURL(String id){
        String auth = BangumiApp.getmInstance().getSession().getAuthEncode();
        return updateComment+id+"/update?source=onAir&auth="+auth;
    }

    public static String getSearchItemURL(String name) {
        return getSearchItem + name + "?cat=all";
    }


    public static String getReviewFromType(String type, int page) {
        return getReviewRoot + type + "/blog/" + page + ".html";
    }

    public static String getReviewUrl(String id, int page) {
        return getSubjectRoot + id + "/reviews/" + page + ".html";
    }

    public static String getCommentUrl(String id, int page) {
        return getSubjectRoot + id + "/comments" + "?page=" + page;
    }

}
