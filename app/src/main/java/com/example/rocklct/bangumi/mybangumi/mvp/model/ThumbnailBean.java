package com.example.rocklct.bangumi.mybangumi.mvp.model;

import android.annotation.SuppressLint;

/**
 * Created by rocklct on 2016/4/26.
 */
@SuppressLint("ParcelCreator")
public class ThumbnailBean extends BaseBean {
    public String title;
    public float rate = 0;
    public int rank;
    public String imageurl;
    public String id;

    public ThumbnailBean(String title,float rate,String imageurl,String id){
        super();
        this.title = title;
        this.rate = rate;
        this.imageurl = imageurl;
        this.id = id;
    }
}
