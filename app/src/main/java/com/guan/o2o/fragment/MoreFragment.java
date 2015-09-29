package com.guan.o2o.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.guan.o2o.R;
import com.guan.o2o.view.CustomView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 更多Fragment
 *
 * @author Guan
 * @file com.guan.o2o.fragment
 * @date 2015/9/29
 * @Version 1.0
 */
public class MoreFragment extends Fragment {

    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.cv_customer)
    CustomView cvCustomer;
    @InjectView(R.id.cv_problem)
    CustomView cvProblem;
    @InjectView(R.id.cv_service_scope)
    CustomView cvServiceScope;
    @InjectView(R.id.cv_about_us)
    CustomView cvAboutUs;
    @InjectView(R.id.user_agree)
    CustomView userAgree;
    @InjectView(R.id.cv_feed_back)
    CustomView cvFeedBack;

    public MoreFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        ButterKnife.inject(this, view);

        /**
         * 初始化变量
         */
        initVariable();

        return view;
    }


    /**
     * 初始化变量
     */
    private void initVariable() {
        tvTitle.setText(R.string.main_navigation_more);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}










