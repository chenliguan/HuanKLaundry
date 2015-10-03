package com.guan.o2o.fragment;


import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.guan.o2o.R;
import com.guan.o2o.adapter.ImagePagerAdapter;
import com.guan.o2o.common.Contant;
import com.guan.o2o.handler.ImageHandler;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 主页Fragment
 *
 * @author Guan
 * @file com.guan.o2o.fragment
 * @date 2015/9/29
 * @Version 1.0
 */
public class HomeFragment extends Fragment {

    @InjectView(R.id.tv_city)
    TextView tvCity;
    @InjectView(R.id.iv_below)
    ImageView ivBelow;
    @InjectView(R.id.viewpager)
    public
    ViewPager viewpager;
    @InjectView(R.id.iv_a_wash)
    ImageView ivAWash;
    @InjectView(R.id.iv_bag_wash)
    ImageView ivBagWash;
    @InjectView(R.id.iv_home_ariticles)
    ImageView ivHomeAriticles;
    @InjectView(R.id.iv_other_wash)
    ImageView ivOtherWash;
    @InjectView(R.id.iv_service_note)
    ImageView ivServiceNote;
    @InjectView(R.id.llyt_dots)
    LinearLayout llytDots;

    private ImageView[] mImageViews;
    private String[] imageUrls;

    public ImageHandler handler;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.inject(this, _view);
        return _view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 初始化变量
        initVariable();
        // 初始化ViewPager
        initViewPager();
    }

    /**
     * 初始化变量
     */
    private void initVariable() {
        handler = new ImageHandler(HomeFragment.this);
    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        LayoutInflater _inflater = LayoutInflater.from(getActivity());
        // 图片资源数组
        ArrayList<View> _listViews = new ArrayList<View>();
        imageUrls = new String[]{
                "http://image.zcool.com.cn/56/35/1303967876491.jpg",
                "http://image.zcool.com.cn/59/54/m_1303967870670.jpg",
                "http://image.zcool.com.cn/47/19/1280115949992.jpg",
                "http://image.zcool.com.cn/59/11/m_1303967844788.jpg"
        };
        // 圆点资源数组
        mImageViews = new ImageView[imageUrls.length];

        // 热点个数与图片、圆点相等
        for (int i = 0; i < imageUrls.length; i++) {
            // 图片
            View _view = (View) _inflater.inflate(R.layout.view_pager, null);
            _view.setBackgroundResource(R.mipmap.ic_b);
            _listViews.add(_view);

            // 圆点
            mImageViews[i] = new ImageView(getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(16,16);
            params.setMargins(7, 10, 7, 10);
            mImageViews[i].setLayoutParams(params);
            if (0 == i) {
                mImageViews[i].setBackgroundResource(R.mipmap.ic_dot_c);
            } else {
                mImageViews[i].setBackgroundResource(R.mipmap.ic_dot);
            }
            llytDots.addView(mImageViews[i]);
        }

        viewpager.setAdapter(new ImagePagerAdapter(_listViews));
        viewpager.setOnPageChangeListener(new onPageChangeListener());
        // 默认在中间,使用户看不到边界
        viewpager.setCurrentItem(0);
        // 开始轮播效果
        handler.sendEmptyMessageDelayed(Contant.MSG_UPDATE_IMAGE, Contant.MSG_DELAY);
    }

    /**
     * 轮询页面监听
     */
    private class onPageChangeListener implements ViewPager.OnPageChangeListener {

        // 配合Adapter的currentItem字段进行设置。
        @Override
        public void onPageSelected(int position) {
            // 发送播放消息
            handler.sendMessage(Message.obtain(handler, Contant.MSG_PAGE_CHANGED, position, 0));

            // 更新小圆点图标
            for (int i = 0; i < imageUrls.length; i++) {
                if (position % imageUrls.length == i) {
                    mImageViews[i].setBackgroundResource(R.mipmap.ic_dot_c);
                } else {
                    mImageViews[i].setBackgroundResource(R.mipmap.ic_dot);
                }
            }
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        /**
         * 覆写该方法实现轮播效果的暂停和恢复
         */
        @Override
        public void onPageScrollStateChanged(int position) {

            switch (position) {
                case ViewPager.SCROLL_STATE_DRAGGING:
                    handler.sendEmptyMessage(Contant.MSG_KEEP_SILENT);
                    break;

                case ViewPager.SCROLL_STATE_IDLE:
                    handler.sendEmptyMessageDelayed(Contant.MSG_UPDATE_IMAGE, Contant.MSG_DELAY);
                    break;

                default:
                    break;
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
