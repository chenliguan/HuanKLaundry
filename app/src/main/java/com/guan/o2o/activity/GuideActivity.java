package com.guan.o2o.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.guan.o2o.R;
import com.guan.o2o.adapter.ViewPagerAdapter;
import com.guan.o2o.utils.SharedPreferenceUtil;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class GuideActivity extends FrameActivity implements ViewPager.OnPageChangeListener {

    /**
     * ViewPager
     */
    @InjectView(R.id.viewPager)
    ViewPager viewPager;

    /**
     * 立刻体验按钮
     */
    @InjectView(R.id.btn_im_exp)
    Button btnImExp;
    /**
     * 小点图片位置
     */
    @InjectView(R.id.llyt_dots)
    LinearLayout layoutDots;
    /**
     * 装ImageView数组List
     */
    private ArrayList<View> mList;

    /**
     * 图片资源数组
     */
    private ImageView[] mImageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.inject(this);

        // 初始化ViewPager
        initViewPager();
    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        LayoutInflater inflater = getLayoutInflater();
        mList = new ArrayList<View>();
        mList.add(inflater.inflate(R.layout.item_first, null));
        mList.add(inflater.inflate(R.layout.item_second, null));
        mList.add(inflater.inflate(R.layout.item_third, null));

        mImageViews = new ImageView[mList.size()];

        for (int i = 0; i < mList.size(); i++) {
            mImageViews[i] = new ImageView(GuideActivity.this);
            if (0 == i) {
                mImageViews[i].setBackgroundResource(R.mipmap.ic_indicator_c);
            } else {
                mImageViews[i].setBackgroundResource(R.mipmap.ic_indicator);
            }
            mImageViews[i].setPadding(0, 0, 20, 0);
            layoutDots.addView(mImageViews[i]);
        }

        viewPager.setAdapter(new ViewPagerAdapter(mList, GuideActivity.this));
        // 绑定回调
        viewPager.setOnPageChangeListener(this);
//        viewPager.setCurrentItem(0);
    }

    /**
     * 立刻体验按钮监听
     */
    @OnClick(R.id.btn_im_exp)
    public void onButtonClick() {
        // 持久化数据设置已经引导
        SharedPreferenceUtil.sharedPreferences(GuideActivity.this);
        // 跳转到LoginActivity
        openActivity(LoginActivity.class);
        finish();
    }
    
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        // 更新小圆点图标
        for (int i = 0; i < mList.size(); i++) {
            if (position == i) {
                mImageViews[i].setBackgroundResource(R.mipmap.ic_indicator_c);
            } else {
                mImageViews[i].setBackgroundResource(R.mipmap.ic_indicator);
            }
        }

//        // 滑动到最后pager时显示“立刻体验”按钮并监听
//        if (position == mList.size() - 1) {
//            onButtonClick();
//        } else {
//            btnImExp.setVisibility(View.INVISIBLE);
//        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}