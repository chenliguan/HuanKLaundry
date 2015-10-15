package com.guan.o2o.activity;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.guan.o2o.R;
import com.guan.o2o.adapter.FragmentAdapter;
import com.guan.o2o.common.Contant;
import com.guan.o2o.fragment.SpringCloFragment;
import com.guan.o2o.fragment.TestFragment;
import com.guan.o2o.utils.LogUtil;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 件洗页面，包含4个fragment
 *
 * @author Guan
 * @file com.guan.o2o.activity
 * @date 2015/10/14
 * @Version 1.0
 */
public class AWashActivity extends FrameActivity {

    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.tv_wash_spring)
    TextView tvWashSpring;
    @InjectView(R.id.tv_wash_summer)
    TextView tvWashSummer;
    @InjectView(R.id.tv_wash_winter)
    TextView tvWashWinter;
    @InjectView(R.id.tv_wash_leather)
    TextView tvWashLeather;
    @InjectView(R.id.iv_bottom_line)
    ImageView ivBottomLine;
    @InjectView(R.id.frag_vpager)
    ViewPager fragVPager;

    private int mOffset;
    private int mCurrIndex;
    private int mPositionOne;
    private int mPositionTwo;
    private int mPositionThree;
    private int mColorMainBlue;
    private int mBottomLineWidth;
    private int mColorMainTextGrey;
    private ArrayList<Fragment> mFragList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awash);
        ButterKnife.inject(this);

        /**
         * 初始化变量
         */
        initVariable();
        /**
         * 初始化顶栏宽度
         */
        InitWidth();
        /**
         * 绑定数据
         */
        bindData();
    }

    /**
     * 初始化变量
     */
    private void initVariable() {
        mOffset = 0;
        mCurrIndex = 0;
        tvTitle.setText(R.string.a_wash);
        mColorMainBlue = getResources().getColor(R.color.main_blue);
        mColorMainTextGrey = getResources().getColor(R.color.texts_grey);

        mFragList = new ArrayList<Fragment>();
        mFragList.add(new SpringCloFragment());
        mFragList.add(TestFragment.newInstance("Hello summer."));
        mFragList.add(TestFragment.newInstance("Hello winter."));
        mFragList.add(TestFragment.newInstance("Hello leather."));
    }

    /**
     * 初始化顶栏宽度
     */
    private void InitWidth() {
        mBottomLineWidth = ivBottomLine.getLayoutParams().width;
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;
        mOffset = (int) ((screenW / 4.0 - mBottomLineWidth) / 2);
        mPositionOne = (int) (screenW / 4.0);
        mPositionTwo = mPositionOne * 2;
        mPositionThree = mPositionOne * 3;
    }

    /**
     * 绑定数据
     */
    private void bindData() {
        fragVPager.setAdapter(new FragmentAdapter(
                getSupportFragmentManager(), mFragList));
        fragVPager.addOnPageChangeListener(new onPageChangeListener());
        fragVPager.setCurrentItem(0);
    }

    /**
     * 监听设置当前页面
     *
     * @param view
     */
    @OnClick({R.id.tv_wash_spring, R.id.tv_wash_summer, R.id.tv_wash_winter, R.id.tv_wash_leather, R.id.iv_back})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.tv_wash_spring:
                fragVPager.setCurrentItem(Contant.A_WASH_SPRING);
                break;

            case R.id.tv_wash_summer:
                fragVPager.setCurrentItem(Contant.A_WASH_SUMMER);
                break;

            case R.id.tv_wash_winter:
                fragVPager.setCurrentItem(Contant.A_WASH_WINTER);
                break;

            case R.id.tv_wash_leather:
                fragVPager.setCurrentItem(Contant.A_WASH_LEATER);
                break;

            case R.id.iv_back:
                finish();
                break;

            default:
                break;
        }
    }

    /**
     * 页面选择监听
     */

    private class onPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int position) {
            Animation animation = null;

            switch (position) {
                case Contant.A_WASH_SPRING:
                    if (mCurrIndex == 1)
                        animation = new TranslateAnimation(mPositionOne, 0, 0, 0);
                    else if (mCurrIndex == 2)
                        animation = new TranslateAnimation(mPositionTwo, 0, 0, 0);
                    else if (mCurrIndex == 3)
                        animation = new TranslateAnimation(mPositionThree, 0, 0, 0);
                    tvWashSpring.setTextColor(mColorMainBlue);
                    tvWashSummer.setTextColor(mColorMainTextGrey);
                    tvWashWinter.setTextColor(mColorMainTextGrey);
                    tvWashLeather.setTextColor(mColorMainTextGrey);
                    break;

                case Contant.A_WASH_SUMMER:
                    if (mCurrIndex == 0)
                        animation = new TranslateAnimation(mOffset, mPositionOne, 0, 0);
                    else if (mCurrIndex == 2)
                        animation = new TranslateAnimation(mPositionTwo, mPositionOne, 0, 0);
                    else if (mCurrIndex == 3)
                        animation = new TranslateAnimation(mPositionThree, mPositionOne, 0, 0);
                    tvWashSummer.setTextColor(mColorMainBlue);
                    tvWashSpring.setTextColor(mColorMainTextGrey);
                    tvWashWinter.setTextColor(mColorMainTextGrey);
                    tvWashLeather.setTextColor(mColorMainTextGrey);
                    break;

                case Contant.A_WASH_WINTER:
                    if (mCurrIndex == 0)
                        animation = new TranslateAnimation(mOffset, mPositionTwo, 0, 0);
                    else if (mCurrIndex == 1)
                        animation = new TranslateAnimation(mPositionOne, mPositionTwo, 0, 0);
                    else if (mCurrIndex == 3)
                        animation = new TranslateAnimation(mPositionThree, mPositionTwo, 0, 0);
                    tvWashWinter.setTextColor(mColorMainBlue);
                    tvWashSpring.setTextColor(mColorMainTextGrey);
                    tvWashSummer.setTextColor(mColorMainTextGrey);
                    tvWashLeather.setTextColor(mColorMainTextGrey);
                    break;

                case Contant.A_WASH_LEATER:
                    if (mCurrIndex == 0)
                        animation = new TranslateAnimation(mOffset, mPositionThree, 0, 0);
                    else if (mCurrIndex == 1)
                        animation = new TranslateAnimation(mPositionOne, mPositionThree, 0, 0);
                    else if (mCurrIndex == 2)
                        animation = new TranslateAnimation(mPositionTwo, mPositionThree, 0, 0);
                    tvWashLeather.setTextColor(mColorMainBlue);
                    tvWashSpring.setTextColor(mColorMainTextGrey);
                    tvWashSummer.setTextColor(mColorMainTextGrey);
                    tvWashWinter.setTextColor(mColorMainTextGrey);
                    break;

                default:
                    break;
            }
            // 记录当前position，作为下一项的前position
            mCurrIndex = position;
            animation.setFillAfter(true);
            animation.setDuration(300);
            ivBottomLine.startAnimation(animation);
        }

        @Override
        public void onPageScrolled(int position, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int position) {
        }
    }
}
