package com.example.rocklct.bangumi.mybangumi.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.rocklct.bangumi.mybangumi.R;

import java.util.List;

/**
 * Created by rocklct on 2016/5/6.
 */
public class DetailFragment extends AbstractFragment{

    private String id;

    public static DetailFragment newInstance(String id){
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString("id",id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments().getString("id");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState) {

        View view = inflater.inflate(R.layout.fragment_detail,container,false);
        mProgressBar = (ProgressBar) view.findViewById(R.id.pb_detail);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_detail);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        return null;
    }

    @Override
    void initData() {

    }

    @Override
    public void OnSuccess(List result) {

    }

    @Override
    public void OnError(int tag) {

    }
}
