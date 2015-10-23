package com.guan.o2o.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.guan.o2o.R;
import com.guan.o2o.common.Contant;

/**
 * 框架类封装业务相关的方法
 *
 * @author Guan
 * @file com.example.guan.activity
 * @date 2015/8/14
 * @Version 1.0
 */
public class FrameActivity extends BaseActivity {

    private PopupWindow mPopupWindow;

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
     * 定义提示PopupWindow
     *
     * @param view
     */
    public void showTipsWindow(View view) {
        View contentView = LayoutInflater.from(this).inflate(
                R.layout.view_pop_tip, null);
        mPopupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT, true);
        mPopupWindow.setOutsideTouchable(true);
        // 必须实现,否则点击外部区域和Back键都无法dismiss
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        backgroundAlpha(0.5f);
        // 设置好参数之后再show
        mPopupWindow.showAtLocation(view, Gravity.CENTER_VERTICAL, 0, 0);
        // 隐退监听
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
        // 停留2秒,隐退
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mPopupWindow.dismiss();
            }
        }, Contant.POPWIN_DELAY_MS);
    }
}
