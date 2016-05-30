package com.example.rocklct.bangumi.mybangumi.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rocklct.bangumi.mybangumi.R;
import com.example.rocklct.bangumi.mybangumi.ui.activity.WebViewActivity;
import com.example.rocklct.bangumi.mybangumi.ui.bean.BaseBean;
import com.example.rocklct.bangumi.mybangumi.ui.bean.BlogInfoBean;
import com.example.rocklct.bangumi.mybangumi.ui.viewholder.LoadViewHolder;
import com.example.rocklct.bangumi.mybangumi.util.ImageLoader.ImageLoader;
import com.example.rocklct.bangumi.mybangumi.util.Interfaces.OnRecycleViewItemClick;

import java.util.List;

/**
 * Created by rocklct on 2016/5/8.
 */
public class BlogInfoAdapter extends AbstractAdapter {

    private ImageLoader mImageLoader;
    public static final int TYPE_ITEM = 10;
    public static final int TYPE_LOAD = 11;
    private Context mContext;


    public BlogInfoAdapter(Context context, List<BaseBean> data) {
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
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_blog_item, parent, false);
                holder = new BlogInfoHolder(view);
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
            BlogInfoHolder itemHolder = (BlogInfoHolder) holder;
            final BlogInfoBean bean = (BlogInfoBean) mData.get(position);
            if (bean.imgurl != null) {
                mImageLoader.loadImage(bean.imgurl, itemHolder.rv_blog_img);
            }

            itemHolder.rv_blog_title.setText(bean.getTitle());
            itemHolder.rv_blog_author.setText(bean.author + " ");
            itemHolder.rv_blog_time.setText(bean.time);
            itemHolder.rv_blog_summary.setText(bean.summary);

            final String blogurl = bean.detailurl;
            itemHolder.setOnRecycleViewItemClick(new OnRecycleViewItemClick() {
                @Override
                public void OnItemClick(View v, int position) {
                    Uri uri = Uri.parse(blogurl);
                    Intent intent = new Intent(mContext, WebViewActivity.class);
                    intent.putExtra("url",blogurl);
                    mContext.startActivity(intent);
                    Log.d("testWebView", "entersucc");
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();

    }


    class BlogInfoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView rv_blog_title;
        public TextView rv_blog_summary;
        public TextView rv_blog_time;
        public TextView rv_blog_author;
        public ImageView rv_blog_img;
        private OnRecycleViewItemClick onRecycleViewItemClick;

        public BlogInfoHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            rv_blog_title = (TextView) itemView.findViewById(R.id.rv_blog_title);
            rv_blog_summary = (TextView) itemView.findViewById(R.id.rv_blog_summary);
            rv_blog_time = (TextView) itemView.findViewById(R.id.rv_blog_time);
            rv_blog_author = (TextView) itemView.findViewById(R.id.rv_blog_author);
            rv_blog_img = (ImageView) itemView.findViewById(R.id.rv_blog_img);

        }

        public void setOnRecycleViewItemClick(OnRecycleViewItemClick onRecycleViewItemClick) {
            this.onRecycleViewItemClick = onRecycleViewItemClick;
        }

        @Override
        public void onClick(View v) {
            if (onRecycleViewItemClick != null) {
                onRecycleViewItemClick.OnItemClick(v, getPosition());
            }
        }
    }


}
