package com.example.rocklct.bangumi.mybangumi.mvp.model;

/**
 * Created by rocklct on 2016/5/9.
 */
public class BlogInfoBean extends BaseBean {
    public String detailurl;
    public String title;
    public String author;
    public String summary;
    public String time;
    public String imgurl;

    public BlogInfoBean(String title, String author, String summary, String time, String imgurl,String detailurl) {
        this.title = title;
        this.detailurl = detailurl;
        this.author = author;
        this.summary = summary;
        this.time = time;
        this.imgurl = imgurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
}
