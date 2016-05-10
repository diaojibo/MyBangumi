package com.example.rocklct.bangumi.mybangumi.util;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.rocklct.bangumi.mybangumi.constants.BangumiAPi;
import com.example.rocklct.bangumi.mybangumi.ui.bean.DetailItemBean;

import junit.framework.TestCase;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


    public void testreview(){
        new HttpManager(new HttpManager.OnConnectListener() {
            @Override
            public void OnSuccess(List result) {

            }

            @Override
            public void OnError(int tag) {

            }
        }).getReview("13",1);
    }

    public void testzhengze(){
        String pattern = "sstars([0-9]*)";
        String tests = "sstars10 starsinfo";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(tests);
        if(m.find()){
            Log.d("tt2",tests);
            Log.d("tt2",m.group(0));
            Log.d("tt2",m.group(1));
        }
    }
}