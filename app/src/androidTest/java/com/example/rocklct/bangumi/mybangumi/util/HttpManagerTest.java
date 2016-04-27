package com.example.rocklct.bangumi.mybangumi.util;

import com.example.rocklct.bangumi.mybangumi.constants.BangumiAPi;

import junit.framework.TestCase;

/**
 * Created by rocklct on 2016/4/26.
 */
public class HttpManagerTest extends TestCase {
    public void test(){
        new HttpManager(new HttpManager.OnConnectListener() {
            @Override
            public void OnSuccess(String result, int tag) {

            }

            @Override
            public void OnError(int tag) {

            }
        }).getThumbnailItem(BangumiAPi.getTopAnimation,0);
    }
}