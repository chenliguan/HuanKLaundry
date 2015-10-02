package com.guan.o2o.asynchttp;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * http客户端类静态访问器使它容易与Twitter’sAPI沟通
 *
 * @author Guan
 * @file com.guan.o2o.app
 * @date 2015/9/23
 * @Version 1.0
 */
public class MyAsyncHttpClient {

    private static final String BASE_URL = "http://www.baidu.com/";

    //创建一个static client
    private static AsyncHttpClient client=new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(url, params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(url, params, responseHandler);
    }
}
