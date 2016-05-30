package com.example.rocklct.bangumi.mybangumi.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by rocklct on 2016/5/30.
 */

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;
    private String url;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    public void initView() {
        webView = new WebView(this);
        setContentView(webView);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        WebViewClient webViewClient = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                super.shouldOverrideUrlLoading(view,url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                view.loadUrl("javascript:hideOther();");
                super.onPageFinished(view, url);
                view.loadUrl("javascript:hideOther();");
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                if (url != null) {
                    String fun = "javascript:function getClass(parent,sClass) { var aEle=parent.getElementsByTagName('div'); var aResult=[]; var i=0; for(i<0;i<aEle.length;i++) { if(aEle[i].className==sClass) { aResult.push(aEle[i]); } }; return aResult; } ";
//                    view.loadData("<style>ima{}</style>");
                    view.loadUrl(fun);
                    String fun2 = "javascript:function hideOther() {document.getElementById('headerNeue2').style.display='none';document.getElementById('footer').style.display='none'; document.getElementById('columnB').style.display='none'; document.getElementById('dock').style.display='none';}";
                    view.loadUrl(fun2);
                    view.loadUrl("javascript:window.onload=hideOther;");
                    Log.d("tt3","insertsucc");
                }
                super.onPageStarted(view, url, favicon);
            }
        };

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setBlockNetworkImage(false);
//        webView.getSettings().setLoadsImagesAutomatically(true);
//        webView.getSettings().setUseWideViewPort(true);
        webView.setWebViewClient(webViewClient);
        webView.loadUrl(url);
    }

}
