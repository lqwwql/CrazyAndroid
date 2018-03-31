package com.github.okhttp3practice;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javabean.Users;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018-01-06.
 */

public class OkHttpTest {

    public static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");
    private Context context;

    public OkHttpTest(){
    }


    public OkHttpTest(Context context){
        this.context =context;
    }

    public void getAsynHttpGET(String url) {
        //创建okhttpclient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //设置请求参数
        Request.Builder requestBuilder = new Request.Builder().url(url);
        //可以省略，默认是GET请求
        requestBuilder.method("GET", null);
        Request request = requestBuilder.build();
        //创建call
        Call mCall = mOkHttpClient.newCall(request);

        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.cacheResponse() != null) {
                    String str = response.cacheResponse().toString();
                    JSONObject jsonObject = com.alibaba.fastjson.JSON.parseObject(str);
                    Log.i("okhttp3", "cache-----" + str);
                    Log.i("okhttp3","userName----"+jsonObject.getString("userName"));
                } else {
                    String str = response.networkResponse().toString();
                    String body = response.body().string();
                    Users user = com.alibaba.fastjson.JSON.parseObject(body,Users.class);
                    Log.i("okhttp3","network----"+str);
                    Log.i("okhttp3","userName----"+user.getUserNickName());
                }
            }
        });
    }

    //异步post请求
    public void getAsynPost(String url){
        //第一步，都是okhttpclient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //第二步,创建FromBody
        FormBody mFormBody = new FormBody.Builder()
                .add("username","root")
                .add("password","root")
                .build();
        //第三步，创建Request请求
        Request request = new Request.Builder()
                .url(url)
                .post(mFormBody)
                .build();
        //第四步，创建call对象，建立连接，异步请求
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("okhttp3","请求结果"+response.body().string());
            }
        });
    }


    public void downloadImage(){
        //第一步，构建okhttpclient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();

        //第二步，构建request对象
        Request request = new Request.Builder()
                .url("https://upload-images.jianshu.io/upload_images/727790-ab051c0a28c922c9.png")
                .build();
        //第三部，建立练习，构建call任务,异步执行，回调
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream mInputStream = response.body().byteStream();
                FileOutputStream mFileOutputStream = null;

                mFileOutputStream = new FileOutputStream(mkdirFileDirectory(context));
                byte[] buffer = new byte[1024];
                int len = 0;
                while((len=mInputStream.read(buffer))!=-1){
                    mFileOutputStream.write(buffer,0,len);
                }
                mFileOutputStream.flush();
                Log.i("okhttp3","download file success!");
            }
        });
    }

    public void downloadBook(){
        //第一步，构建okhttpclient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();

        //第二步，构建request对象
        Request request = new Request.Builder()
                .url("http://localhost:8080/YuDongReader/static/books/book001.txt")
                .build();
        //第三部，建立练习，构建call任务,异步执行，回调
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream mInputStream = response.body().byteStream();
                FileOutputStream mFileOutputStream = null;

                File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "斗破苍穹.txt");//文件保存路径

                mFileOutputStream = new FileOutputStream(file);
                byte[] buffer = new byte[1024];
                int len = 0;
                while((len=mInputStream.read(buffer))!=-1){
                    mFileOutputStream.write(buffer,0,len);
                }
                mFileOutputStream.flush();
                Log.i("okhttp3","download file success!");
            }
        });
    }

    //判断是否有sdcard
    public boolean ifExistSdCard(){
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    //然后创建目录，存储文件
    public File mkdirFileDirectory(Context context) throws IOException {
        if (ifExistSdCard()) {
            File fileDir = new File(Environment.getExternalStorageDirectory()+"/okhttp3/");//自定义文件存放路径
            if(!fileDir.exists()){
                fileDir.mkdir();//创建新目录
            }
            File file = new File(fileDir,"lien.jpg");
            Log.i("okhttp3","file in sdcard");
            return file;
        } else {
            File appPath = context.getFilesDir();//获取当前APP运行路径:/data/data/com.github.okhttp3practice/files
            Log.i("okhttp3","appPath is : "+appPath.toString());

            File file = new File(appPath,"/lien.jpg");
            file.createNewFile();
            Log.i("okhttp3","file in appPath");
            return file;
        }
    }

}
