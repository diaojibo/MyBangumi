package com.example.rocklct.bangumi.mybangumi.ui.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by rocklct on 2016/5/26.
 */
public class SearchActvity extends AppCompatActivity {




    public void init(){
        String query;
        Intent intent = getIntent();
        if(Intent.ACTION_SEARCH.equals(getIntent().getAction())){
            query = intent.getStringExtra(SearchManager.QUERY);
        }else {
            query = getIntent().getStringExtra("query");
        }
        Log.d("searchtest","search into new activity");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }
}
