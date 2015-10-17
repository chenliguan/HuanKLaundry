package com.guan.o2o.application;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guan.o2o.model.WashOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * 全局application，始终只存在一个示例,用于临时保存各种传值
 *
 * @author Guan
 * @file com.guan.o2o.application
 * @date 2015/9/28
 * @Version 1.0
 */
public class App extends Application {

    private static RequestQueue queue;
    public static List<WashOrder> washOrderList;

    @Override
    public void onCreate() {
        super.onCreate();
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
        /**
         * 洗衣篮订单集合(全局保存)
         */
        washOrderList = new ArrayList<WashOrder>();
    }

    //入口
    public static RequestQueue getQueue() {
        return queue;
    }

}
