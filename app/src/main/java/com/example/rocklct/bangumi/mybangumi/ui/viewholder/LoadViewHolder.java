package com.example.rocklct.bangumi.mybangumi.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rocklct.bangumi.mybangumi.R;

/**
 * Created by rocklct on 2016/4/19.
 */
public class LoadViewHolder extends RecyclerView.ViewHolder {

    public TextView tv_more_information;
    public LinearLayout loading_layout;


    public LoadViewHolder(View itemView) {
        super(itemView);
        tv_more_information = (TextView) itemView.findViewById(R.id.tv_more_information);
        loading_layout = (LinearLayout) itemView.findViewById(R.id.loading_layout);
    }
}
