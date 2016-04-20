package com.example.rocklct.bangumi.mybangumi.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.rocklct.bangumi.mybangumi.ui.bean.BaseBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/24.
 */
public class AbstractAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_ITEM = 0;
    public static final int TYPE_LOAD = 1;
    public static final int TYPE_HEADER = 2;

    //mData就是要传进去的数据
    public List<BaseBean> mData;
    public Map<Integer, View> mItems;

    public AbstractAdapter() {
        mItems = new HashMap<>();
        mData = new ArrayList<>();
    }

    //添加自定义视图,同时判断视图属于什么类型，item，load，还是什么。
    public void addCustomView(View view, int position, int tag) {
        BaseBean bean = new BaseBean();
        if (tag > 0) {
            bean.setView_type(tag);
        }
        mItems.put(tag, view);
        mData.add(position, bean);
    }

    public int getItemViewType(int position) {
        return mData.get(position).getView_type() == TYPE_LOAD ? TYPE_LOAD : TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
