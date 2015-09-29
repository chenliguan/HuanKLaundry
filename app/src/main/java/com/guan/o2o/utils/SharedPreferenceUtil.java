package com.guan.o2o.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.guan.o2o.common.Contant;

/**
 * @author Guan
 * @file com.guan.o2o.utils
 * @date 2015/9/19
 * @Version 1.0
 */
public class SharedPreferenceUtil {

    /**
     * 持久化用户登录信息
     */
    public static void sharedPreferences(Context context, String loginPhone, String loginCode) {
        //1、实例化SharedPreferences对象
        SharedPreferences preferences = context.getSharedPreferences(
                Contant.SHAREDPREFERENCES_NAME_LOGIN, Context.MODE_PRIVATE);
        //2、实例化SharedPreferences.Editor对象
        SharedPreferences.Editor editor = preferences.edit();
        //3、editor.put()存入数据
        editor.putString("loginPhone", loginPhone);
        editor.putString("loginCode", loginCode);
        //4、commit()提交修改
        editor.commit();
    }

    /**
     * 设置已经引导过了，下次启动不用再次引导
     */
    public static void sharedPreferences(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(
                Contant.SHAREDPREFERENCES_NAME_FIRST, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isFirstIn", false);
        editor.commit();
    }
}
