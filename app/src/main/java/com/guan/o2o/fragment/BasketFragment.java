package com.guan.o2o.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.guan.o2o.R;
import com.guan.o2o.adapter.WashOrderAdapter;
import com.guan.o2o.application.App;
import com.guan.o2o.utils.LogUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 洗衣篮Fragment
 *
 * @author Guan
 * @file com.guan.o2o.fragment
 * @date 2015/10/5
 * @Version 1.0
 */
public class BasketFragment extends BaseFragment {

    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.tv_delete_order)
    TextView tvDeleteOrder;
    @InjectView(R.id.lv_basket)
    ListView lvBasket;
    @InjectView(R.id.iv_basket_null)
    ImageView ivBasketNull;

    private boolean isRefresh;
    private WashOrderAdapter mWashOrderAdapter;

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
        isRefresh = false;
        tvTitle.setText(R.string.main_navigation_basket);
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
     * 更新数据
     */
    public void notifyData() {
        if (App.washOrderList != null & !isRefresh)
            bindAdapter();

        if (App.washOrderList != null & mWashOrderAdapter != null)
            mWashOrderAdapter.notifyDataSetChanged();
    }

    /*
     * 绑定适配器
     */
    public void bindAdapter() {
        mWashOrderAdapter = new WashOrderAdapter(getActivity(), App.washOrderList, ivBasketNull);
        lvBasket.setAdapter(mWashOrderAdapter);
        ivBasketNull.setVisibility(View.INVISIBLE);
        isRefresh = true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
