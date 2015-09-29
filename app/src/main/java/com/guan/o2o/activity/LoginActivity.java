package com.guan.o2o.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.guan.o2o.R;
import com.guan.o2o.application.App;
import com.guan.o2o.common.Contant;
import com.guan.o2o.common.HttpPath;
import com.guan.o2o.utils.SharedPreferenceUtil;
import com.guan.o2o.volley.VolleyHandler;
import com.guan.o2o.volley.VolleyHttpRequest;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static com.guan.o2o.utils.RegularExpressUtil.isChineseNo;
import static com.guan.o2o.utils.RegularExpressUtil.isMobileNO;

/**
 * 登录页面
 *
 * @author Guan
 * @file com.guan.o2o.activity
 * @date 2015/9/23
 * @Version 1.0
 */
public class LoginActivity extends FrameActivity {

    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.et_phone)
    EditText etPhone;
    @InjectView(R.id.btn_code)
    Button btnCode;
    @InjectView(R.id.et_code)
    EditText etCode;
    @InjectView(R.id.btn_login)
    Button btnLogin;
    @InjectView(R.id.tv_book2)
    TextView tvBook2;
    @InjectView(R.id.tv_title)
    TextView tvTitle;

    private TimeCount mTime;
    private String mLoginPhone;
    private String mLoginCode;
    public Context context;
    public VolleyHandler<String> volleyRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        /**
         * 初始化变量
         */
        initVariable();
    }

    /**
     * 初始化变量
     */
    private void initVariable() {
        mLoginCode = null;
        mLoginPhone = null;
        mTime = new TimeCount(60000, 1000);
        tvTitle.setText(R.string.title_login);
        context = LoginActivity.this;
    }

    /**
     * 监听实现
     */
    @OnClick({R.id.iv_back, R.id.btn_code, R.id.btn_login, R.id.tv_book2})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.iv_back:
                showMsg("返回");
                break;

            case R.id.btn_code:
                // 开始计时
                mTime.start();
                getCode();
                break;

            case R.id.btn_login:
                userLogin();
                break;

            case R.id.tv_book2:
                openActivity(UserRegulateActivity.class);
                break;

            default:
                break;
        }
    }

    /**
     * 获取验证码倒计时
     */
    private class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        // 计时完毕
        @Override
        public void onFinish() {
            btnCode.setText(R.string.text_refresh);
            btnCode.setClickable(true);
        }

        // 计时过程
        @Override
        public void onTick(long millisUntilFinished) {
            btnCode.setClickable(false);
            btnCode.setBackgroundColor(getResources().getColor(R.color.icon_text_grey));
            String text = "(" + millisUntilFinished / 1000 + "s" + ")后重新获取";
            btnCode.setText(text);
        }
    }

    /**
     * 获取验证码请求
     */
    private void getCode() {
        volleyRequest = new VolleyHandler<String>() {
            @Override
            public void reqSuccess(String response) {
                // 暂时代替验证码效果
                String text = "[浣客洗衣] 您的登录验证码：" + response;
                showMsg(text);
            }

            @Override
            public void reqError(String error) {
                showMsg(error);
            }
        };

        // 对手机号码验证
        if (isMobileNO(mLoginPhone)) {
            // 请求网络
            VolleyHttpRequest.String_request(HttpPath.getCodeIfo(mLoginPhone), volleyRequest);
        } else
            showMsg(Contant.MSG_PHONE_ERROR);
    }

    /**
     * 用户登录请求
     */
    private void userLogin() {
        mLoginPhone = etPhone.getText().toString();
        mLoginCode = etCode.getText().toString();

        volleyRequest = new VolleyHandler<String>() {
            @Override
            public void reqSuccess(String response) {
                // 登录业务判断
                if (response.equals("547061")) {
                    SharedPreferenceUtil.sharedPreferences(context, mLoginPhone, mLoginCode);
                    openActivity(MainActivity.class);
                    finish();
                } else
                    showMsg(Contant.MSG_SEIVICE_ERROR);
            }

            @Override
            public void reqError(String error) {
                showMsg(error);
            }
        };

        // 对手机号码与验证码验证
        if (isMobileNO(mLoginPhone)) {
            if (!isChineseNo(mLoginCode))
                // 请求网络
                VolleyHttpRequest.String_request(HttpPath.getLoginIfo(mLoginPhone, mLoginCode), volleyRequest);
            else
                showMsg(Contant.MSG_PASSWORD_ERROR);
        } else
            showMsg(Contant.MSG_PHONE_ERROR);
    }

    @Override
    protected void onStop() {
        super.onStop();
        App.getQueue().cancelAll("stringrequest");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
