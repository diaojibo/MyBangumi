package com.example.rocklct.bangumi.mybangumi.mvp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by rocklct on 2016/3/23.
 */
public class BaseBean{

    //存放bean视图的类型
    protected int view_type;

    protected BaseBean(Parcel in) {
        view_type = in.readInt();
    }



    public BaseBean() {

    }

    public int getView_type() {
        return view_type;
    }

    public void setView_type(int view_type) {
        this.view_type = view_type;
    }


}
