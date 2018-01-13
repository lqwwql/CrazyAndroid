package com.github.okhttp3practice;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Administrator on 2018-01-12.
 */

public class OkhttpActivity extends Activity {

    private String geturl = "http://192.168.13.134:8080/YuDongReader/jsonGet";
    private String posturl = "http://192.168.13.134:8080/YuDongReader/jsonPost";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.okhttp_layout);

        findViewById(R.id.btn_jsonGet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*OkHttpClientTest GET请求*/
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        new OkHttpTest().getAsynHttpGET(geturl);
                    }
                }).start();
            }
        });

        findViewById(R.id.btn_jsonPost).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*OkHttpClientTest GET请求*/
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        new OkHttpTest().getAsynPost(posturl);
                    }
                }).start();
            }
        });

    }
}
