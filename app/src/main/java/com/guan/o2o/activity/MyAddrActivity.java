package com.guan.o2o.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.guan.o2o.R;
import com.guan.o2o.common.Constant;
import com.guan.o2o.utils.RegularExpressUtil;
import com.guan.o2o.utils.SharedPfeUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 我的地址页面
 *
 * @author Guan
 * @file com.guan.o2o.activity
 * @date 2015/11/1
 * @Version 1.0
 */
public class MyAddrActivity extends FrameActivity {

    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.tv_delete_order)
    TextView tvModify;
    @InjectView(R.id.tv_name)
    TextView tvName;
    @InjectView(R.id.tv_phone)
    TextView tvPhone;
    @InjectView(R.id.tv_address)
    TextView tvAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_addr);
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
        tvTitle.setText(R.string.title_my_addr);
        tvModify.setText(R.string.text_modify);

        // 读取SHARED_NAME_USERINFO中的数据
        SharedPreferences preferences_user = getSharedPreferences(
                Constant.SHARED_NAME_USERINFO, Context.MODE_PRIVATE);
        String userName = preferences_user.getString(Constant.SHARED_KEY_NAME, "");
        String userPhone = preferences_user.getString(Constant.SHARED_KEY_USERPHONE, "");
        String userArea = preferences_user.getString(Constant.SHARED_KEY_AREA, "");
        String userCommu = preferences_user.getString(Constant.SHARED_KEY_COMMU, "");
        String userDetailAddr = preferences_user.getString(Constant.SHARED_KEY_DETAIL_ADDR, "");

        tvName.setText(userName);
        tvPhone.setText(userPhone);
        tvAddress.setText(userArea + userCommu + userDetailAddr);
    }

    /**
     * 监听实现
     *
     * @param view
     */
    @OnClick({R.id.iv_back, R.id.tv_delete_order})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.iv_back:
                finish();
                break;

            case R.id.tv_delete_order:
                openActivityFn(UserInfoActivity.class);
                break;

            default:
                break;
        }
    }

}
