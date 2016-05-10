package com.example.rocklct.bangumi.mybangumi.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.rocklct.bangumi.mybangumi.R;
import com.example.rocklct.bangumi.mybangumi.ui.bean.BaseBean;
import com.example.rocklct.bangumi.mybangumi.ui.bean.CommentBean;
import com.example.rocklct.bangumi.mybangumi.ui.viewholder.LoadViewHolder;
import com.example.rocklct.bangumi.mybangumi.util.ImageLoader.ImageLoader;
import com.example.rocklct.bangumi.mybangumi.util.Interfaces.OnRecycleViewItemClick;
import com.example.rocklct.bangumi.mybangumi.util.Util;

import java.util.List;

/**
 * Created by rocklct on 2016/5/8.
 */
public class CommentAdapter extends AbstractAdapter {

    private ImageLoader mImageLoader;
    public static final int TYPE_ITEM = 10;
    public static final int TYPE_LOAD = 11;
    private Context mContext;


    public CommentAdapter(Context context, List<BaseBean> data) {
        this.mData = data;
        mImageLoader = ImageLoader.getmInstance();
        this.mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        int type = mData.get(position).getView_type();
        switch (type) {
            case TYPE_LOAD:
                type = TYPE_LOAD;
                break;
            default:
                type = TYPE_ITEM;
        }
        return type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        View view = null;
        switch (viewType) {
            case TYPE_ITEM:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_comment_item, parent, false);
                holder = new CommentHolder(view);
                break;
            case TYPE_LOAD:
                view = mItems.get(TYPE_LOAD);
                holder = new LoadViewHolder(view);
        }

        return holder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == TYPE_LOAD) {
        }

        if (getItemViewType(position) == TYPE_ITEM) {
            CommentHolder itemHolder = (CommentHolder) holder;
            final CommentBean bean = (CommentBean) mData.get(position);
            if (bean.imgurl != null) {
                mImageLoader.loadImage(bean.imgurl, itemHolder.rv_comment_img);
            }

            if (Util.isZero(bean.rating)) {
                itemHolder.rv_comment_rb.setVisibility(View.GONE);
            } else {
                itemHolder.rv_comment_rb.setRating(bean.rating / 2);
                itemHolder.rv_comment_rb.setVisibility(View.VISIBLE);
            }
            itemHolder.rv_comment_author.setText(bean.author + " ");
            itemHolder.rv_comment_time.setText(bean.time);
            itemHolder.rv_comment_content.setText(bean.comment);
            itemHolder.setOnRecycleViewItemClick(new OnRecycleViewItemClick() {
                @Override
                public void OnItemClick(View v, int position) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();

    }


    class CommentHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView rv_comment_author;
        public TextView rv_comment_time;
        public TextView rv_comment_content;
        public RatingBar rv_comment_rb;
        public ImageView rv_comment_img;
        private OnRecycleViewItemClick onRecycleViewItemClick;

        public CommentHolder(View itemView) {
            super(itemView);
            rv_comment_author = (TextView) itemView.findViewById(R.id.rv_comment_author);
            rv_comment_content = (TextView) itemView.findViewById(R.id.rv_comment_content);
            rv_comment_time = (TextView) itemView.findViewById(R.id.rv_comment_time);
            rv_comment_img = (ImageView) itemView.findViewById(R.id.rv_comment_img);
            rv_comment_rb = (RatingBar) itemView.findViewById(R.id.rv_comment_rating);

        }

        public void setOnRecycleViewItemClick(OnRecycleViewItemClick onRecycleViewItemClick) {
            this.onRecycleViewItemClick = onRecycleViewItemClick;
        }

        @Override
        public void onClick(View v) {
            onRecycleViewItemClick.OnItemClick(v, getPosition());
        }
    }


}
