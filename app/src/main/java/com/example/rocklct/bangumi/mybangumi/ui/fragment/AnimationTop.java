package com.example.rocklct.bangumi.mybangumi.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.rocklct.bangumi.mybangumi.R;
import com.example.rocklct.bangumi.mybangumi.ui.adapter.ThumbnailAdapter;
import com.example.rocklct.bangumi.mybangumi.ui.bean.ThumbnailBean;
import com.example.rocklct.bangumi.mybangumi.util.HttpManager;
import com.example.rocklct.bangumi.mybangumi.util.ImageLoader.OnScrollPauseListener;
import com.example.rocklct.bangumi.mybangumi.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rocklct on 2016/3/21.
 */
public class AnimationTop extends AbstractFragment implements HttpManager.OnConnectListener {

    private ThumbnailAdapter mAdapter;
    private GridLayoutManager mManager;
    private int title_position;
    private String city;
    private boolean isRefresh = false;
    private boolean isLoad = false;
    private View loadView;
    private TextView tv_more_information;
    private TextView tv_normal;
    private LinearLayout loading_layout;
    private int load_pages = 1;

    public AnimationTop() {
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
        initData();
        initLoadView();
        return view;
    }


    //用来加载最下面那一条加载更多动画的View
    private void initLoadView() {
        loading_layout = (LinearLayout) loadView.findViewById(R.id.loading_layout);
        tv_more_information = (TextView) loadView.findViewById(R.id.tv_more_information);
        tv_more_information.setVisibility(View.VISIBLE);
        tv_more_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_more_information.setVisibility(View.GONE);
                loading_layout.setVisibility(View.VISIBLE);
                load_pages++;
                isLoad = true;
                mHttpManager.getTopAnimation(load_pages);
            }
        });
    }

    public static AnimationTop getInstance() {
        AnimationTop fragment = new AnimationTop();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadView = LayoutInflater.from(getContext()).inflate(R.layout.title_item, null);
        isCreated = false;
    }

    @Override
    void initData() {
        mHttpManager.getTopAnimation(1);
    }

    private void initView() {
        mSwipeRefreshLayout.setEnabled(false);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new ThumbnailAdapter(getContext(), mData);
        mRecyclerView.setAdapter(mAdapter);
        mManager = new GridLayoutManager(getContext(), 3);
        mRecyclerView.setLayoutManager(mManager);
        mManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int size = 1;
                switch (mAdapter.getItemViewType(position)) {
                    case ThumbnailAdapter.TYPE_TITLE:
                    case ThumbnailAdapter.TYPE_LOAD:
                        size = mManager.getSpanCount();
                        break;
                }
                return size;
            }
        });
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setOnScrollListener(new OnScrollPauseListener());

    }

    @Override
    public void OnSuccess(List result) {
        if (result.isEmpty()) {
            return;
        }
        Log.d("test", "onsuccess");
        if (isRefresh) {
            mData.clear();
            mAdapter.notifyDataSetChanged();
            isRefresh = false;
        }
        if (isLoad) {
            mData.remove(mData.size() - 1);
            isLoad = false;
        }
        ThumbnailBean s = (ThumbnailBean) result.get(0);
        mData.addAll(result);

        loadView.findViewById(R.id.tv_more_information).setVisibility(View.VISIBLE);
        loadView.findViewById(R.id.loading_layout).setVisibility(View.GONE);

        //插入最后的加载条view模板，把自定义视图插到最后一个位置显示出来
        mAdapter.addCustomView(loadView, mData.size(), ThumbnailAdapter.TYPE_LOAD);
        mAdapter.notifyDataSetChanged();
        Util.loadAnima(mProgressBar, mRecyclerView);


    }

    @Override
    public void OnError(int tag) {

    }

    public void load_thumbnail() {

    }
}
