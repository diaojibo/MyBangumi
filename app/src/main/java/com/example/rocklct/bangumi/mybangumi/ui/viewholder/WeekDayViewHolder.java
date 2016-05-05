package com.example.rocklct.bangumi.mybangumi.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by rocklct on 2016/4/19.
 */
public class WeekDayViewHolder extends RecyclerView.ViewHolder {



    public WeekDayViewHolder(View itemView) {
        super(itemView);
        ViewGroup p = (ViewGroup) itemView.getParent();
        if(p != null){
            p.removeAllViews();
        }
        Log.d("testhttp", String.valueOf(itemView == null));
    }
}
