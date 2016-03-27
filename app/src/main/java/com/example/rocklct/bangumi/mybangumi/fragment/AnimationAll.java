package com.example.rocklct.bangumi.mybangumi.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rocklct.bangumi.mybangumi.R;

/**
 * Created by rocklct on 2016/3/21.
 */
public class AnimationAll extends AbstractFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isCreated = false;
    }

    @Override
    void initData() {

    }

    private void initView(){
        mSwipeRefreshLayout.setEnabled(false);
        mRecyclerView.setHasFixedSize(true);

    }
}
