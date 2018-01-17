package com.github.okhttp3practice;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Administrator on 2018-01-12.
 */

public class OkhttpActivity extends Activity {

    private String geturl = "http://192.168.13.134:8080/YuDongReader/jsonGet";
    private String posturl = "http://192.168.13.134:8080/YuDongReader/jsonPost";
    private OkHttpManager okHttpManager;
    private TextView txt_result;
    private Button getJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.okhttp_layout);

        getJson = (Button) findViewById(R.id.btn_jsonGet);
        txt_result = (TextView) findViewById(R.id.txt_jsonResult);
        txt_result.setText("connecting...");
        okHttpManager = OkHttpManager.getIstance();

        getJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                okHttpManager.asyncJsonStringByUrl(geturl, new OkHttpManager.JsonResultCallBack() {
                    @Override
                    public void onResponse(String result) {
                        Log.i("okhttp3", "result is : " + result);
                        if(txt_result != null){
                            if (result != null && !result.equals("")) {
                                txt_result.setText(result);
                                Log.i("okhttp3","set text success!");
                            }
                        }else{
                            Log.i("okhttp3","textview is null");
                        }
                    }
                });
            }
        });

        findViewById(R.id.btn_jsonPost).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*OkHttpClientTest POST请求*/
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
