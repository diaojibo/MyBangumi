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
import android.widget.ProgressBar;

import com.example.rocklct.bangumi.mybangumi.R;
import com.example.rocklct.bangumi.mybangumi.ui.adapter.ThumbnailAdapter;
import com.example.rocklct.bangumi.mybangumi.ui.bean.BaseBean;
import com.example.rocklct.bangumi.mybangumi.util.HttpManager;
import com.example.rocklct.bangumi.mybangumi.util.ImageLoader.OnScrollPauseListener;
import com.example.rocklct.bangumi.mybangumi.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rocklct on 2016/3/21.
 */
public class AnimationCalendar extends AbstractFragment implements HttpManager.OnConnectListener {

    private ThumbnailAdapter mAdapter;
    private GridLayoutManager mManager;
    private int title_position;
    private boolean isRefresh = false;
    private List<View> weekViews;
    private View weekViewItem;
    private int load_pages = 1;

    public AnimationCalendar() {
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
        initWeekViews();
        initData();
        return view;
    }

    //初始化星期views
    private void initWeekViews() {

        weekViews = new ArrayList();
        weekViews.add(weekViewItem.findViewById(R.id.calendar_monday));
        weekViews.add(weekViewItem.findViewById(R.id.calendar_tuesday));
        weekViews.add(weekViewItem.findViewById(R.id.calendar_wednesday));
        weekViews.add(weekViewItem.findViewById(R.id.calendar_thursday));
        weekViews.add(weekViewItem.findViewById(R.id.calendar_friday));
        weekViews.add(weekViewItem.findViewById(R.id.calendar_saturday));
        weekViews.add(weekViewItem.findViewById(R.id.calendar_sunday));



    }


    //用来加载最下面那一条加载更多动画的View
    private void initLoadView() {

    }

    public static AnimationCalendar getInstance() {
        AnimationCalendar fragment = new AnimationCalendar();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weekViewItem = LayoutInflater.from(getContext()).inflate(R.layout.calendar_item, null);
        isCreated = false;
    }

    @Override
    void initData() {
        mHttpManager.getAnimationCalendar();
    }

    private void initView() {
        mSwipeRefreshLayout.setEnabled(false);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new ThumbnailAdapter(getContext(), mData);
        mRecyclerView.setAdapter(mAdapter);
        mManager = new GridLayoutManager(getContext(), 3);
        mRecyclerView.setLayoutManager(mManager);
        mManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            //这是在根据类型调整gridLayout的间隔,如果是普通缩略图就是占据一份，否则3份
            @Override
            public int getSpanSize(int position) {
                int size = 1;
                int type = mAdapter.getItemViewType(position);

                //处理星期提示View
                if (type >= mAdapter.TYPE_WEEKDAY) {
                    size = mManager.getSpanCount();
                    return size;
                }
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


        //0-6分别把不同星期几的新番内容加载进去
        for (int i = 0; i < 7; i++) {
            Log.d("testhttp","succweeday"+i);
            mAdapter.addCustomView(weekViews.get(i), mData.size(), ThumbnailAdapter.TYPE_WEEKDAY + i + 1);
            mData.addAll((List<BaseBean>)result.get(i));
        }

        mAdapter.notifyDataSetChanged();

        //加载结束，调用停止加载动画显示界面
        Util.loadAnima(mProgressBar, mRecyclerView);


    }

    @Override
    public void OnError(int tag) {

    }

    public void load_thumbnail() {

    }
}
