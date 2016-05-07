package com.example.rocklct.bangumi.mybangumi.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rocklct.bangumi.mybangumi.R;
import com.example.rocklct.bangumi.mybangumi.ui.activity.DetailActivity;
import com.example.rocklct.bangumi.mybangumi.ui.bean.BaseBean;
import com.example.rocklct.bangumi.mybangumi.ui.bean.ThumbnailBean;
import com.example.rocklct.bangumi.mybangumi.ui.viewholder.LoadViewHolder;
import com.example.rocklct.bangumi.mybangumi.ui.viewholder.ThumbnailViewHolder;
import com.example.rocklct.bangumi.mybangumi.ui.viewholder.WeekDayViewHolder;
import com.example.rocklct.bangumi.mybangumi.util.ImageLoader.ImageLoader;
import com.example.rocklct.bangumi.mybangumi.util.Interfaces.OnRecycleViewItemClick;
import com.example.rocklct.bangumi.mybangumi.util.Util;

import java.util.List;

/**
 * Created by rocklct on 2016/3/24.
 */
public class ThumbnailAdapter extends AbstractAdapter {

    private ImageLoader mImageLoader;
    public static final int TYPE_ITEM = 1;
    public static final int TYPE_TITLE = 2;
    public static final int TYPE_LOAD = 3;
    private Context mContext;

    public ThumbnailAdapter(Context mContext, List<BaseBean> data) {
        this.mData = data;
        mImageLoader = ImageLoader.getmInstance();
        this.mContext = mContext;
    }

    @Override
    public int getItemViewType(int position) {
        int type = mData.get(position).getView_type();
        if (type > TYPE_WEEKDAY) {
            return type;
        }
        switch (type) {
            case TYPE_TITLE:
            case TYPE_LOAD:
                type = TYPE_LOAD;
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

        //处理新番view的情况
        if (viewType > TYPE_WEEKDAY) {
            view = mItems.get(viewType);
            Log.d("testhttp","viewtype"+viewType);
            viewHolder = new WeekDayViewHolder(view);
            return viewHolder;
        }

        //根据不同的viewtype返回不同的viewHolder
        switch (viewType) {
            case TYPE_ITEM:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.thumbnail_item, parent, false);
                viewHolder = new ThumbnailViewHolder(view);
                break;
            case TYPE_TITLE:
//                View tempView = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_item)
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
            final ThumbnailBean bean = (ThumbnailBean) mData.get(position);
            mImageLoader.loadImage(bean.imageurl, thumbnailViewHolder.thumbnail_iv);
            thumbnailViewHolder.thumbnail_title.setText(bean.title);
            thumbnailViewHolder.thumbnail_title.setSelected(true);
            if (Util.isZero(bean.rate)) {
                thumbnailViewHolder.thumbnail_score.setText(mContext.getString(R.string.have_no_score));
                thumbnailViewHolder.thumbnail_rating.setVisibility(View.GONE);
            } else {
                thumbnailViewHolder.thumbnail_score.setText(String.valueOf(bean.rate));
                thumbnailViewHolder.thumbnail_rating.setRating(bean.rate / 2);
                thumbnailViewHolder.thumbnail_rating.setVisibility(View.VISIBLE);
            }
            thumbnailViewHolder.setOnRecycleViewItemClick(new OnRecycleViewItemClick() {
                @Override
                public void OnItemClick(View v, int position) {
                    Intent intent = new Intent(mContext, DetailActivity.class);
                    intent.putExtra("id",bean.id);
                    intent.putExtra("title",bean.title);
                    intent.putExtra("image",bean.imageurl);
                    Log.d("tq","cao"+bean.id);
                    mContext.startActivity(intent);
                }
            });

        }

    }

    public interface OnAdapterBackListener {
        void OnClick(int position, int type);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
