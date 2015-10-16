package com.guan.o2o.activity;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.guan.o2o.R;
import com.guan.o2o.application.App;
import com.guan.o2o.common.Contant;
import com.guan.o2o.common.HttpPath;
import com.guan.o2o.utils.RglExpressUtil;
import com.guan.o2o.utils.SharedPfeUtil;
import com.guan.o2o.volley.VolleyHandler;
import com.guan.o2o.volley.VolleyHttpRequest;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static com.guan.o2o.utils.RglExpressUtil.isChineseNo;
import static com.guan.o2o.utils.RglExpressUtil.isMobileNO;

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
    private PopupWindow mPopupWindow;
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
        if (isMobileNO(etPhone.getText().toString())) {
            // 开始计时
            mTime.start();
            // 请求网络
            VolleyHttpRequest.String_request(HttpPath.getCodeIfo(mLoginPhone), volleyRequest);
        } else
            showPopupWindow(mLocalView);
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
                    SharedPfeUtil.sharedPreferences(context, mLoginPhone, mLoginCode);
                    openActivityFn(MainActivity.class);
                } else
                    showMsg(Contant.MSG_SEIVICE_ERROR);
            }

            @Override
            public void reqError(String error) {
                showMsg("连接服务器出错");
            }
        };

        // 对手机号码与验证码验证
        if (isMobileNO(mLoginPhone) & !isChineseNo(mLoginCode) & !RglExpressUtil.isNullNo(mLoginCode)) {
            // 请求网络
            VolleyHttpRequest.String_request(HttpPath.getLoginIfo(mLoginPhone, mLoginCode), volleyRequest);
        } else
            showMsg(Contant.MSG_PHONE_PASS_ERROR);
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
     * 定义提示PopupWindow
     *
     * @param view
     */
    private void showPopupWindow(View view) {
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
        App.getQueue().cancelAll(Contant.TAG_STRING_REQUEST);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
