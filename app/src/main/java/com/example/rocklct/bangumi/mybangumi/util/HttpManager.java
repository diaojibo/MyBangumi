package com.example.rocklct.bangumi.mybangumi.util;

import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;

import com.example.rocklct.bangumi.mybangumi.ui.bean.RankBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/19.
 */
public class HttpManager {

    private OnConnectListener onConnectListener;
    private String access_token;
    private final String apikey = "";
    private static final String USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.80 Safari/537.36";

    public HttpManager(OnConnectListener onConnectListener) {
        this.onConnectListener = onConnectListener;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String result = msg.getData().getString("result");
            int tag = msg.what;
            if (result == null || result.isEmpty()) {
                onConnectListener.OnError(tag);
            } else {
                onConnectListener.OnSuccess(result, msg.what);
            }

        }
    };

    public interface OnConnectListener {
        void OnSuccess(String result, int tag);

        void OnError(int tag);
    }





    //这里是一个爬虫函数，去真正的bangumi网站爬去排行榜得到item的id号
    private List getTopItem(String url, int page) {
        url = url + "&page=" + page;
        List list = new ArrayList();

        try {
            Document doc = Jsoup.connect(url).get();
            Element section = doc.getElementById("browserItemList");
            Elements lists = section.getElementsByTag("li");
            int num = 0;
            for (Element li : lists) {
                num++;
                int rank = num + (page - 1) * 24;
                String item = (li.attr("id").split("_"))[1];
                list.add(new RankBean(rank,item));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
