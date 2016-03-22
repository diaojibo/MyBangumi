package com.example.rocklct.bangumi.mybangumi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/3/19.
 */
public class testFragment extends Fragment {

    int i=1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View m = inflater.inflate(R.layout.testlayout,container,false);
        return m;
    }
}
