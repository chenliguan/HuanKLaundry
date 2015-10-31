package com.guan.o2o.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.guan.o2o.R;
import com.guan.o2o.application.App;
import com.guan.o2o.common.Constant;
import com.guan.o2o.common.HttpPath;
import com.guan.o2o.utils.RegularExpressUtil;
import com.guan.o2o.utils.SharedPfeUtil;
import com.guan.o2o.volley.VolleyHandler;
import com.guan.o2o.volley.VolleyHttpRequest;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

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
    private View mLocalView;
    private long mExitTime;
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
        mExitTime = 0;
        mLoginCode = null;
        mLoginPhone = null;
        context = LoginActivity.this;
        tvTitle.setText(R.string.title_login);
        mTime = new TimeCount(60000, 1000);
        mLocalView = getWindow().getDecorView();
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
                getCode();
                break;

            case R.id.btn_login:
                userLogin();
                break;

            case R.id.tv_book2:
                openActivity(UserAgreeActivity.class);
                break;

            default:
                break;
        }
    }

    /**
     * 获取验证码请求
     */
    private void getCode() {
        mLoginPhone = etPhone.getText().toString();
        volleyRequest = new VolleyHandler<String>() {
            @Override
            public void reqSuccess(String response) {
                // 暂时代替验证码效果
                String text = "您的登录验证码：" + response;
                showMsg(text);
            }

            @Override
            public void reqError(String error) {
                showMsg("获取验证码失败");
            }
        };

        // 对手机号码验证
        if (RegularExpressUtil.isNullNo(mLoginPhone)) {
            showTipsWindow(mLocalView, getString(R.string.pop_tip_title), getString(R.string.phone_null));
        } else if (!RegularExpressUtil.isMobileNO(mLoginPhone)) {
            showTipsWindow(mLocalView, getString(R.string.pop_tip_title), getString(R.string.pop_tip_content));
        } else {
            // 开始计时
            mTime.start();
            // 请求网络
            VolleyHttpRequest.String_request(HttpPath.getCodeIfo(mLoginPhone), volleyRequest);
        }

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
                    SharedPfeUtil.sharedLoginInfo(context, mLoginPhone, mLoginCode);
                    openActivityFn(MainActivity.class);
                } else
                    showMsg(Constant.MSG_SEIVICE_ERROR);
            }

            @Override
            public void reqError(String error) {
                showMsg(getString(R.string.msg_con_server_error));
            }
        };

//        // 对手机号码与验证码验证
//        if (RegularExpressUtil.isMobileNO(mLoginPhone) &
//                !RegularExpressUtil.isChineseNo(mLoginCode) &
//                !RegularExpressUtil.isNullNo(mLoginCode)) {
//            // 请求网络
//            VolleyHttpRequest.String_request(HttpPath.getLoginIfo(mLoginPhone, mLoginCode), volleyRequest);
//        } else
//            showTipsWindow(mLocalView, getString(R.string.pop_tip_title), getString(R.string.phone_pass_error));

        // 对手机号码与验证码验证
        if (RegularExpressUtil.isNullNo(mLoginPhone)) {
            showTipsWindow(mLocalView, getString(R.string.pop_tip_title), getString(R.string.phone_null));
        } else if (!RegularExpressUtil.isMobileNO(mLoginPhone)) {
            showTipsWindow(mLocalView, getString(R.string.pop_tip_title), getString(R.string.pop_tip_content));
        } else if (RegularExpressUtil.isNullNo(mLoginCode)) {
            showTipsWindow(mLocalView, getString(R.string.pop_tip_title), getString(R.string.pass_null));
        } else if (RegularExpressUtil.isChineseNo(mLoginCode)) {
            showTipsWindow(mLocalView, getString(R.string.pop_tip_title), getString(R.string.pass_error));
        } else
            // 请求网络
            VolleyHttpRequest.String_request(HttpPath.getLoginIfo(mLoginPhone, mLoginCode), volleyRequest);
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
            btnCode.setClickable(true);
            btnCode.setText(R.string.text_refresh);
            btnCode.setBackgroundColor(getResources().getColor(R.color.main_blue));
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
     * 再按一次退出程序
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                mExitTime = System.currentTimeMillis();
                showMsg(getResources().getString(R.string.msg_repress));
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onStop() {
        super.onStop();
        App.getQueue().cancelAll(Constant.TAG_STRING_REQUEST);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
