package com.example.rocklct.bangumi.mybangumi.util;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.rocklct.bangumi.mybangumi.constants.BangumiAPi;
import com.example.rocklct.bangumi.mybangumi.ui.bean.DetailItemBean;

import junit.framework.TestCase;

import java.util.List;

/**
 * Created by rocklct on 2016/4/26.
 */
public class HttpManagerTest extends TestCase {
    public void test() {
        String is = "";
        is = new HttpManager(new HttpManager.OnConnectListener() {

            @Override
            public void OnSuccess(List result) {

            }


            @Override
            public void OnError(int tag) {

            }
        }).getJson(BangumiAPi.getDetailItem + "265?responseGroup=large");
        DetailItemBean bean = JSON.parseObject(is, DetailItemBean.class);

        Log.d("testhttp2", "detail" + bean.id + "  " + bean.name_cn);
    }
}