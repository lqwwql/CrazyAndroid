package com.github.okhttp3practice;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018-01-06.
 */

public class OkHttpTest {

    public static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");

    public void getAsynHttpGET() {
        //创建okhttpclient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //设置请求参数
        Request.Builder requestBuilder = new Request.Builder().url("http://www.baidu.com");
        //可以省略，默认是GET请求
        requestBuilder.method("GET", null);
        Request request = requestBuilder.build();
        //创建call
        Call mCall = mOkHttpClient.newCall(request);
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.cacheResponse() != null) {
                    String str = response.cacheResponse().toString();
                    Log.i("okhttp3", "cache-----" + str);
                } else {

                    String str = response.networkResponse().toString();
                    Log.i("okhttp3","network----"+str);
                    Log.i("okhttp3","body----"+response.body().string());
                }
            }
        });


    }

}
