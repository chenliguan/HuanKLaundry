package com.guan.o2o.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.guan.o2o.R;
import com.guan.o2o.common.Contant;
import com.guan.o2o.view.CustomView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

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

    private OnClickListener mCallback;

    // 存放fragment的Activtiy必须实现的接口
    public interface OnClickListener {
        public void onIntentSelected(int position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 初始化变量
        initVariable();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // 为保证Activity容器实现以回调的接口,如果没会抛出一个异常。
        try {
            mCallback = (OnClickListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    /**
     * 初始化变量
     */
    private void initVariable() {
        tvTitle.setText(R.string.main_navigation_more);
    }

    /**
     * 监听实现，回调接口
     */
    @OnClick({R.id.cv_customer, R.id.cv_problem, R.id.cv_service_scope, R.id.cv_about_us, R.id.user_agree, R.id.cv_feed_back})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.cv_customer:
                break;

            case R.id.cv_problem:
                mCallback.onIntentSelected(Contant.CV_PROBLEM);
                break;

            case R.id.cv_service_scope:
                break;

            case R.id.cv_about_us:
                break;

            case R.id.user_agree:
                mCallback.onIntentSelected(Contant.CV_USERAGREE);
                break;

            case R.id.cv_feed_back:
                break;

            default:
                break;
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}










