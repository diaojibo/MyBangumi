package com.example.rocklct.bangumi.mybangumi.constants;

/**
 * Created by rocklct on 2016/4/20.
 */
public class BangumiAPi {

    public static final String getTopAnimation = "http://bangumi.tv/anime/browser?sort=rank";
    public static final String getSimpleItemInfo = "http://api.bgm.tv/subject/";
    public static final String getCalendar = "http://api.bgm.tv/calendar";
    public static final String getDetailItem = "http://api.bgm.tv/subject/";
    public static final String getSubjectRoot = "http://bgm.tv/subject/";
    public static final String getReviewRoot = "http://bgm.tv/";
//    public static final String getReview = "https://bgm.tv/subject/253/reviews";


    public static String getReviewFromType(String type,int page){
        return getReviewRoot+type+"/blog/"+page+".html";
    }

    public static String getReviewUrl(String id,int page){
        return getSubjectRoot+id+"/reviews/"+page+".html";
    }

    public static String getCommentUrl(String id,int page){
        return getSubjectRoot+id+"/comments"+"?page="+page;
    }

}
