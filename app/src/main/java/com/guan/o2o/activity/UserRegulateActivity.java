package com.guan.o2o.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.guan.o2o.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 用户协议页面
 *
 * @author Guan
 * @file com.guan.o2o.activity
 * @date 2015/9/29
 * @Version 1.0
 */
public class UserRegulateActivity extends FrameActivity {

    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.wv_regulate)
    WebView wvRegulate;
    @InjectView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_regulate);
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

        tvTitle.setText(R.string.title_user_regulate);

        wvRegulate.loadUrl("http://123.56.138.192:8002/protocol/");
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        wvRegulate.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }

    /**
     * 监听实现
     */
    @OnClick(R.id.iv_back)
    public void onClick() {
        openActivity(LoginActivity.class);
        finish();
    }

}
