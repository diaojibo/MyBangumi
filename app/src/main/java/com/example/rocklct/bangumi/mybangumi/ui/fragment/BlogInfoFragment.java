package com.example.rocklct.bangumi.mybangumi.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.rocklct.bangumi.mybangumi.R;
import com.example.rocklct.bangumi.mybangumi.ui.adapter.BlogInfoAdapter;
import com.example.rocklct.bangumi.mybangumi.ui.bean.BaseBean;
import com.example.rocklct.bangumi.mybangumi.util.ImageLoader.OnScrollPauseListener;
import com.example.rocklct.bangumi.mybangumi.util.Util;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rocklct on 2016/5/6.
 */
public class BlogInfoFragment extends AbstractFragment {

    private String id;
    private BlogInfoAdapter mAdapter;
    private List<BaseBean> data;
    private View loadView;
    private boolean isLoad = false;
    private TextView tv_more_information;
    private TextView tv_normal;
    private LinearLayout loading_layout;
    private int load_pages = 1;
    private LinearLayoutManager mManager;

    public static BlogInfoFragment newInstance(String id) {
        BlogInfoFragment fragment = new BlogInfoFragment();
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
        loadView = LayoutInflater.from(getContext()).inflate(R.layout.title_item, container, false);
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        mProgressBar = (ProgressBar) view.findViewById(R.id.pb_detail);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_detail);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        data = new ArrayList<>();
        mAdapter = new BlogInfoAdapter(getContext(), data);
        mRecyclerView.setAdapter(mAdapter);
        initView();
        initData();
        initLoadView();
        return view;
    }

    private void initView() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext()).build());
        mManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setOnScrollListener(new OnScrollPauseListener());
    }

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
                mHttpManager.getReview(id, load_pages);
            }
        });
    }

    @Override
    void initData() {
        mHttpManager.getReview(id, 1);
    }

    @Override
    public void OnSuccess(List result) {
        boolean isE = false;
        if (result.isEmpty()) {
            loadView.findViewById(R.id.loading_layout).setVisibility(View.GONE);
            loadView.findViewById(R.id.tv_more_information).setVisibility(View.GONE);
            loadView.findViewById(R.id.load_null).setVisibility(View.VISIBLE);
            isE = true;
        }

        if (isRefresh) {
            data.clear();
            mAdapter.notifyDataSetChanged();
            isRefresh = false;
        }

        if (isLoad) {
            data.remove(data.size() - 1);
            isLoad = false;
        }

        data.addAll(result);
        if (!isE) {
            loadView.findViewById(R.id.tv_more_information).setVisibility(View.VISIBLE);
            loadView.findViewById(R.id.loading_layout).setVisibility(View.GONE);
        }
        mAdapter.addCustomView(loadView, data.size(), BlogInfoAdapter.TYPE_LOAD);
        mAdapter.notifyDataSetChanged();
        Util.loadAnima(mProgressBar, mRecyclerView);

    }


    @Override
    public void OnError(int tag) {

    }
}
