package com.guan.o2o.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.guan.o2o.R;
import com.guan.o2o.common.Contant;
import com.guan.o2o.common.HttpPath;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 常见问题页面
 *
 * @author Guan
 * @file com.guan.o2o.activity
 * @date 2015/10/1
 * @Version 1.0
 */
public class ProblemActivity extends FrameActivity {

    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.wv_com_problem)
    WebView wvComProblem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com_problem);
        ButterKnife.inject(this);

        /**
         * 初始化布局
         */
        initView();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        tvTitle.setText(R.string.title_com_problem);
        wvComProblem.loadUrl(HttpPath.getUserAgreeIfo());
        wvComProblem.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
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
        setResultTo(Contant.TAB_MORE);
    }
}
