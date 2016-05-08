package com.example.rocklct.bangumi.mybangumi.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rocklct.bangumi.mybangumi.R;
import com.example.rocklct.bangumi.mybangumi.ui.bean.BaseBean;
import com.example.rocklct.bangumi.mybangumi.ui.bean.DetailItemBean;
import com.example.rocklct.bangumi.mybangumi.util.ImageLoader.ImageLoader;
import com.example.rocklct.bangumi.mybangumi.util.Interfaces.OnRecycleViewItemClick;

import java.util.List;

/**
 * Created by rocklct on 2016/5/8.
 */
public class DetailAdapter extends AbstractAdapter {

    private List<BaseBean> data;
    private ImageLoader mImageLoader;
    public static final int TYPE_ITEM = 10;
    public static final int TYPE_HEADER = 11;
    private Context mContext;


    public DetailAdapter(Context context, List<BaseBean> data) {
        this.data = data;
        mImageLoader = ImageLoader.getmInstance();
        this.mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getView_type() == TYPE_HEADER ? TYPE_HEADER : TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        View view = null;
        switch (viewType) {
            case TYPE_HEADER:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_detail_header, parent, false);
                holder = new HeaderHolder(view);
                break;
            case TYPE_ITEM:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_detail_item, parent, false);
                holder = new ItemHolder(view);
                break;

        }

        return holder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d("httptest2", "pos" + position + " " + getItemViewType(position));

        if (getItemViewType(position) == TYPE_HEADER) {
            HeaderHolder headerHolder = (HeaderHolder) holder;
            DetailItemBean.DeatilInfoBean bean = (DetailItemBean.DeatilInfoBean) data.get(position);
            headerHolder.rv_air_summary.setText(bean.summary);
            headerHolder.rv_air_date.setText(bean.air_date);
            headerHolder.setOnRecycleViewItemClick(new OnRecycleViewItemClick() {
                @Override
                public void OnItemClick(View v, int position) {

                }
            });
        }

        if (getItemViewType(position) == TYPE_ITEM) {
            ItemHolder itemHolder = (ItemHolder) holder;
            final DetailItemBean.crtBean bean = (DetailItemBean.crtBean) data.get(position);
            if (bean.images != null) {
                mImageLoader.loadImage(bean.images.medium.replace(":", "s:"), itemHolder.rv_detail_chrImg);
            }
            if (bean.name_cn.equals("")) {
                bean.name_cn = bean.name;
            }
            itemHolder.rv_detail_name.setText(bean.name_cn);
            itemHolder.rv_detail_role.setText(bean.role_name);
            String cv = bean.getCv();
            if (cv != null) {

                itemHolder.rv_detail_cv.setText(bean.getCv());
            }
            itemHolder.setOnRecycleViewItemClick(new OnRecycleViewItemClick() {
                @Override
                public void OnItemClick(View v, int position) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();

    }


    class HeaderHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView rv_air_date;
        public TextView rv_air_summary;
        private OnRecycleViewItemClick onRecycleViewItemClick;

        public HeaderHolder(View itemView) {
            super(itemView);
            rv_air_date = (TextView) itemView.findViewById(R.id.rv_detail_AirDate);
            rv_air_summary = (TextView) itemView.findViewById(R.id.rv_detail_summary);
        }

        public void setOnRecycleViewItemClick(OnRecycleViewItemClick onRecycleViewItemClick) {
            this.onRecycleViewItemClick = onRecycleViewItemClick;
        }

        @Override
        public void onClick(View v) {
            onRecycleViewItemClick.OnItemClick(v, getPosition());
        }
    }

    class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView rv_detail_name;
        private TextView rv_detail_role;
        private TextView rv_detail_cv;
        private ImageView rv_detail_chrImg;
        private OnRecycleViewItemClick onRecycleViewItemClick;

        public void setOnRecycleViewItemClick(OnRecycleViewItemClick onRecycleViewItemClick) {
            this.onRecycleViewItemClick = onRecycleViewItemClick;
        }

        public ItemHolder(View itemView) {
            super(itemView);
            rv_detail_chrImg = (ImageView) itemView.findViewById(R.id.rv_detail_itempic);
            rv_detail_name = (TextView) itemView.findViewById(R.id.rv_detail_itemName);
            rv_detail_role = (TextView) itemView.findViewById(R.id.rv_detail_role);
            rv_detail_cv = (TextView) itemView.findViewById(R.id.rv_detail_cv);
        }

        @Override
        public void onClick(View v) {
            onRecycleViewItemClick.OnItemClick(v, getPosition());
        }
    }
}
