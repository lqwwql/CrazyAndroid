package com.github.okhttp3practice;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018-01-16.
 */

public class OkHttpManager {

    /**
     * OkHttpManager实例创建时创建该实例，而且只有一次创建
     * */
    private OkHttpClient mOkHttpClient;
    /**
     * 在子线程连接网络
     * */
    private Handler mHandler;

    /**
     * 外部无法通过new来创建对象，只能调用getInstance获取唯一对象
     * */
    private OkHttpManager(){
        mOkHttpClient = new OkHttpClient();
        mHandler = new Handler(Looper.getMainLooper());
    }

    /**
     * 静态内部类实现懒汉单例模式，提供唯一一个的okhttpmanager实例
     * */
    private static class LazyLoader {
        private static final OkHttpManager OK_HTTP_MANAGER = new OkHttpManager();
    }
    /**
     * 保持系统只有一个okhttpmanager对象，也就是一个okhttpclient对象
     * @return OkHttpManager唯一对象
     * */
    public static final OkHttpManager getIstance(){
        return LazyLoader.OK_HTTP_MANAGER;
    }

    /**
     * 异步更新UI线程
     * @param  jsonValue 服务器返回的json数据
     * @param  callBack 自定义的回调接口
     * */
    private void onSuccessJsonStringMethod(final String jsonValue,final JsonResultCallBack callBack){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if(callBack!=null){
                    callBack.onResponse(jsonValue);//在这里把数据传到前台activity
                }
            }
        });
    }

    /**
     * 异步GET请求，返回json数据
     * @param url 访问服务器的url
     * @param callBack 自定义的回调接口，由自己实现具体逻辑
     * */
    public void asyncJsonStringByUrl(String url,final JsonResultCallBack callBack){
        final Request request = new Request.Builder().url(url).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //访问失败时调用
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response!=null&&response.isSuccessful()){
                    onSuccessJsonStringMethod(response.body().string(),callBack);//把数据传出去
                }
            }
        });
    }

    /**
     * 创建接口回调，把okhttp的数据传出来用
     * */
    public interface JsonResultCallBack{
        void onResponse(String result);//具体方法逻辑由自己实现这个接口时定义，可在里面更新UI
    }

}
