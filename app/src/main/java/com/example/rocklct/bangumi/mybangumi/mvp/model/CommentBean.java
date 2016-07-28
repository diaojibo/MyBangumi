package com.example.rocklct.bangumi.mybangumi.mvp.model;

/**
 * Created by rocklct on 2016/5/9.
 */
public class CommentBean extends BaseBean {
    public String author;
    public String comment;
    public String time;
    public String imgurl;
    public Float rating;

    public CommentBean(String author, String comment, String time, String imgurl,float rating) {
        this.author = author;
        this.time = time;
        this.imgurl = imgurl;
        this.rating = rating;
        this.comment = comment;
    }

}
