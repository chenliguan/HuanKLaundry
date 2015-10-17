package com.guan.o2o.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.guan.o2o.R;
import com.guan.o2o.adapter.AWLeaAdapter;
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
public class LeatherCloFragment extends BaseFragment {

    @InjectView(R.id.gv_spr_clo)
    GridView gvSpringclo;

    private List mStringList;
    private AWLeaAdapter mLeaAdapter;
    public WinterCloth winterCloth;
    public VolleyHandler<String> volleyRequest;

    /**
     * Fragment可见时,并在onCreateView之前调用
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser & mLeaAdapter != null) {
            mLeaAdapter.notifyDataSetChanged();
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
        View view = inflater.inflate(R.layout.fragment_spr_clo, container, false);
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
                LogUtil.showLog("response:" + response);
                winterCloth = WinterCloth.praseJson(response);
                mLeaAdapter = new AWLeaAdapter(getActivity(), winterCloth.WashInfo);
                // 配置适配器
                gvSpringclo.setAdapter(mLeaAdapter);

                gvSpringclo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        // popwindow
//                        if (mPopupWindow != null && mPopupWindow.isShowing())
//                            mPopupWindow.dismiss();
//                        else
//                            showOrderWindow(view);
                    }
                });
            }

            @Override
            public void reqError(String error) {
                showMsg(getString(R.string.msg_con_server_error));
            }
        };

        // 请求网络
        VolleyHttpRequest.String_request("Leater",HttpPath.getClothIfo(), volleyRequest);
    }

    /**
     * 绑定数据
     */
    @Override
    public void bindData() {
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtil.showLog("LeatherCloFragment:onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
        App.getQueue().cancelAll("Leater");
//        App.getQueue().cancelAll(Contant.TAG_STRING_REQUEST);
        LogUtil.showLog("LeatherCloFragment:onDestroyView()");
    }
}
