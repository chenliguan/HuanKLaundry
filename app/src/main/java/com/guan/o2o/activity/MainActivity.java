package com.guan.o2o.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;

import com.guan.o2o.R;
import com.guan.o2o.adapter.FragmentAdapter;
import com.guan.o2o.common.Contant;
import com.guan.o2o.fragment.BasketFragment;
import com.guan.o2o.fragment.HomeFragment;
import com.guan.o2o.fragment.MoreFragment;
import com.guan.o2o.fragment.MyHomeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 主页面，包含4个fragment
 *
 * @author Guan
 * @file com.guan.o2o.activity
 * @date 2015/9/29
 * @Version 1.0
 */
public class MainActivity extends FrameActivity {

    @InjectView(R.id.frag_vpager)
    ViewPager fragVPager;
    @InjectView(R.id.main_tab_home)
    RadioButton mainTabHome;
    @InjectView(R.id.main_tab_basket)
    RadioButton mainTabBasket;
    @InjectView(R.id.main_tab_my)
    RadioButton mainTabMy;
    @InjectView(R.id.main_tab_more)
    RadioButton mainTabMore;

    private long mExitTime;
    private int mColorMainBlue;
    private int mColorMainTextGrey;
    private ArrayList<Fragment> mFragList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        /**
         * 初始化变量
         */
        initVariable();
        /**
         * 绑定数据
         */
        bindData();
    }

    /**
     * 初始化变量
     */
    private void initVariable() {
        mExitTime = 0;
        mColorMainBlue = getResources().getColor(R.color.main_blue);
        mColorMainTextGrey = getResources().getColor(R.color.icon_text_grey);

        mFragList = new ArrayList<>();
        mFragList.add(new HomeFragment());
        mFragList.add(new BasketFragment());
        mFragList.add(new MyHomeFragment());
        mFragList.add(new MoreFragment());
    }

    /**
     * 绑定数据
     */
    private void bindData() {
        // 缓存页面的个数
        fragVPager.setOffscreenPageLimit(3);
        fragVPager.setAdapter(new FragmentAdapter(
                getSupportFragmentManager(), mFragList));
        fragVPager.addOnPageChangeListener(new onPageChangeListener());
    }

//    /**
//     * 3.实现MoreFragment的回调方法
//     *
//     * @param position
//     */
//    @Override
//    public void onMoreIntentSelected(int position) {
//
//        switch (position) {
//            case Contant.CV_CUSTOMER:
//                break;
//
//            case Contant.CV_PROBLEM:
//                // startActivityForResult()跳转，class、传值、请求码
//                requestActivity(ProblemActivity.class, null, Contant.CODE_PROBLEM);
//                break;
//
//            case Contant.CV_SERVICESCOPE:
//                requestActivity(ServiceScopeActivity.class, null, Contant.CODE_SERVICESCOPE);
//                break;
//
//            case Contant.CV_ABOUTUS:
//                break;
//
//            case Contant.CV_USERAGREE:
//                requestActivity(UserAgreeActivity.class, Contant.VALUE_MAIN_ACTIVITY, Contant.CODE_USERAGREE);
//                break;
//
//            case Contant.CV_FEEDBACK:
//                break;
//        }
//    }

//    /**
//     * 执行startActivityForResult()方法的回调
//     *
//     * @param requestCode
//     * @param resultCode
//     * @param intent
//     */
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        switch (requestCode) {
//            case Contant.CODE_USERAGREE:
//            case Contant.CODE_PROBLEM:
//            case Contant.CODE_SERVICESCOPE:
//                if (resultCode == RESULT_OK)
//                    fragVPager.setCurrentItem(intent.getIntExtra(Contant.INTENT_KEY, 0));
//                break;
//
//            default:
//                break;
//        }
//    }

    /**
     * 监听设置当前页面
     *
     * @param view
     */
    @OnClick({R.id.main_tab_home, R.id.main_tab_basket, R.id.main_tab_my, R.id.main_tab_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_tab_home:
                fragVPager.setCurrentItem(Contant.TAB_HOME);
                break;

            case R.id.main_tab_basket:
                fragVPager.setCurrentItem(Contant.TAB_BASKET);
                break;

            case R.id.main_tab_my:
                fragVPager.setCurrentItem(Contant.TAB_MY);
                break;

            case R.id.main_tab_more:
                fragVPager.setCurrentItem(Contant.TAB_MORE);
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

            switch (position) {
                case Contant.TAB_HOME:
                    mainTabHome.setChecked(true);
                    mainTabHome.setTextColor(mColorMainBlue);
                    mainTabBasket.setTextColor(mColorMainTextGrey);
                    mainTabMy.setTextColor(mColorMainTextGrey);
                    mainTabMore.setTextColor(mColorMainTextGrey);
                    break;

                case Contant.TAB_BASKET:
                    mainTabBasket.setChecked(true);
                    mainTabBasket.setTextColor(mColorMainBlue);
                    mainTabHome.setTextColor(mColorMainTextGrey);
                    mainTabMy.setTextColor(mColorMainTextGrey);
                    mainTabMore.setTextColor(mColorMainTextGrey);
                    break;

                case Contant.TAB_MY:
                    mainTabMy.setChecked(true);
                    mainTabMy.setTextColor(mColorMainBlue);
                    mainTabHome.setTextColor(mColorMainTextGrey);
                    mainTabBasket.setTextColor(mColorMainTextGrey);
                    mainTabMore.setTextColor(mColorMainTextGrey);
                    break;

                case Contant.TAB_MORE:
                    mainTabMore.setChecked(true);
                    mainTabMore.setTextColor(mColorMainBlue);
                    mainTabHome.setTextColor(mColorMainTextGrey);
                    mainTabBasket.setTextColor(mColorMainTextGrey);
                    mainTabMy.setTextColor(mColorMainTextGrey);
                    break;

                default:
                    break;
            }
        }

        @Override
        public void onPageScrolled(int position, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int position) {
        }
    }

    /**
     * 再按一次退出程序
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                mExitTime = System.currentTimeMillis();
                showMsg(getResources().getString(R.string.msg_repress));
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
