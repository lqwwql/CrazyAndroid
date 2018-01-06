package com.github.okhttp3practice;

import android.text.TextUtils;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018-01-06.
 */

public class HttpUrlConnectionTest {


    public static HttpURLConnection getHttpUrlConnection(String url){
        HttpURLConnection mHttpURLConnection = null;
        try {
            URL mUrl = new URL(url);
            mHttpURLConnection = (HttpURLConnection) mUrl.openConnection();
            //设置超时时间
            mHttpURLConnection.setConnectTimeout(15000);
            //设置读取超时时间
            mHttpURLConnection.setReadTimeout(15000);
            //设置请求参数
            mHttpURLConnection.setRequestMethod("POST");
            //添加Header
            mHttpURLConnection.setRequestProperty("Connection","Keep-Alive");
            //接收输入流
            mHttpURLConnection.setDoInput(true);
            //传递参数时需要开启此选项
            mHttpURLConnection.setDoOutput(true);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mHttpURLConnection;
    }

    //组织一下请求参数并将请求参数写入到输出流中
    public static void postParams(OutputStream output, List<NameValuePair> paramsList) throws IOException {
        StringBuilder mStringBuilder = new StringBuilder();
        for(NameValuePair pair : paramsList){
            if(!TextUtils.isEmpty(mStringBuilder)){
                mStringBuilder.append("&");
            }
            mStringBuilder.append(URLEncoder.encode(pair.getName(),"UTF-8"));
            mStringBuilder.append("=");
            mStringBuilder.append(URLEncoder.encode(pair.getValue(),"UTF-8"));
        }
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output,"UTF-8"));
        writer.write(mStringBuilder.toString());
        writer.flush();
        writer.close();
    }

    //传入组织好的请求参数，传给HttpURLCconnection的输出流，并开始连接
    public void useHttpUrlConnectionPost(String url){
        try {
            InputStream inputStream = null;
            HttpURLConnection mhHttpURLConnection = getHttpUrlConnection(url);
            List<NameValuePair> postParams = new ArrayList<>();
            //传入参数
            postParams.add(new BasicNameValuePair("username","root"));
            postParams.add(new BasicNameValuePair("password","root"));
            postParams(mhHttpURLConnection.getOutputStream(),postParams);
            mhHttpURLConnection.connect();
            inputStream = mhHttpURLConnection.getInputStream();
            int code = mhHttpURLConnection.getResponseCode();
            String respose = new HttpClientTest().converStreamToString(inputStream);
            Log.i("httpurlconnection", "请求状态码:" + code + "\n请求结果:\n" + respose);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
