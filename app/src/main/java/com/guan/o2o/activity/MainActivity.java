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
 * 主页面
 *
 * @author Guan
 * @file com.guan.o2o.activity
 * @date 2015/9/29
 * @Version 1.0
 */
public class MainActivity extends BaseFragActivity {

    @InjectView(R.id.viewpager)
    ViewPager viewPager;
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
         * 页面选择监听
         */
        onPageChangeListener();
    }

    /**
     * 初始化变量
     */
    private void initVariable() {
        mExitTime = 0;
        mColorMainBlue = getResources().getColor(R.color.main_blue);
        mColorMainTextGrey = getResources().getColor(R.color.icon_text_grey);

        List<Fragment> fragList = new ArrayList<>();
        HomeFragment homeFragment = new HomeFragment();
        BasketFragment basketFragment = new BasketFragment();
        MyHomeFragment myHomeFragment = new MyHomeFragment();
        MoreFragment moreFragment = new MoreFragment();
        fragList.add(homeFragment);
        fragList.add(basketFragment);
        fragList.add(myHomeFragment);
        fragList.add(moreFragment);

        FragmentAdapter fagAdapter = new FragmentAdapter(
                getSupportFragmentManager(), fragList);
        // 缓存页面的个数
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(fagAdapter);
    }

    /**
     * 0.实现HomeFragment的回调方法
     *
     * @param position
     */
    @Override
    public void onHomeIntentSelected(int position) {

    }

    /**
     * 3.实现MoreFragment的回调方法
     *
     * @param position
     */
    @Override
    public void onMoreIntentSelected(int position) {

        switch (position) {
            case Contant.CV_CUSTOMER:
                break;

            case Contant.CV_PROBLEM:
                // startActivityForResult()跳转，class、传值、请求码
                requestActivity(ProblemActivity.class, null, Contant.CODE_PROBLEM);
                break;

            case Contant.CV_SERVICESCOPE:
                requestActivity(ServiceScopeActivity.class, null, Contant.CODE_SERVICESCOPE);
                break;

            case Contant.CV_ABOUTUS:
                break;

            case Contant.CV_USERAGREE:
                requestActivity(UserAgreeActivity.class, Contant.VALUE_MAIN_ACTIVITY, Contant.CODE_USERAGREE);
                break;

            case Contant.CV_FEEDBACK:
                break;
        }
    }

    /**
     * 执行startActivityForResult()方法的回调
     *
     * @param requestCode
     * @param resultCode
     * @param intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case Contant.CODE_USERAGREE:
            case Contant.CODE_PROBLEM:
            case Contant.CODE_SERVICESCOPE:
                if (resultCode == RESULT_OK)
                    viewPager.setCurrentItem(intent.getIntExtra(Contant.INTENT_KEY, 0));
                break;

            default:
                break;
        }
    }

    /**
     * 监听设置当前页面
     *
     * @param view
     */
    @OnClick({R.id.main_tab_home, R.id.main_tab_basket, R.id.main_tab_my, R.id.main_tab_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_tab_home:
                viewPager.setCurrentItem(Contant.TAB_HOME);
                break;

            case R.id.main_tab_basket:
                viewPager.setCurrentItem(Contant.TAB_BASKET);
                break;

            case R.id.main_tab_my:
                viewPager.setCurrentItem(Contant.TAB_MY);
                break;

            case R.id.main_tab_more:
                viewPager.setCurrentItem(Contant.TAB_MORE);
                break;

            default:
                break;
        }
    }

    /**
     * 页面选择监听
     */
    private void onPageChangeListener() {
        viewPager.addOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int id) {
                switch (id) {
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
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
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
