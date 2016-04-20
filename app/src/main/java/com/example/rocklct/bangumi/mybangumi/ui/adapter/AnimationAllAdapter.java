package com.example.rocklct.bangumi.mybangumi.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rocklct.bangumi.mybangumi.R;
import com.example.rocklct.bangumi.mybangumi.constants.Setting;
import com.example.rocklct.bangumi.mybangumi.ui.Activity.DetailActivity;
import com.example.rocklct.bangumi.mybangumi.ui.bean.AnimationBean;
import com.example.rocklct.bangumi.mybangumi.ui.bean.BaseBean;
import com.example.rocklct.bangumi.mybangumi.ui.viewholder.LoadViewHolder;
import com.example.rocklct.bangumi.mybangumi.ui.viewholder.ThumbnailViewHolder;
import com.example.rocklct.bangumi.mybangumi.util.ImageLoader.ImageLoader;
import com.example.rocklct.bangumi.mybangumi.util.Interfaces.OnRecycleViewItemClick;
import com.example.rocklct.bangumi.mybangumi.util.Util;

import java.util.List;

/**
 * Created by rocklct on 2016/3/24.
 */
public class AnimationAllAdapter extends AbstractAdapter {

    private ImageLoader mImageLoader;
    public static final int TYPE_ITEM = 1;
    public static final int TYPE_TITLE = 2;
    public static final int TYPE_LOAD = 3;
    private Context mContext;

    public AnimationAllAdapter(Context mContext, List<BaseBean> data) {
        this.mData = data;
        mImageLoader = ImageLoader.getmInstance();
        this.mContext = mContext;
    }

    @Override
    public int getItemViewType(int position) {
        int type = mData.get(position).getView_type();
        switch (type) {
            case TYPE_TITLE:
            case TYPE_LOAD:
                break;
            default:
                type = TYPE_ITEM;
                break;
        }
        return type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        View view = null;

        //根据不同的viewtype返回不同的viewHolder
        switch (viewType) {
            case TYPE_ITEM:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.thumbnail_item, parent, false);
                viewHolder = new ThumbnailViewHolder(view);
                break;
            case TYPE_TITLE:
                view = mItems.get(TYPE_TITLE);
                viewHolder = new LoadViewHolder(view);
                break;
            case TYPE_LOAD:
                view = mItems.get(TYPE_LOAD);
                viewHolder = new LoadViewHolder(view);

        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (holder.getItemViewType() == TYPE_ITEM) {
            ThumbnailViewHolder thumbnailViewHolder = (ThumbnailViewHolder) holder;
            final AnimationBean.SubjectBean bean = (AnimationBean.SubjectBean) mData.get(position);
            mImageLoader.loadImage(Setting.getImageUrl(bean.getImages()), thumbnailViewHolder.thumbnail_iv);
            thumbnailViewHolder.thumbnail_title.setText(bean.getTitle());
            thumbnailViewHolder.thumbnail_title.setSelected(true);
            if (Util.isZero(bean.getRating().getAverage())) {
                thumbnailViewHolder.thumbnail_score.setText(mContext.getString(R.string.have_no_score));
                thumbnailViewHolder.thumbnail_rating.setVisibility(View.GONE);
            } else {
                thumbnailViewHolder.thumbnail_score.setText(String.valueOf(bean.getRating().getAverage()));
                thumbnailViewHolder.thumbnail_rating.setRating(bean.getRating().getAverage() / 2);
                thumbnailViewHolder.thumbnail_rating.setVisibility(View.VISIBLE);
            }
            thumbnailViewHolder.setOnRecycleViewItemClick(new OnRecycleViewItemClick() {
                @Override
                public void OnItemClick(View v, int position) {
                    Intent intent = new Intent(mContext, DetailActivity.class);
                }
            });

        }

    }

    public interface OnAdapterBackListener{
        void OnClick(int position,int type);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
