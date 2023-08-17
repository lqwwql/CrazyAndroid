package com.main.crazyandroid.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.main.crazyandroid.R;

/**
 * Created by Administrator on 2017/9/2.
 */

public class DialogActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);

        Button showDialog = (Button) findViewById(R.id.btn_showDialog);
        showDialog.setText("打开对话框");
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        showDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog(builder);
            }
        });
    }
    //创建简单对话框
    private void getDialog(AlertDialog.Builder builder){
        builder.setIcon(R.drawable.ptt_1);
        builder.setTitle("消息对话框");
        builder.setMessage("this is a easy dialog");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView tv = (TextView) findViewById(R.id.tv_dialogText);
                tv.setText("用户点击了确定按钮");
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView tv = (TextView) findViewById(R.id.tv_dialogText);
                tv.setText("用户点击了取消按钮");
            }
        });
        builder.setNeutralButton("继续", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView tv = (TextView) findViewById(R.id.tv_dialogText);
                tv.setText("用户点击了继续按钮");
            }
        });
        builder.create().show();
    }


}
