package com.main.crazyandroid.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by Administrator on 2017/9/1.
 */

public class MyView extends View {

    public float currentX = 100;
    public float currentY = 100;

    public MyView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        p.setColor(Color.BLUE);
        canvas.drawCircle(currentX,currentY,15,p);
    }
}
