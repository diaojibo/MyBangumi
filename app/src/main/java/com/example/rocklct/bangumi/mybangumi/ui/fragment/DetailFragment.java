package com.example.rocklct.bangumi.mybangumi.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.rocklct.bangumi.mybangumi.R;
import com.example.rocklct.bangumi.mybangumi.ui.adapter.DetailAdapter;
import com.example.rocklct.bangumi.mybangumi.ui.bean.BaseBean;
import com.example.rocklct.bangumi.mybangumi.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rocklct on 2016/5/6.
 */
public class DetailFragment extends AbstractFragment {

    private String id;
    DetailAdapter mAdapter;
    private List<BaseBean> data;

    public static DetailFragment newInstance(String id) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString("id", id);
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

        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.detail_swipe);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mProgressBar = (ProgressBar) view.findViewById(R.id.pb_detail);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_detail);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        data = new ArrayList<>();
        mAdapter = new DetailAdapter(getContext(), data);
        mRecyclerView.setAdapter(mAdapter);

        initData();
        return view;
    }

    @Override
    void initData() {
        mHttpManager.getDetailItem(id);
    }

    @Override
    public void OnSuccess(List result) {
        if (result.isEmpty()) {
            return;
        }

        if (isRefresh) {
            mData.clear();
            mAdapter.notifyDataSetChanged();
            isRefresh = false;

        }



        data.addAll(result);
        mAdapter.notifyDataSetChanged();
        Util.loadAnima(mProgressBar,mRecyclerView);
        mSwipeRefreshLayout.setRefreshing(false);

    }


    @Override
    public void OnError(int tag) {

    }

    @Override
    public void onRefresh() {
        isRefresh = true;
        Log.d("tt4","load data refresh");
        mHttpManager.getDetailItem(id);
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
