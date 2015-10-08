package com.guan.o2o.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.guan.o2o.R;
import com.guan.o2o.adapter.GridViewAdapter;
import com.guan.o2o.utils.CustomHanzTV;
import com.guan.o2o.utils.CustomMsyhTV;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 我的主页Fragment
 *
 * @author Guan
 * @file com.guan.o2o.fragment
 * @date 2015/10/6
 * @Version 1.0
 */
public class MyHomeFragment extends BaseFragment {


    @InjectView(R.id.tv_name)
    TextView tvName;
    @InjectView(R.id.tv_phone)
    CustomMsyhTV tvPhone;
    @InjectView(R.id.tv_recom_code)
    CustomHanzTV tvRecomCode;
    @InjectView(R.id.tv_last_coupon)
    TextView tvLastCoupon;
    @InjectView(R.id.gv_my)
    GridView gvMy;

    private List mStringList;
    private GridViewAdapter mGridAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 绑定数据
        bindData();
    }

    /**
     * 实现父类方法
     *
     * @param inflater
     * @return
     */
    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_myhome, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    /**
     * 初始化变量
     */
    public void initVariable() {
        mStringList = new ArrayList();
        mStringList.add(0, this.getString(R.string.grid_myorder));
        mStringList.add(1, this.getString(R.string.grid_washcoin));
        mStringList.add(2, this.getString(R.string.grid_myads));
        mStringList.add(3, this.getString(R.string.grid_share));
        mStringList.add(4, this.getString(R.string.grid_rcmcode));
        mGridAdapter = new GridViewAdapter(getActivity(), mStringList);
    }

    /**
     * 绑定数据
     */
    private void bindData() {
        // 配置适配器
        gvMy.setAdapter(mGridAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}