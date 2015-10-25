package com.guan.o2o.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.guan.o2o.application.App;
import com.guan.o2o.common.Constant;

import java.io.IOException;

/**
 * @author Guan
 * @file com.guan.o2o.utils
 * @date 2015/9/19
 * @Version 1.0
 */
public class SharedPfeUtil {

    /**
     * 持久化用户登录信息
     */
    public static void sharedLoginInfo(Context context, String loginPhone, String loginCode) {
        //1、实例化SharedPreferences对象
        SharedPreferences preferences = context.getSharedPreferences(
                Constant.SHARED_NAME_LOGIN, Context.MODE_PRIVATE);
        //2、实例化SharedPreferences.Editor对象
        SharedPreferences.Editor editor = preferences.edit();
        //3、editor.put()存入数据
        editor.putString(Constant.SHARED_KEY_PHONE, loginPhone);
        editor.putString(Constant.SHARED_KEY_CODE, loginCode);
        //4、commit()提交修改
        editor.apply();
    }

    /**
     * 设置已经引导过了，下次启动不用再次引导
     */
    public static void sharedFirstLogin(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(
                Constant.SHARED_NAME_FIRST, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(Constant.SHARED_KEY_LOGIN, false);
        editor.apply();
    }

    /**
     * 本地化订单数据
     */
    public static void sharedOrderInfo(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(
                Constant.SHARED_NAME_ORDER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String listString = ConvertUtil.listToString(App.washOrderList);
        editor.putString(Constant.SHARED_KEY_ORDER, listString);
        editor.apply();
    }

}
