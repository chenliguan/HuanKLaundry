package com.guan.o2o.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.guan.o2o.R;

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
    @InjectView(R.id.av_viewpager)
    ViewPager avViewpager;
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

    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.inject(this, _view);
        return _view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
