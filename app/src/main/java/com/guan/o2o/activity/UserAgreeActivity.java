package com.guan.o2o.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.guan.o2o.R;
import com.guan.o2o.common.HttpPath;

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
public class UserAgreeActivity extends FrameActivity {

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
        /**
         * 初始化布局
         */
        initView();
    }

    /**
     * 初始化变量
     */
    private void initVariable() {
        tvTitle.setText(R.string.title_user_regulate);
    }

    /**
     * 初始化布局
     */
    private void initView() {
        wvRegulate.loadUrl(HttpPath.getUserAgreeIfo());
        // 覆盖WebView默认其他浏览器打开网页的行为,使网页用WebView打开
        wvRegulate.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String url) {
                //返回值是true时用WebView打开，为false调用其他浏览器
                webView.loadUrl(url);
                return true;
            }
        });
    }

    /**
     * 监听实现
     */
    @OnClick(R.id.iv_back)
    public void onClick() {
       finish();
    }
}
