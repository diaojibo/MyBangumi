package com.example.rocklct.bangumi.mybangumi.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.rocklct.bangumi.mybangumi.R;
import com.example.rocklct.bangumi.mybangumi.adapter.AnimationAllAdapter;
import com.example.rocklct.bangumi.mybangumi.util.HttpManager;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by rocklct on 2016/3/21.
 */
public class AnimationAll extends AbstractFragment implements HttpManager.OnConnectListener {

    private AnimationAllAdapter mAdapter;
    private GridLayoutManager mManager;
    private int title_position;
    private String city;
    private boolean isRefresh = false;
    private final Gson gson = new Gson();
    private View loadView;
    private TextView tv_complete;
    private TextView tv_normal;
    private LinearLayout loading_layout;

    public AnimationAll() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //一开始的时候是一个正在加载的progressbar
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        mHttpManager = new HttpManager(this);

        mData = new ArrayList<>();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_base);
        mProgressBar = (ProgressBar) view.findViewById(R.id.pb_base);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_base);
        mFloatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab_base);
        initView();

        return view;
    }


    public static AnimationAll getInstance(){
        AnimationAll fragment = new AnimationAll();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadView = LayoutInflater.from(getContext()).inflate(R.layout.title_item,null);
        isCreated = false;
    }

    @Override
    void initData() {

    }

    private void initView() {
        mSwipeRefreshLayout.setEnabled(false);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new AnimationAllAdapter(getContext(),mData);

    }

    @Override
    public void OnSuccess(String result, int tag) {

    }

    @Override
    public void OnError(int tag) {

    }
}