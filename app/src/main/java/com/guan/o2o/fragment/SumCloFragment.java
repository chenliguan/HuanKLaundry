package com.guan.o2o.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.guan.o2o.R;
import com.guan.o2o.adapter.AWashGridAdapter;
import com.guan.o2o.application.App;
import com.guan.o2o.common.Contant;
import com.guan.o2o.common.HttpPath;
import com.guan.o2o.model.WinterCloth;
import com.guan.o2o.utils.LogUtil;
import com.guan.o2o.volley.VolleyHandler;
import com.guan.o2o.volley.VolleyHttpRequest;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 春秋装Fragment
 *
 * @author Guan
 * @file com.guan.o2o.fragment
 * @date 2015/10/14
 * @Version 1.0
 */
public class SumCloFragment extends BaseFragment {

    @InjectView(R.id.gv_spring_clo)
    GridView gvSpringclo;

    private List mStringList;
    private AWashGridAdapter mAWGAdapter;
    public WinterCloth winterCloth;
    public VolleyHandler<String> volleyRequest;

    /**
     * Fragment可见时,并在onCreateView之前调用
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser & mAWGAdapter != null) {
            mAWGAdapter.notifyDataSetChanged();
        }
    }

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
        View view = inflater.inflate(R.layout.fragment_spring_clo, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    /**
     * 初始化变量
     */
    @Override
    public void initVariable() {
        mStringList = new ArrayList();
        getClothHttpData();
    }

    /**
     * 获取网络数据
     *
     * @return
     */
    public void getClothHttpData() {

        volleyRequest = new VolleyHandler<String>() {
            @Override
            public void reqSuccess(String response) {
                winterCloth = WinterCloth.praseJson(response);
                mAWGAdapter = new AWashGridAdapter(getActivity(), winterCloth.washInfo);
                // 配置适配器
                gvSpringclo.setAdapter(mAWGAdapter);
            }

            @Override
            public void reqError(String error) {
                showMsg("连接服务器出错");
            }
        };

        // 请求网络
        VolleyHttpRequest.String_request(HttpPath.getClothIfo(), volleyRequest);
    }

    /**
     * 绑定数据
     */
    @Override
    public void bindData() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
        App.getQueue().cancelAll(Contant.TAG_STRING_REQUEST);
    }
}