package com.guan.o2o.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.guan.o2o.R;
import com.guan.o2o.adapter.FragmentAdapter;

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
public class MainActivity extends FragmentActivity {

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
    @InjectView(R.id.main_tab_group)
    RadioGroup mainTabGroup;

    public static final int TAB_HOME = 0;
    public static final int TAB_BASKET = 1;
    public static final int TAB_MY = 2;
    public static final int TAB_MORE = 3;

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
        FragmentAdapter adapter = new FragmentAdapter(
                getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    /**
     * 页面选择监听
     */
    private void onPageChangeListener() {
        viewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int id) {
                switch (id) {
                    case TAB_HOME:
                        mainTabHome.setChecked(true);
                        mainTabHome.setTextColor(getResources().getColor(R.color.main_blue));
                        mainTabBasket.setTextColor(getResources().getColor(R.color.icon_text_grey));
                        mainTabMy.setTextColor(getResources().getColor(R.color.icon_text_grey));
                        mainTabMore.setTextColor(getResources().getColor(R.color.icon_text_grey));
                        break;

                    case TAB_BASKET:
                        mainTabBasket.setChecked(true);
                        mainTabBasket.setTextColor(getResources().getColor(R.color.main_blue));
                        mainTabHome.setTextColor(getResources().getColor(R.color.icon_text_grey));
                        mainTabMy.setTextColor(getResources().getColor(R.color.icon_text_grey));
                        mainTabMore.setTextColor(getResources().getColor(R.color.icon_text_grey));
                        break;

                    case TAB_MY:
                        mainTabMy.setChecked(true);
                        mainTabMy.setTextColor(getResources().getColor(R.color.main_blue));
                        mainTabHome.setTextColor(getResources().getColor(R.color.icon_text_grey));
                        mainTabBasket.setTextColor(getResources().getColor(R.color.icon_text_grey));
                        mainTabMore.setTextColor(getResources().getColor(R.color.icon_text_grey));
                        break;

                    case TAB_MORE:
                        mainTabMore.setChecked(true);
                        mainTabMore.setTextColor(getResources().getColor(R.color.main_blue));
                        mainTabHome.setTextColor(getResources().getColor(R.color.icon_text_grey));
                        mainTabBasket.setTextColor(getResources().getColor(R.color.icon_text_grey));
                        mainTabMy.setTextColor(getResources().getColor(R.color.icon_text_grey));
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
     * 监听设置当前选择项
     *
     * @param view
     */
    @OnClick({R.id.main_tab_home, R.id.main_tab_basket, R.id.main_tab_my, R.id.main_tab_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_tab_home:
                viewPager.setCurrentItem(TAB_HOME);
                break;

            case R.id.main_tab_basket:
                viewPager.setCurrentItem(TAB_BASKET);
                break;

            case R.id.main_tab_my:
                viewPager.setCurrentItem(TAB_MY);
                break;

            case R.id.main_tab_more:
                viewPager.setCurrentItem(TAB_MORE);
                break;

            default:
                break;
        }
    }
}
