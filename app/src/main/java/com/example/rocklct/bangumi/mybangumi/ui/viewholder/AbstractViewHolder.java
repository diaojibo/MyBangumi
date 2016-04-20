package com.example.rocklct.bangumi.mybangumi.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.rocklct.bangumi.mybangumi.util.Interfaces.OnRecycleViewItemClick;

/**
 * Created by rocklct on 2016/4/19.
 */
public class AbstractViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private OnRecycleViewItemClick onRecycleViewItemClick;

    public void setOnRecycleViewItemClick(OnRecycleViewItemClick onRecycleViewItemClick){
        this.onRecycleViewItemClick = onRecycleViewItemClick;
    }

    public AbstractViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(onRecycleViewItemClick != null){
            onRecycleViewItemClick.OnItemClick(v,getPosition());
        }
    }
}
