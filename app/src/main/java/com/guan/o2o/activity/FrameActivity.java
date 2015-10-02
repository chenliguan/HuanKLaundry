package com.guan.o2o.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.guan.o2o.R;

/**
 * 框架类封装业务相关的方法
 *
 * @author Guan
 * @file com.example.guan.activity
 * @date 2015/8/14
 * @Version 1.0
 */
public class FrameActivity extends BaseActivity {

    /**
     * 把与业务相关的系统框架、界面初始化、设置等操作封装
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    /**
     * 动态添加主界面的girdview布局
     *
     * @param pResID
     */
    protected void AppendMainBody(int pResID) {
//        LinearLayout _mainBody = (LinearLayout) findViewById(R.id.llyt_main_body);
//        View _view = LayoutInflater.from(this).inflate(pResID, null);
//        _mainBody.addView(_view, RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT);
    }
}
