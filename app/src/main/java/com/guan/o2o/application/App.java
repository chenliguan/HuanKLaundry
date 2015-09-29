package com.guan.o2o.application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guan.o2o.utils.LogUtil;

import android.app.Application;
import android.util.Log;

/**
 * 全局application,用于临时保存各种传值,服务器url
 * 保持程序运行过程中该类始终只存在一个示例
 *
 * @author Guan
 * @file com.guan.o2o.application
 * @date 2015/9/28
 * @Version 1.0
 */
public class App extends Application {

    private static RequestQueue queue;

    @Override
    public void onCreate() {
        super.onCreate();

        LogUtil.v("TAG","App onCreate");

        /**
         * 建立Volley请求队列(双重校验锁方法)
         */
        if (queue == null) {
            synchronized (App.class) {
                if (queue == null) {
                    queue = Volley.newRequestQueue(getApplicationContext());
                }
            }
        }
    }

    //入口
    public static RequestQueue getQueue() {
        return queue;
    }

}
