package com.main.crazyandroid.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.main.crazyandroid.R;

/**
 * Created by Administrator on 2017/9/3.
 */

public class MenuResActivity extends Activity {

    private TextView tv_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_res_layout);
        tv_menu = (TextView) findViewById(R.id.tv_menu);
        registerForContextMenu(tv_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.context_menu,menu);
        menu.setHeaderIcon(R.drawable.pitt);
        menu.setHeaderTitle("请选择背景色");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.red:
                item.setChecked(true);
                tv_menu.setBackgroundColor(Color.RED);
                break;
            case R.id.green:
                item.setChecked(true);
                tv_menu.setBackgroundColor(Color.GREEN);
                break;
            case R.id.blue:
                item.setChecked(true);
                tv_menu.setBackgroundColor(Color.BLUE);
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item01:
                tv_menu.setText(item.getTitle());
            break;
            case R.id.item02:
                tv_menu.setText(item.getTitle());
            break;
            case R.id.item03:
                tv_menu.setText(item.getTitle());
            break;
            case R.id.item04:
                tv_menu.setText(item.getTitle());
            break;
            case R.id.normal_item:
                tv_menu.setText(item.getTitle());
            break;
            case R.id.color01:
                tv_menu.setBackgroundColor(Color.RED);
                item.setChecked(true);
            break;
            case R.id.color02:
                tv_menu.setBackgroundColor(Color.GREEN);
                item.setChecked(true);
            break;
            case R.id.color03:
                tv_menu.setBackgroundColor(Color.BLUE);
                item.setChecked(true);
            break;
        }
        return super.onOptionsItemSelected(item);
    }
}
