package com.example.rocklct.bangumi.mybangumi.ui.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.rocklct.bangumi.mybangumi.R;

/**
 * Created by rocklct on 2016/4/19.

 */
public class ThumbnailViewHolder extends AbstractViewHolder {
    public ImageView thumbnail_iv;
    public TextView thumbnail_title;
    public TextView thumbnail_score;
    public RatingBar thumbnail_rating;


    public ThumbnailViewHolder(View itemView) {
        super(itemView);
        thumbnail_iv = (ImageView) itemView.findViewById(R.id.thumbnail_iv);
        thumbnail_rating = (RatingBar) itemView.findViewById(R.id.thumbnail_item_rating);
        thumbnail_title = (TextView) itemView.findViewById(R.id.thumbnail_item_title);
        thumbnail_score = (TextView) itemView.findViewById(R.id.thumbnail_item_score);
    }
}
