package com.guan.o2o.fragment;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.guan.o2o.R;
import com.guan.o2o.activity.ServiceNoteActivity;
import com.guan.o2o.adapter.WashOrderAdapter;
import com.guan.o2o.application.App;
import com.guan.o2o.common.Constant;
import com.guan.o2o.model.WashOrder;
import com.guan.o2o.utils.ListUtil;
import com.guan.o2o.utils.LogUtil;

import java.util.ArrayList;
import java.util.Iterator;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 洗衣篮Fragment
 *
 * @author Guan
 * @file com.guan.o2o.fragment
 * @date 2015/10/5
 * @Version 1.0
 */
public class BasketFragment extends BaseFragment {

    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.lv_basket)
    ListView lvBasket;
    @InjectView(R.id.iv_basket_null)
    ImageView ivBasketNull;

    private ImageView mIvHave;
    private boolean mIsRefresh;
    private WashOrderAdapter mWashOrderAdapter;
    private OnClickListener mCallback;
    private UpdateUIReceiver mUpdateUIReceiver;

    // 存放fragment的Activtiy必须实现的接口
    public interface OnClickListener {
        public void onIntentSelected(int position);
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
     * Fragment可见时,并在onCreateView之前调用
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // 更新数据
            notifyData();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 动态注册广播
        mUpdateUIReceiver = new UpdateUIReceiver();
        IntentFilter filter = new IntentFilter(Constant.ACTION_BU_UPDATEUI);
        getActivity().registerReceiver(mUpdateUIReceiver, filter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 加载控件的数据
        loadData();
    }

    /**
     * 实现父类方法
     *
     * @param inflater
     * @return
     */
    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_basket, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    /**
     * 初始化变量
     */
    public void initVariable() {
        mIsRefresh = false;
        tvTitle.setText(R.string.main_navigation_basket);
        // 取得MainActivity的控件
        mIvHave = (ImageView) getActivity().findViewById(R.id.iv_have);
    }

    /**
     * 绑定/设置数据操作
     */
    @Override
    public void bindData() {
    }

    /*
     * 加载控件的数据
     */
    public void loadData() {
        if (App.washOrderList.size() == 0 && lvBasket != null)
            ivBasketNull.setVisibility(View.VISIBLE);
        else if (App.washOrderList.size() != 0)
            bindAdapter();
    }

    /**
     * 监听实现
     */
    @OnClick({R.id.iv_back, R.id.tv_delete_order})
    public void OnClick(View view) {

        switch (view.getId()) {
            case R.id.iv_back:
                mCallback.onIntentSelected(Constant.CV_BASKET_MAIN);
                break;

            case R.id.tv_delete_order:
                ListUtil.removeAll(App.washOrderList);
                notifyData();
                break;

            default:
                break;
        }
    }

    /**
     * 定义广播接收器（内部类）
     */
    private class UpdateUIReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // 更新数据
            notifyData();
        }
    }

    /**
     * 更新数据
     */
    public void notifyData() {
        if (App.washOrderList != null & !mIsRefresh)
            bindAdapter();
        if (App.washOrderList != null & mWashOrderAdapter != null)
            mWashOrderAdapter.notifyDataSetChanged();
    }

    /*
     * 绑定适配器
     */
    public void bindAdapter() {
        mWashOrderAdapter = new WashOrderAdapter(getActivity(), App.washOrderList, ivBasketNull, mIvHave);
        lvBasket.setAdapter(mWashOrderAdapter);
        ivBasketNull.setVisibility(View.INVISIBLE);
        mIsRefresh = true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 注销广播
        getActivity().unregisterReceiver(mUpdateUIReceiver);
    }
}
