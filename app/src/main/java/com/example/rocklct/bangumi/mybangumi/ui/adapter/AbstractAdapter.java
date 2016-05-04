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
 * Created by rocklct on 2016/3/24.
 */
public class AbstractAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    // TYPE_ITEM 视图类型是缩略图的view类型
    public static final int TYPE_ITEM = 0;
    // TYPE_LOAD 视图类型是底部翻页的加载
    public static final int TYPE_LOAD = 1;
    public static final int TYPE_HEADER = 2;
    public static final int TYPE_WEEKDAY = 100;

    public static final int TYPE_WEEKDAY_MON = 101;
    public static final int TYPE_WEEKDAY_TUE = 102;
    public static final int TYPE_WEEKDAY_WED = 103;
    public static final int TYPE_WEEKDAY_THU = 104;
    public static final int TYPE_WEEKDAY_FRI = 105;
    public static final int TYPE_WEEKDAY_SAT = 106;
    public static final int TYPE_WEEKDAY_SUN = 107;

    //mData就是要传进去的数据
    public List<BaseBean> mData;
    //用来放不同类型的类模板
    public Map<Integer, View> mItems;
    //用来给每个数据放置额外信息
    public Map<Integer,String> extra_message;

    public AbstractAdapter() {
        mItems = new HashMap<>();
        mData = new ArrayList<>();
        extra_message = new HashMap<>();
    }

    public void addExtraMessage(int position,String msg){
        extra_message.put(position,msg);
    }


    //添加自定义视图模板,同时判断视图属于什么类型，item，load，还是什么。
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
