package com.main.crazyandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.main.contentprovider.ContentProviderActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnExpandListView(View view){
        startActivity(new Intent(this,ExpandedListViewActivity.class));
    }

    public void btnDialog(View view){
        startActivity(new Intent(this,DialogActivity.class));
    }

    public void btnConfiguration(View view){
        startActivity(new Intent(this,ConfigurationActivity.class));
    }

    public void btnHandler(View view){
        startActivity(new Intent(this,HandlerActivity.class));
    }

    public void btnClipDrawable(View view){
        startActivity(new Intent(this,ClipDrawableActivity.class));
    }

    public void btnMenuRes(View view){
        startActivity(new Intent(this,MenuResActivity.class));
    }

    public void btnDoubleBufferDraw(View view){
        startActivity(new Intent(this,DoubleBufferDrawActivity.class));
    }

    public void btnMoveBackground(View view){
        startActivity(new Intent(this,MoveBackgroundActivity.class));
    }

    public void btnSqlite(View view){
        startActivity(new Intent(this,SQLiteActivity.class));
    }

    public void btnContentprovider(View view){
        startActivity(new Intent(this,ContentProviderActivity.class));
    }

}
