package com.example.rocklct.bangumi.mybangumi.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.rocklct.bangumi.mybangumi.bean.BaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/24.
 */
public abstract class AbstractFragment extends Fragment {
   protected RecyclerView mRecyclerView;
   protected ProgressBar mProgressBar;
   protected List<BaseBean> mdata;
   protected FloatingActionButton mFloatingActionButton;
   protected boolean isCreated = false;
   protected boolean isRefresh = false;
   protected SwipeRefreshLayout mSwipeRefreshLayout;
   public AbstractFragment(){
      mdata = new ArrayList<>();
   }

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      isCreated = true;
   }

   public abstract View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState);

   abstract void initData();






}
