package com.guan.o2o.activity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.guan.o2o.R;
import com.guan.o2o.common.HttpPath;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 关于浣洗页面
 *
 * @author Guan
 * @file com.guan.o2o.activity
 * @date 2015/10/26
 * @Version 1.0
 */
public class AboutAppActivity extends FrameActivity {

    @InjectView(R.id.wv_about_app)
    WebView wvAboutApp;
    @InjectView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);
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
        tvTitle.setText(R.string.title_about_app);
    }

    /**
     * 初始化布局
     */
    private void initView() {
        wvAboutApp.loadUrl(HttpPath.getAboutAppfo());
        // 覆盖WebView默认其他浏览器打开网页的行为,使网页用WebView打开
        wvAboutApp.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String url) {
                // 返回值是true时用WebView打开，为false调用其他浏览器
                webView.loadUrl(url);
                // 设置Webview自适应屏幕
                WebSettings settings = webView.getSettings();
                settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
                settings.setLoadWithOverviewMode(true);
                settings.setUseWideViewPort(true);
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
