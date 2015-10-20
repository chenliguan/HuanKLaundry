package com.guan.o2o.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.guan.o2o.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ShareCodeActivity extends FrameActivity {

    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.rllyt_title)
    RelativeLayout rllytTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_code);
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
        tvTitle.setText(R.string.coupon_share_title);
    }

    /**
     * 监听实现
     */
    @OnClick(R.id.iv_back)
    public void onClick() {
        finish();
    }

}
