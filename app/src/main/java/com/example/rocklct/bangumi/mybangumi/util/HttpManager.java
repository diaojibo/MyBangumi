package com.example.rocklct.bangumi.mybangumi.util;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.rocklct.bangumi.mybangumi.constants.BangumiAPi;
import com.example.rocklct.bangumi.mybangumi.ui.adapter.DetailAdapter;
import com.example.rocklct.bangumi.mybangumi.ui.bean.BaseBean;
import com.example.rocklct.bangumi.mybangumi.ui.bean.CalendarItemsBean;
import com.example.rocklct.bangumi.mybangumi.ui.bean.DetailItemBean;
import com.example.rocklct.bangumi.mybangumi.ui.bean.ThumbnailBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rocklct on 2016/4/19.
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
            Bundle bundle = msg.getData();
            String type = bundle.getString("getType");
            String result = bundle.getString("result");
            int tag = msg.what;

            //分不同的情况handler发送处理
            if (result == null || result.isEmpty()) {
                onConnectListener.OnError(tag);
            } else if (type == "getTop") {
                List<BaseBean> list = bundle.getParcelableArrayList("data");
                onConnectListener.OnSuccess(list);

            } else if (type == "getCalendar") {
                List list = new ArrayList();
                for (int i = 1; i <= 7; i++) {
                    List<BaseBean> tb = bundle.getParcelableArrayList("day" + i);
                    list.add(tb);
                }
                onConnectListener.OnSuccess(list);
            } else if (type == "getDetail") {

                List<BaseBean> resultlist = bundle.getParcelableArrayList("detail");
                onConnectListener.OnSuccess(resultlist);
            }

        }
    };

    public interface OnConnectListener {
        //        void OnSuccess(String result, int tag);
        void OnSuccess(List result);

        void OnError(int tag);
    }


    public List<CalendarItemsBean.CalendarBean> getCalendarItem() {
        String calendarjson = getCalendarJson(BangumiAPi.getCalendar);
        CalendarItemsBean clb = JSON.parseObject(calendarjson, CalendarItemsBean.class);
        return clb.weekitem;
    }


    public String getJson(String murl) {
        String result = "";
        try {
            URL url = new URL(murl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            result = readIs(is);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("httptest", "{\"weekitem:\"" + result + "}");

        return result;
    }

    public String getCalendarJson(String murl) {
        String result = "";
        result = getJson(murl);
        return "{\"weekitem\":" + result + "}";
    }

    //从inputstream输入流转换成字符串
    private String readIs(InputStream is) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(is, "UTF-8");
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuffer sb = new StringBuffer();
        String tempLine = null;
        while ((tempLine = reader.readLine()) != null) {
            sb.append(tempLine);
            sb.append("\r\n");
        }
        return sb.toString();
    }


    //这里是一个爬虫函数，去真正的bangumi网站爬去排行榜得到item的id号
    public List getThumbnailItem(String url, int page) {
        url = url + "&page=" + page;
        List list = new ArrayList();

        try {
            Document doc = Jsoup.connect(url).get();
            Element section = doc.getElementById("browserItemList");
            Elements lists = section.getElementsByTag("li");
            int num = 0;
            for (Element li : lists) {
                float rate = 0;
                String imgurl = "";
                String title = "";

                //爬取页面获取评分
                Elements rate_es = li.getElementsByClass("fade");
                for (Element rate_e : rate_es) {
                    rate = Float.parseFloat(rate_e.text());
                }

                //抓取图片的链接
                Elements imgs = li.getElementsByTag("img");
                for (Element img : imgs) {
                    imgurl = img.attr("src").replace("/s/", "/c/");
                    imgurl = "https:" + imgurl;
                }

                Elements title_es = li.getElementsByClass("l");
                for (Element title_e : title_es) {
                    title = title_e.text();
                }


                Log.d("testhttp", String.valueOf(rate));
                Log.d("testhttp", imgurl);
                Log.d("testhttp", title);

                String item_id = (li.attr("id").split("_"))[1];
                ThumbnailBean thumbnailBean = new ThumbnailBean(title, rate, imgurl, item_id);
                num++;
                int rank = num + (page - 1) * 24;
//                thumbnailBean.rank = rank;
                list.add(thumbnailBean);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }


    //获取某个条目的详细综述信息
    public void getDetailItem(String id) {
        final String mid = id;
        new Thread() {
            @Override
            public void run() {
                super.run();
                String detailJson = getJson(BangumiAPi.getDetailItem + mid + "?responseGroup=large");
                Log.d("errorjson", detailJson);
                DetailItemBean bean = JSON.parseObject(detailJson, DetailItemBean.class);
                if (bean.name_cn == null) {
                    bean.name_cn = bean.name;
                }
                if (bean != null) {
                    Message msg = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("result", "succ");
                    bundle.putString("getType", "getDetail");

                    DetailItemBean.DeatilInfoBean dtb = bean.getinfobean();
                    List<BaseBean> list = new ArrayList<BaseBean>();
                    dtb.setView_type(DetailAdapter.TYPE_HEADER);
                    list.add(dtb);
                    if (bean.crt != null) {

                        list.addAll(bean.crt);
                    }


                    bundle.putParcelableArrayList("detail", (ArrayList<BaseBean>) list);
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }
            }
        }.start();
    }


    //开启新进程获取当前新番
    public void getAnimationCalendar() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                List<CalendarItemsBean.CalendarBean> list = getCalendarItem();
                if (list != null) {
                    Message msg = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("result", "succ");
                    bundle.putString("getType", "getCalendar");
                    List weekitems = new ArrayList();
                    int weekday = 0;

                    //从JSON解析出的Bean对象中抽取信息组装成需要的缩略图Bean对象
                    for (CalendarItemsBean.CalendarBean cb : list) {
                        List tempweek = new ArrayList();
                        weekday++;
                        for (CalendarItemsBean.CalendarBean.Item it : cb.items) {
                            String title = it.name_cn;
                            String id = it.id;
                            float score = it.rating.score;
                            Log.d("testhttp", title + " " + id + " " + score);
                            String imgurl = "null";
                            if (it.images != null) {
                                String tempurl = it.images.common.split(":")[1];
                                imgurl = "https:" + tempurl;
                            }
                            ThumbnailBean tb = new ThumbnailBean(title, score, imgurl, id);
                            tempweek.add(tb);
                        }
                        bundle.putParcelableArrayList("day" + String.valueOf(weekday), (ArrayList<BaseBean>) tempweek);
                    }
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }
            }
        }.start();
    }

    public void getTopAnimation(int page) {
        final int mpage = page;
        new Thread() {
            @Override
            public void run() {
                super.run();
                List list = getThumbnailItem(BangumiAPi.getTopAnimation, mpage);
                if (list != null) {
                    Message msg = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("getType", "getTop");
                    bundle.putString("result", "succ");
                    bundle.putParcelableArrayList("data", (ArrayList<BaseBean>) list);
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }

            }
        }.start();
    }


}
