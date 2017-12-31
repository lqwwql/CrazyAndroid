package com.main.crazyandroid;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by Administrator on 2017/10/12.
 */

public class SQLiteActivity extends Activity {

    private SQLiteDatabase db;
    private Button insert;
    private Button read;
    private ListView listView;
    private EditText input;
    private EditText output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite_layout);
        insert = (Button) findViewById(R.id.insertbtn);
        read = (Button) findViewById(R.id.searchbtn);
        input = (EditText) findViewById(R.id.inputtext);
        output = (EditText) findViewById(R.id.outputtext);

        //创建或打开数据库,此处要用绝对路径,创建在当前APP路径下
        db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir()+"/my.db3",null);
        //创建表
        db.execSQL("drop table student");
        db.execSQL("create table student (id integer primary key autoincrement," +
                "name varchar(20))");
        //写入数据
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = input.getText().toString();
                db.execSQL("insert into student values(null,?)",new String[]{name});
            }
        });
        //读取数据
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = db.rawQuery("select * from student",null);
                cursor.moveToFirst();
                String name = cursor.getString(1);
                output.setText(name);
            }
        });

    }


}
