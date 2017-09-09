package com.main.crazyandroid;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/9/2.
 */

public class ConfigurationActivity extends Activity {

    private TextView tv_screen;
    private TextView tv_navi;
    private TextView tv_touch;
    private TextView tv_mnc;
    private Button btn_showInfo,btn_changeScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuration_layout);
        tv_screen = (TextView) findViewById(R.id.tv_screen);
        tv_navi = (TextView) findViewById(R.id.tv_navi);
        tv_touch = (TextView) findViewById(R.id.tv_touch);
        tv_mnc = (TextView) findViewById(R.id.tv_mnc);
        btn_showInfo = (Button) findViewById(R.id.btn_showInfo);
        btn_showInfo.setText("显示系统信息");
        btn_changeScreen = (Button) findViewById(R.id.btn_changeScreen);
        btn_changeScreen.setText("打开键盘");
        btn_changeScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Configuration config = getResources().getConfiguration();
                //非系统级设置，无法使用
                if(config.keyboard == Configuration.KEYBOARDHIDDEN_NO){
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInputFromWindow(v.getWindowToken(), 0, 0);
                    btn_changeScreen.setText("关闭键盘");
                }else if(config.keyboard == Configuration.KEYBOARDHIDDEN_YES){
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInputFromWindow(v.getWindowToken(), 0, 0);
                    btn_changeScreen.setText("打开键盘");
                }
            }
        });
        btn_showInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Configuration config = getResources().getConfiguration();
                String screen = config.orientation == Configuration.ORIENTATION_LANDSCAPE ? "横屏":"竖屏";
                String navi = config.orientation == Configuration.NAVIGATION_NONAV  ? "没有方向控制" :
                                config.orientation == Configuration.NAVIGATION_WHEEL ? "滚轮控制方向" :
                                config.orientation == Configuration.NAVIGATION_DPAD ? "方向键控制方向" : "轨迹球控制方向";
                String mnc = config.mnc + "";
                String touch = config.touchscreen == Configuration.TOUCHSCREEN_NOTOUCH  ?  "没有触摸方式" :
                                config.touchscreen == Configuration.TOUCHSCREEN_FINGER ? "手指触摸方式" :"没定义触摸方式";
                tv_screen.setText(screen);
                tv_navi.setText(navi);
                tv_mnc.setText(mnc);
                tv_touch.setText(touch);
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("ConfigurationActivity:","回调系统修改事件响应");
        String screen = newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE ? "横屏" : "竖屏";
        Toast.makeText(this,"系统屏幕发生改变"+"\n屏幕方向改为："+screen,Toast.LENGTH_LONG).show();
    }
}
