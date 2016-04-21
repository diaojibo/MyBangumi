package com.example.rocklct.bangumi.mybangumi.util;

import android.os.Handler;
import android.os.Message;

import java.util.List;

/**
 * Created by Administrator on 2016/4/19.
 */
public class HttpManager {

    private OnConnectListener onConnectListener;
    private String access_token;
    private final String apikey = "";
    private static final String USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.80 Safari/537.36";
    public HttpManager(OnConnectListener onConnectListener){
        this.onConnectListener = onConnectListener;
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String result = msg.getData().getString("result");
            int tag = msg.what;
            if(result == null || result.isEmpty()){
                onConnectListener.OnError(tag);
            }else {
                onConnectListener.OnSuccess(result,msg.what);
            }

        }
    };

    public interface OnConnectListener{
        void OnSuccess(String result,int tag);
        void OnError(int tag);
    }

    private List getTopItem(String url,int page){


        return null;
    }
}
