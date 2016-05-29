package com.example.rocklct.bangumi.mybangumi.ui.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.rocklct.bangumi.mybangumi.R;
import com.example.rocklct.bangumi.mybangumi.ui.fragment.AnimationTop;
import com.example.rocklct.bangumi.mybangumi.ui.fragment.SearchFragment;

/**
 * Created by rocklct on 2016/5/26.
 */
public class SearchActvity extends AbstractSwipeActivity {


    Toolbar toolbar;

    public void init(){
        String query;
        Intent intent = getIntent();
        if(Intent.ACTION_SEARCH.equals(getIntent().getAction())){
            query = intent.getStringExtra(SearchManager.QUERY);
        }else {
            query = getIntent().getStringExtra("query");
        }
        Log.d("searchtest","search into new activity");

        Fragment fragment = new SearchFragment();
        //put search query into the bundle sent to the fragment
        Bundle bundle = new Bundle();
        bundle.putString("SearchName",query);
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.search_container,fragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.search_toolbar);
        setSupportActionBar(toolbar);
        init();
    }
}
