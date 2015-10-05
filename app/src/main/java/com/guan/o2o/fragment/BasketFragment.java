package com.guan.o2o.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.guan.o2o.R;
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
public class BasketFragment extends Fragment {

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

    /**
     * Fragment可见时,在onCreateView之前调用
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            lazyLoad();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basket, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 初始化变量
        initVariable();
    }

    /**
     * 初始化变量
     */
    private void initVariable() {
        tvTitle.setText(R.string.main_navigation_basket);
    }

    /*
     * 可见时,懒加载控件的数据
     */
    protected void lazyLoad() {

        if(App.washOrders.size() == 0 && lvBasket != null) {
            ivBasketNull.setVisibility(View.VISIBLE);
        } else {

            ivBasketNull.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
