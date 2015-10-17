package com.guan.o2o.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.guan.o2o.R;
import com.guan.o2o.activity.AboutUsActivity;
import com.guan.o2o.activity.ProblemActivity;
import com.guan.o2o.activity.ServiceAreaActivity;
import com.guan.o2o.activity.UserAgreeActivity;
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
public class MoreFragment extends BaseFragment {

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

//    private OnClickListener mCallback;
//
//    // 存放fragment的Activtiy必须实现的接口
//    public interface OnClickListener {
//        public void onMoreIntentSelected(int position);
//    }
//
//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        // 为保证Activity容器实现以回调的接口,如果没会抛出一个异常。
//        try {
//            mCallback = (OnClickListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnHeadlineSelectedListener");
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * 实现父类方法
     *
     * @param inflater
     * @return
     */
    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View _view = inflater.inflate(R.layout.fragment_more, container, false);
        ButterKnife.inject(this, _view);
        return _view;
    }

    /**
     * 初始化变量
     */
    public void initVariable() {
        tvTitle.setText(R.string.main_navigation_more);
    }

    /**
     * 绑定/设置数据操作
     */
    @Override
    public void bindData() {
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
                openActivity(ProblemActivity.class);
                break;

            case R.id.cv_service_scope:
                openActivity(ServiceAreaActivity.class);
                break;

            case R.id.cv_about_us:
                openActivity(AboutUsActivity.class);
                break;

            case R.id.user_agree:
                openActivity(UserAgreeActivity.class);
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










