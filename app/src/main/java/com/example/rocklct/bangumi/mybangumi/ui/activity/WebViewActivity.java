package com.example.rocklct.bangumi.mybangumi.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.rocklct.bangumi.mybangumi.R;

/**
 * Created by rocklct on 2016/5/30.
 */

public class WebViewActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private WebView webView;
    private String url;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    public void initView() {
        setContentView(R.layout.activity_webview);
        webView = (WebView) findViewById(R.id.blog_webview);
        progressBar = (ProgressBar) findViewById(R.id.progressbar_webview);
        progressBar.setProgress(0);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        setProgressBarIndeterminateVisibility(true);
        setProgressBarVisibility(true);
        final Activity activity = this;
        WebChromeClient webChromeClient = new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
            }
        };
        WebViewClient webViewClient = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(url.equals("https://bgm.tv/")){
                    Toast.makeText(activity, "此文章已经不存在", Toast.LENGTH_LONG).show();
                    activity.finish();
                }
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.d("tt3",url);
                view.loadUrl("javascript:hideOther();adjustSc();");
                super.onPageFinished(view, url);
                webView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                webView.setVisibility(View.GONE);
                if (url != null) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        String myjs = adjustScreenJS();
                        view.loadUrl("javascript:" + myjs);
                    }
                    String fun2 = "javascript:function hideOther() {document.getElementById('headerNeue2').style.display='none';document.getElementById('footer').style.display='none'; document.getElementById('columnB').style.display='none'; document.getElementById('dock').style.display='none';}";
                    view.loadUrl(fun2);
                    view.loadUrl("javascript:window.onload=function(){hideOther();adjustSc();}");
                }
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                //super.onReceivedError(view, request, error);
                Toast.makeText(activity, "Error", Toast.LENGTH_LONG).show();
                Log.d("tt3", "error!");
            }
        };

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        }
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.setWebViewClient(webViewClient);
        webView.setWebChromeClient(webChromeClient);
        webView.loadUrl(url);
    }


    private static String adjustScreenJS() {
        String myjs = "function adjustSc() {" +
                "document.getElementById('wrapperNeue').style.minWidth='0';" +
                "document.getElementById('main').style.width='95%';" +
                "document.getElementsByClassName('columns')[0].style.width='100%';" +
                "document.getElementById('columnA').style.width='100%';" +
                "var els = document.getElementsByClassName('code');" +
                "for(i=0,l=els.length;i<l;i++){" +
                "els[i].style.maxWidth='95%';" +
                "}" +
                "var els2=document.getElementsByClassName('quote');" +
                "for(i=0,l=els2.length;i<l;i++){" +
                "els2[i].style.width='95%';" +
                "}" +
                "}";
        return myjs;


    }


}
