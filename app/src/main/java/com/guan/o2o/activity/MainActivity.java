package com.guan.o2o.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.guan.o2o.R;
import com.guan.o2o.adapter.FragmentAdapter;
import com.guan.o2o.application.App;
import com.guan.o2o.common.Constant;
import com.guan.o2o.fragment.BasketFragment;
import com.guan.o2o.fragment.HomeFragment;
import com.guan.o2o.fragment.MoreFragment;
import com.guan.o2o.fragment.MyHomeFragment;
import com.guan.o2o.utils.ConvertUtil;
import com.guan.o2o.utils.SharedPfeUtil;

import java.util.ArrayList;

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
public class MainActivity extends FrameActivity implements
        HomeFragment.OnClickListener, BasketFragment.OnClickListener,
        MoreFragment.OnClickListener {

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
    @InjectView(R.id.iv_have)
    ImageView ivHave;

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
         * 初始化订单列表
         */
        initOrderList();
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
     * 初始化订单列表
     */
    private void initOrderList() {
        SharedPreferences preferences_order = getSharedPreferences(
                Constant.SHARED_NAME_ORDER, MODE_PRIVATE);
        String liststring = preferences_order.getString(Constant.SHARED_KEY_ORDER, "");
        if (!liststring.equals(""))
            // 从本地获取出字符串转化为集合
            App.washOrderList = ConvertUtil.stringToList(liststring);
        if (App.washOrderList.size() != 0)
            ivHave.setVisibility(View.VISIBLE);
        else
            ivHave.setVisibility(View.INVISIBLE);
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

    @Override
    protected void onResume() {
        super.onResume();
        // 从AwashActivity-->MainActivity时，刷新页面
        if (App.washOrderList.size() != 0)
            ivHave.setVisibility(View.VISIBLE);
    }

    /**
     * 实现HomeFragment的回调方法
     *
     * @param position
     */
    @Override
    public void onIntentSelected(int position) {

        switch (position) {
            case Constant.CV_HOME_AWASH:
                // startActivityForResult()跳转，class、传值、请求码
                requestActivity(AWashActivity.class, null, Constant.CODE_HOME_AWASH);
                break;

            case Constant.CV_BASKET_MAIN:
                fragVPager.setCurrentItem(Constant.TAB_HOME);
                break;

            default:
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
            case Constant.CODE_HOME_AWASH:
                if (resultCode == RESULT_OK)
                    fragVPager.setCurrentItem(intent.getIntExtra(Constant.INTENT_KEY, 0));
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
                fragVPager.setCurrentItem(Constant.TAB_HOME);
                break;

            case R.id.main_tab_basket:
                fragVPager.setCurrentItem(Constant.TAB_BASKET);
                break;

            case R.id.main_tab_my:
                fragVPager.setCurrentItem(Constant.TAB_MY);
                break;

            case R.id.main_tab_more:
                fragVPager.setCurrentItem(Constant.TAB_MORE);
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
                case Constant.TAB_HOME:
                    mainTabHome.setChecked(true);
                    mainTabBasket.setChecked(false);
                    mainTabMy.setChecked(false);
                    mainTabMore.setChecked(false);
                    mainTabHome.setTextColor(mColorMainBlue);
                    mainTabBasket.setTextColor(mColorMainTextGrey);
                    mainTabMy.setTextColor(mColorMainTextGrey);
                    mainTabMore.setTextColor(mColorMainTextGrey);
                    break;

                case Constant.TAB_BASKET:
                    mainTabHome.setChecked(false);
                    mainTabBasket.setChecked(true);
                    mainTabMy.setChecked(false);
                    mainTabMore.setChecked(false);
                    mainTabHome.setTextColor(mColorMainTextGrey);
                    mainTabBasket.setTextColor(mColorMainBlue);
                    mainTabMy.setTextColor(mColorMainTextGrey);
                    mainTabMore.setTextColor(mColorMainTextGrey);
                    break;

                case Constant.TAB_MY:
                    mainTabHome.setChecked(false);
                    mainTabBasket.setChecked(false);
                    mainTabMy.setChecked(true);
                    mainTabMore.setChecked(false);
                    mainTabHome.setTextColor(mColorMainTextGrey);
                    mainTabBasket.setTextColor(mColorMainTextGrey);
                    mainTabMy.setTextColor(mColorMainBlue);
                    mainTabMore.setTextColor(mColorMainTextGrey);
                    break;

                case Constant.TAB_MORE:
                    mainTabHome.setChecked(false);
                    mainTabBasket.setChecked(false);
                    mainTabMy.setChecked(false);
                    mainTabMore.setChecked(true);
                    mainTabHome.setTextColor(mColorMainTextGrey);
                    mainTabBasket.setTextColor(mColorMainTextGrey);
                    mainTabMy.setTextColor(mColorMainTextGrey);
                    mainTabMore.setTextColor(mColorMainBlue);
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
                // 将集合转化为字符串保存在本地
                SharedPfeUtil.sharedOrderInfo(this);
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
