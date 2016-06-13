package com.example.rocklct.bangumi.mybangumi.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rocklct.bangumi.mybangumi.BangumiApp;
import com.example.rocklct.bangumi.mybangumi.R;
import com.example.rocklct.bangumi.mybangumi.ui.bean.LoginInfoBean;
import com.example.rocklct.bangumi.mybangumi.util.HttpManager;
import com.example.rocklct.bangumi.mybangumi.util.SessionManager;

import java.util.List;

/**
 * Created by rocklct on 2016/6/8.
 */

public class LoginActivity extends AppCompatActivity implements HttpManager.OnConnectListener {
    private EditText mUsernameField;
    private EditText mPasswordField;
    public HttpManager httpManager;
    ProgressDialog progressDialog;
    private SessionManager mSession = BangumiApp.getmInstance().getSession();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mUsernameField = (EditText) findViewById(R.id.username);
        mPasswordField = (EditText) findViewById(R.id.password);
        httpManager = new HttpManager(this);
    }

    public void initLoading() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    public void login(View view) {
        String username = mUsernameField.getText().toString();
        String password = mPasswordField.getText().toString();

        if (isInputsValid(username, password)) {
            initLoading();
            httpManager.tryLogin(username, password);
        }
    }

    private boolean isInputsValid(String username, String password) {
        boolean flag = true;

        if (username.trim().length() == 0) {
            flag = false;
            mUsernameField.setError(getString(R.string.error_username_must_not_be_empty));
        }

        if (password.trim().length() == 0) {
            flag = false;
            mPasswordField.setError(getString(R.string.error_password_must_not_be_empty));
        }

        return flag;
    }

    @Override
    public void OnSuccess(List result) {
        LoginInfoBean loginInfoBean = (LoginInfoBean) result.get(0);
        if (loginInfoBean.auth == null) {
            OnError(INFOERROR);
            return;
        } else {
            mSession.setIsLogin(true);
            mSession.setAuth(loginInfoBean.auth);
            mSession.setAuthEncode(loginInfoBean.auth_encode);
            mSession.setUserId(loginInfoBean.id);
            mSession.setUserNickname(loginInfoBean.nickname);
            String avatarurl = loginInfoBean.avatar.medium.replace("http:","https:");
            mSession.setUserAvatar(avatarurl);
        }

        Toast.makeText(this, "登陆成功,欢迎你,"+loginInfoBean.nickname, Toast.LENGTH_LONG).show();
        progressDialog.cancel();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void OnError(int tag) {
        progressDialog.cancel();
        if (tag == INFOERROR) {
            Toast.makeText(this, "用户名或者密码信息有误", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "网络错误", Toast.LENGTH_LONG).show();
        }
    }
}
