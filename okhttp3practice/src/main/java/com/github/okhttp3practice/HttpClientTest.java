package com.github.okhttp3practice;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018-01-06.
 */

public class HttpClientTest {

    //创建httpclient
    private HttpClient createHttpClient(){
        HttpParams mdefaultParams = new BasicHttpParams();
        //设置连接超时时间
        HttpConnectionParams.setConnectionTimeout(mdefaultParams,15000);
        //设置请求超时时间
        HttpConnectionParams.setSoTimeout(mdefaultParams,15000);

        HttpConnectionParams.setTcpNoDelay(mdefaultParams,true);
        HttpProtocolParams.setVersion(mdefaultParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(mdefaultParams, HTTP.UTF_8);

        //持续握手
        HttpProtocolParams.setUseExpectContinue(mdefaultParams,true);
        HttpClient mHttpClient = new DefaultHttpClient(mdefaultParams);
        return mHttpClient;
    }

    //利用httpclient测试get请求
    public void useHttpClientGet(String url) {
        HttpGet mHttpGet = new HttpGet(url);
        mHttpGet.addHeader("Connection", "Keep-Alive");
        try {
            HttpClient mHttpClient = createHttpClient();
            HttpResponse mHttpResponse = mHttpClient.execute(mHttpGet);
            HttpEntity mHttpEntity = mHttpResponse.getEntity();
            int code = mHttpResponse.getStatusLine().getStatusCode();
            if (null != mHttpEntity) {
                InputStream mInputStream = mHttpEntity.getContent();
                String respose = converStreamToString(mInputStream);
                Log.i("wangshu", "请求状态码:" + code + "\n请求结果:\n" + respose);
                mInputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //利用httpclient测试get请求
    public void useHttpClientPost(String url){
        try {
            HttpPost mHttpPost = new HttpPost(url);
            mHttpPost.addHeader("Connection","Keep-Alive");

            HttpClient mtthpcHttpClient = createHttpClient();
            List<NameValuePair> postParams = new ArrayList<>();

            //设置传递参数
            postParams.add(new BasicNameValuePair("username","moon"));
            postParams.add(new BasicNameValuePair("password","1234"));
            mHttpPost.setEntity(new UrlEncodedFormEntity(postParams));

            HttpResponse mHttpResponse = mtthpcHttpClient.execute(mHttpPost);
            HttpEntity mHttpEntity  = mHttpResponse.getEntity();
            int code = mHttpResponse.getStatusLine().getStatusCode();
            if(null != mHttpEntity){
                InputStream is = mHttpEntity.getContent();
                String respose = converStreamToString(is);
                Log.i("httpclient test","请求状态码:" + code + "\n请求结果:\n" + respose);
                is.close();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String converStreamToString(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuffer sb = new StringBuffer();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        String respose = sb.toString();
        return respose;
    }
}
