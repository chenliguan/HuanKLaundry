package com.guan.o2o.volley;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * 创建回调抽象类：抽象出成功的监听和失败的监听,用来回调信息（相当于：回调接口）
 *
 * @author Guan
 * @file com.guan.o2o.volley
 * @date 2015/9/28
 * @param <T>
 * @Version 1.0
 */
public abstract class VolleyHandler<T> {

    public Response.Listener<T> reqLis;
    public Response.ErrorListener reqErr;
    public abstract void reqSuccess(T response);
    public abstract void reqError(String error);

    public VolleyHandler() {
        // 初始化变量
        reqLis = new reqListener();
        reqErr = new reqErrorListener();
    }

    /**
     * 成功后的监听
     */
    public class reqListener implements Response.Listener<T> {

        @Override
        public void onResponse(T response) {
            // 使用抽象函数设置回调函数 reqSuccess
            reqSuccess(response);
        }
    }

    /**
     * 失败后的监听
     */
    public class reqErrorListener implements Response.ErrorListener {

        @Override
        public void onErrorResponse(VolleyError error) {
            // 设置回调函数 使用抽象方法ReqError
            reqError(error.getMessage());
        }
    }
}
