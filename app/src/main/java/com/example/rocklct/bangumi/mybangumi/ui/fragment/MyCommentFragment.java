package com.example.rocklct.bangumi.mybangumi.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.rocklct.bangumi.mybangumi.R;
import com.example.rocklct.bangumi.mybangumi.util.HttpManager;

import java.util.List;

/**
 * Created by rocklct on 2016/5/6.
 */
public class MyCommentFragment extends Fragment implements HttpManager.OnConnectListener {

    private String id;
    protected boolean isRefresh = false;
    HttpManager mHttpmanager;
    ProgressDialog progressDialog;

    public static MyCommentFragment newInstance(String id) {
        MyCommentFragment fragment = new MyCommentFragment();
        Bundle args = new Bundle();
        args.putString("id", id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments().getString("id");
        mHttpmanager = new HttpManager(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState) {

        final View view = inflater.inflate(R.layout.fragment_subject_grade, container, false);
        Button button = (Button) view.findViewById(R.id.postComment);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initLoading();
                RatingBar ratingBar = (RatingBar) view.findViewById(R.id.comment_ratingbar);
                RadioGroup rg = (RadioGroup) view.findViewById(R.id.subject_status_field);
                int sid = rg.getCheckedRadioButtonId();

                EditText ev = (EditText) view.findViewById(R.id.subject_comment_field);
                float rating = ratingBar.getRating();
                String comment = ev.getText().toString();
                String status = "";
                switch (sid) {
                    case R.id.subject_status_watched:
                        status = "collect";
                        break;
                    case R.id.subject_status_watching:
                        status = "do";
                        break;
                    case R.id.subject_status_wish:
                        status = "wish";
                        break;
                    case R.id.subject_status_on_hold:
                        status = "on_hold";
                        break;
                    case R.id.subject_status_drop:
                        status = "drop";
                        break;

                }

                mHttpmanager.postComment(id, status, rating * 2, comment);

            }
        });

        return view;
    }

    public void initLoading() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }


    @Override
    public void OnSuccess(List result) {
        progressDialog.cancel();
        Toast.makeText(getContext(), "更新成功", Toast.LENGTH_LONG).show();

    }


    @Override
    public void OnError(int tag) {

    }
}
