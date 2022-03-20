package com.main.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.main.crazyandroid.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/9/7.
 */

public class MoveBackgroundView extends View {
    //背景图高度
    final int BACK_HEIGHT = 1700;
    //放置背景图和飞机图片
    private Bitmap back;
    private Bitmap plane;
    //图片开始位置
    final int WIDTH = 520;
    final int HEIGHT = 840;
    //图片变化高度
    private int STARTY = BACK_HEIGHT - HEIGHT;

    public MoveBackgroundView(Context context) {
        super(context);
        init(context);
    }

    public MoveBackgroundView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        //加载图片
        back = BitmapFactory.decodeResource(context.getResources(), R.drawable.back);
        plane = BitmapFactory.decodeResource(context.getResources(), R.drawable.plane);
        //处理背景移动
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0x123) {
                    if (STARTY <= 0) {
                        STARTY = BACK_HEIGHT - HEIGHT;
                    }else {
                        STARTY -= 3;
                    }
                }
                invalidate();
            }
        };
        //启动定时器
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0x123);
            }
        },0,100);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap newbitmap = Bitmap.createBitmap(back,0,STARTY,WIDTH,HEIGHT);
        canvas.drawBitmap(newbitmap,0,0,null);
        canvas.drawBitmap(plane,160,380,null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
