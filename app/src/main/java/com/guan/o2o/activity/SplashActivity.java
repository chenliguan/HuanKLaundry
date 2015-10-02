package com.guan.o2o.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;

import com.guan.o2o.R;
import com.guan.o2o.application.App;
import com.guan.o2o.common.Contant;
import com.guan.o2o.common.HttpPath;
import com.guan.o2o.exception.ServiceRulesException;
import com.guan.o2o.utils.SharedPreferenceUtil;
import com.guan.o2o.volley.VolleyHandler;
import com.guan.o2o.volley.VolleyHttpRequest;

import java.lang.ref.WeakReference;

import static com.guan.o2o.utils.RegularExpressUtil.isChineseNo;
import static com.guan.o2o.utils.RegularExpressUtil.isMobileNO;

public class SplashActivity extends FrameActivity {

    /**
     * 记录是否第一次启动
     */
    private boolean mIsFirstIn;
    private String mLoginPhone;
    private String mLoginCode;
    public Context context;
    public VolleyHandler<String> volleyRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /**
         * 初始化数据
         */
        initVariable();

        /**
         * Handler的postDelayed方法实现"画面停留2-3秒",即：定时器
         */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                switchActivity();
            }
        }, Contant.SPLASH_DELAY_MS);
    }

    /**
     * 初始化数据
     */
    private void initVariable() {

        mLoginCode = null;
        mLoginPhone = null;
        mIsFirstIn = false;

        // 读取SHARED_NAME_FIRST中的数据
        SharedPreferences preferences_first = getSharedPreferences(
                Contant.SHARED_NAME_FIRST, MODE_PRIVATE);
        // 读取SHARED_NAME_LOGIN中的数据
        SharedPreferences preferences_login = getSharedPreferences(
                Contant.SHARED_NAME_LOGIN, MODE_PRIVATE);

        // 取得相应的值,如果没有用true作为默认值
        mIsFirstIn = preferences_first.getBoolean("isFirstIn", true);
        mLoginPhone = preferences_login.getString("loginPhone", "");
        mLoginCode = preferences_login.getString("loginCode", "");
    }

    /**
     * 判断选择跳转Activity
     */
    private void switchActivity() {
        // 判断程序与第几次运行
        if (!mIsFirstIn) {
            volleyRequest = new VolleyHandler<String>() {

                @Override
                public void reqSuccess(String response) {
                    // 登录业务判断
                    if (response.equals("547061")) {
                        openActivityFn(MainActivity.class);
                    } else
                        openActivityFn(LoginActivity.class);
                }

                @Override
                public void reqError(String error) {
                    openActivityFn(LoginActivity.class);
                }
            };

            // 请求网络
            VolleyHttpRequest.String_request(HttpPath.getLoginIfo(mLoginPhone, mLoginCode), volleyRequest);
        } else {
            openActivityFn(GuideActivity.class);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        App.getQueue().cancelAll(Contant.TAG_STRING_REQUEST);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
