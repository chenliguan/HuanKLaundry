package com.guan.o2o.fragment;


import android.os.Bundle;
import android.os.Handler;
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
import com.guan.o2o.utils.LogUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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

    // 当前轮播页
    private int mCurrentItem;
    private int[] imageUrls;
    private ImageView[] mImageViews;
    // Handler来处理ViewPager的轮播,实现定时更新
    private ImageHandler imageHandler;
    // 定时周期执行指定的任务
    private ScheduledExecutorService mScheduledExecutorService;

    /**
     * 使用弱引用避免Handler泄露,泛型参数可以是Activity/Fragment
     */
    private class ImageHandler extends Handler {

        private WeakReference<HomeFragment> mWeakReference;

        public ImageHandler(HomeFragment fragment) {
            mWeakReference = new WeakReference<HomeFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Contant.MSG_UPDATE_IMAGE:
                    mWeakReference.get().viewpager.setCurrentItem(mCurrentItem);
                    break;

                default:
                    break;
            }
        }
    }

    ;

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
        imageUrls = new int[]{
//                "http://image.zcool.com.cn/56/35/1303967876491.jpg",
//                "http://image.zcool.com.cn/59/54/m_1303967870670.jpg",
//                "http://image.zcool.com.cn/47/19/1280115949992.jpg",
//                "http://image.zcool.com.cn/59/11/m_1303967844788.jpg"
                R.mipmap.ic_b,
                R.mipmap.ic_c,
                R.mipmap.ic_a,
                R.mipmap.ic_d
        };
        mCurrentItem = imageUrls.length * 1000;
        imageHandler = new ImageHandler(HomeFragment.this);
    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        LayoutInflater _inflater = LayoutInflater.from(getActivity());
        // 图片资源数组
        ArrayList<View> _listViews = new ArrayList<View>();
        // 圆点资源数组
        mImageViews = new ImageView[imageUrls.length];

        for (int i = 0; i < imageUrls.length; i++) {
            // 图片
            View _view = (View) _inflater.inflate(R.layout.view_pager, null);
            _view.setBackgroundResource(imageUrls[i]);
            _listViews.add(_view);

            // 圆点
            mImageViews[i] = new ImageView(getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(16, 16);
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
        // 设定大大的值实现向左回播
        viewpager.setCurrentItem(mCurrentItem);
    }

    /**
     * 开始轮播图切换
     */
    @Override
    public void onStart() {
        super.onStart();
        mScheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        mScheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 2, 3, TimeUnit.SECONDS);
    }

    /**
     * 轮询页面监听
     */
    private class onPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int position) {

            mCurrentItem = position;
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

        @Override
        public void onPageScrollStateChanged(int position) {

        }
    }

    /**
     * 执行轮播图切换任务
     */
    private class ScrollTask implements Runnable {

        @Override
        public void run() {
            synchronized (viewpager) {
                mCurrentItem++;
                // 通过handler切换图片
                imageHandler.sendEmptyMessage(Contant.MSG_UPDATE_IMAGE);
            }
        }
    }

    /**
     * 停止轮播图切换
     */
    @Override
    public void onStop() {
        super.onStop();
        mScheduledExecutorService.shutdown();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
