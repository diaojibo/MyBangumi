package com.example.rocklct.bangumi.mybangumi.adapter;

import android.content.Context;

import com.example.rocklct.bangumi.mybangumi.bean.BaseBean;
import com.example.rocklct.bangumi.mybangumi.util.ImageLoader.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/3/24.
 */
public class AnimationAllAdapter extends AbstractAdapter {

    private ImageLoader mImageLoader;
    public static final int TYPE_ITEM = 1;
    public static final int TYPE_TITLE = 2;
    public static final int TYPE_LOAD = 3;
    private Context mContext;
    public AnimationAllAdapter(Context mContext, List<BaseBean> data){
        this.mData = data;
        mImageLoader = ImageLoader.getmInstance();
        this.mContext = mContext;
    }

}
