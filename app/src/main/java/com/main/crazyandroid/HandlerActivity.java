package com.main.crazyandroid;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/9/2.
 */

public class HandlerActivity extends Activity {

    private int images[] = {R.drawable.ptt_1,R.drawable.ptt_2,R.drawable.ptt_3,R.drawable.ptt_4};
    private int currentID = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler_layout);
        final ImageView imageView = (ImageView) findViewById(R.id.im_handler);
        final Handler myhandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what==0x123){
                    imageView.setImageResource(images[currentID++]);
                    if(currentID>=4){
                        currentID = 0;
                    }
                }
            }
        };
        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        Message msg = new Message();
                        msg.what = 0x123;
                        myhandler.sendMessage(msg);
                    }
                }
        ,0,500);
    }
}
