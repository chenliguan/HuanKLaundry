package com.guan.o2o.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.guan.o2o.R;
import com.guan.o2o.utils.ListUtil;
import com.guan.o2o.utils.LogUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 意见反馈页面
 *
 * @author Guan
 * @file com.guan.o2o.activity
 * @date 2015/10/28
 * @Version 1.0
 */
public class FeekBackActivity extends FrameActivity {

    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.tv_input)
    TextView tvInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feek_back);
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
        tvTitle.setText(R.string.title_feek_back);
    }

    /**
     * 监听实现
     */
    @OnClick({R.id.iv_back,R.id.btn_submit})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;

            case R.id.btn_submit:
                /**
                 * 提交到服务器
                 */
                LogUtil.showLog("意见反馈：" + tvInput.getText());
                finish();
                break;
        }
    }
}
