package com.main.crazyandroid;

import android.app.Activity;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/9/3.
 */

public class ClipDrawableActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clip_drawable_layout);

        ImageView imageView = (ImageView) findViewById(R.id.iv_clip);
        final ClipDrawable cd = (ClipDrawable) imageView.getDrawable();
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what == 0x123){
                    cd.setLevel(cd.getLevel()+200);
                }
            }
        };
        final Timer timer = new Timer();
        timer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        Message msg = new Message();
                        msg.what = 0x123;
                        handler.sendMessage(msg);
                        //level值在0-10000之间
                        if(cd.getLevel()>=10000){
                            timer.cancel();
                        }
                    }
                }
        ,0,100);
    }
}
