package com.guan.o2o.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.guan.o2o.R;
import com.guan.o2o.utils.CustomMsyhTV;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class ShareCodeActivity extends FrameActivity {

    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.iv_coupons)
    ImageView ivCoupons;
    @InjectView(R.id.tv_get_coupons)
    TextView tvGetCoupons;
    @InjectView(R.id.tv_recom_code)
    CustomMsyhTV tvRecomCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_code);
        ButterKnife.inject(this);
        ShareSDK.initSDK(this);
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
    @OnClick({R.id.iv_back,R.id.iv_share})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;

            case R.id.iv_share:
                share();
                break;

            default:
                break;
        }
    }

    public void share() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(getString(R.string.title_share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText(getString(R.string.content_share) + tvRecomCode.getText());

        // 确保SDcard下面存在此张图片
//        oks.setImagePath("/sdcard/test.jpg");
//        oks.setImagePath(Environment.getExternalStorageDirectory() + "/meinv.jpg");
        //网络图片的url：所有平台
//        oks.setImageUrl("http://www.heartguard.cn:8080/demo/means1.png");//网络图片rul

        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");
        // 启动分享GUI
        oks.show(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ShareSDK.stopSDK(this);
    }
}
