package com.example.rocklct.bangumi.mybangumi.util;

import junit.framework.TestCase;

import java.util.List;

/**
 * Created by rocklct on 2016/4/26.
 */
public class HttpManagerTest extends TestCase {
    public void test(){
        new HttpManager(new HttpManager.OnConnectListener() {

            @Override
            public void OnSuccess(List result) {

            }

            @Override
            public void OnError(int tag) {

            }
        }).getCalendarJson("http://api.bgm.tv/calendar");
    }
}