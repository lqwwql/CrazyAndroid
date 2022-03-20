package com.main.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2017/9/6.
 */

public class DoubleBufferDrawView extends View {


    float preX;
    float preY;
    private Path path;
    public Paint paint = null;
    //内存bitmap的宽和高
    final int VIEW_WIDTH = 320;
    final int VIEW_HEIGHT =480;

    //定义一张图片作为内存的的缓存
    Bitmap cacheBitmap = null;
    //定义cacheBitmap上的canvas对象
    Canvas cachecanvas = null;

    public DoubleBufferDrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        cacheBitmap = Bitmap.createBitmap(VIEW_WIDTH,VIEW_HEIGHT, Bitmap.Config.ARGB_8888);
        cachecanvas = new Canvas();
        path = new Path();
        //设置cacheCanvas绘制到cacheBitmap上
        cachecanvas.setBitmap(cacheBitmap);
        //设置画笔的颜色和风格
        paint = new Paint(Paint.DITHER_FLAG);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        //反锯齿
        paint.setAntiAlias(true);
        paint.setDither(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取拖动事件的发生位置
        float x = event.getX();
        float y = event.getY();
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x,y);
                preX = x;
                preY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                path.quadTo(preX,preY,x,y);
                preX = x;
                preY = y;
                break;
            case MotionEvent.ACTION_UP:
                cachecanvas.drawPath(path,paint);
                path.reset();
                break;
        }
        invalidate();
        //返回true表示该方法已经完全处理事件，不再往上传递
        return true;
    }

    @Override
    public void onDraw(Canvas canvas) {
        Paint bmpPaint = new Paint();
        //沿着path路径将内存中的cacheBitmap绘制到view中
        canvas.drawBitmap(cacheBitmap,0,0,bmpPaint);
        canvas.drawPath(path,paint);
    }

}
