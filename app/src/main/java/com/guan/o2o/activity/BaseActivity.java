package com.guan.o2o.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * @author Guan
 * @file com.example.guan.activity
 * @date 2015/8/14
 * @Version 1.0
 */
public class BaseActivity extends Activity {

    /**
     * 把最常用的与业务无关的方法封装，简化编码编写过程
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
     */
    public void openActivity(Class<?> pClass) {

        Intent _intent = new Intent(this,pClass);
        startActivity(_intent);
    }

    /**
     * dialog 对话框公共方法
     */
    public void showAlterDialog() {

    }

}
