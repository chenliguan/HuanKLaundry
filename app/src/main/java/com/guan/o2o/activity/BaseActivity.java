package com.guan.o2o.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.guan.o2o.common.Constant;
import com.guan.o2o.utils.LogUtil;

/**
 * 基础类封装业务无关的方法
 *
 * @author Guan
 * @file com.guan.o2o.activity
 * @date 2015/8/14
 * @Version 1.0
 */
public class BaseActivity extends FragmentActivity {

    /**
     * 把最常用的与业务无关的方法封装,简化编码编写过程
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Toast公共方法
     * @param pMsg
     */
    public void showMsg(String pMsg) {
        Toast.makeText(BaseActivity.this, pMsg, Toast.LENGTH_SHORT).show();
    }

    /**
     * intent 跳转Activity公共方法
     * @param pClass
     */
    public void openActivity(Class<?> pClass) {
        Intent _intent = new Intent(this,pClass);
        startActivity(_intent);
    }

    /**
     * intent 跳转Activity并finish()公共方法
     * @param pClass
     */
    public void openActivityFn(Class<?> pClass) {
        Intent _intent = new Intent(this,pClass);
        startActivity(_intent);
        finish();
    }

    /**
     * startActivityForResult()发请求，跳转到Activity
     *
     * @param pClass
     * @param value
     * @param requestCode
     */
    public void requestActivity(Class<?> pClass, String value, int requestCode) {
        // 第二个参数是请求码,是一个唯一值
        Intent _intent = new Intent(this, pClass);
        _intent.putExtra(Constant.INTENT_PARAM, value);
        startActivityForResult(_intent, requestCode);
    }

    /**
     * startActivityForResult()返回Activity的setResult方法
     * @param value
     */
    public void setResultTo(int value) {
        Intent _intent = new Intent();
        _intent.putExtra(Constant.INTENT_KEY, value);
        setResult(RESULT_OK, _intent);
        finish();
    }

    /**
     * intent 最佳数据传值方法
     * @param context
     * @param pClass
     * @param data
     */
    public static void actionStart(Context context,Class<?> pClass, String data) {
        Intent intent = new Intent(context, pClass);
        intent.putExtra(Constant.INTENT_KEY, data);
        context.startActivity(intent);
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        // 0.0-1.0
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
    }
}
