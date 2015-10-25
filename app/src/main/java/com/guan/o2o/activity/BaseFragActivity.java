package com.guan.o2o.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.guan.o2o.common.Constant;

/**
 * 基础类封装业务无关的方法
 *
 * @author Guan
 * @file com.example.guan.activity
 * @date 2015/10/1
 * @Version 1.0
 */
public abstract class BaseFragActivity extends FragmentActivity {
//        implements MoreFragment.OnClickListener {

    /**
     * 把最常用的与业务无关的方法封装，简化编码编写过程
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Toast公共方法
     *
     * @param pMsg
     */
    public void showMsg(String pMsg) {
        Toast.makeText(BaseFragActivity.this, pMsg, Toast.LENGTH_SHORT).show();
    }

    /**
     * intent 跳转Activity公共方法
     */
    public void openActivity(Class<?> pClass) {
        Intent _intent = new Intent(this, pClass);
        startActivity(_intent);
    }

    /**
     * startActivityForResult()跳转Activity请求方法
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
     * dialog 对话框公共方法
     */
    public void showAlterDialog() {

    }

}
