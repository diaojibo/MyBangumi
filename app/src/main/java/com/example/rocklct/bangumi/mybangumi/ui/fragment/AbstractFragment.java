package com.example.rocklct.bangumi.mybangumi.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.rocklct.bangumi.mybangumi.BangumiApp;
import com.example.rocklct.bangumi.mybangumi.ui.bean.BaseBean;
import com.example.rocklct.bangumi.mybangumi.util.HttpManager;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rocklct on 2016/3/24.
 */
public abstract class AbstractFragment extends Fragment implements HttpManager.OnConnectListener{
   public HttpManager mHttpManager;
   protected RecyclerView mRecyclerView;
   protected ProgressBar mProgressBar;
   protected List<BaseBean> mData;
   protected FloatingActionButton mFloatingActionButton;
   protected boolean isCreated = false;
   protected boolean isRefresh = false;
   public Gson mGson;
   protected SwipeRefreshLayout mSwipeRefreshLayout;
   public AbstractFragment(){
      mGson = BangumiApp.getmInstance().getGson();
      mHttpManager = new HttpManager(this);
      mData = new ArrayList<>();
   }

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      isCreated = true;
   }

   public abstract View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState);

   abstract void initData();






}
