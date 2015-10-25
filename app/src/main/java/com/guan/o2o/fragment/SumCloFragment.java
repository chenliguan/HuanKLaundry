package com.guan.o2o.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.guan.o2o.R;
import com.guan.o2o.adapter.AWashGridAdapter;
import com.guan.o2o.application.App;
import com.guan.o2o.common.Constant;
import com.guan.o2o.common.HttpPath;
import com.guan.o2o.model.AWashCloth;
import com.guan.o2o.volley.VolleyHandler;
import com.guan.o2o.volley.VolleyHttpRequest;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 夏装Fragment
 *
 * @author Guan
 * @file com.guan.o2o.fragment
 * @date 2015/10/14
 * @Version 1.0
 */
public class SumCloFragment extends FrameFragment {

    @InjectView(R.id.gv_spr_clo)
    GridView gvSpringclo;

    private List mStringList;
    private AWashGridAdapter mWinAdapter;
    public AWashCloth aWashCloth;
    public VolleyHandler<String> volleyRequest;
    public List<AWashCloth.WashInfoEntity> awashInfo;

    /**
     * Fragment可见时,并在onCreateView之前调用
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser & mWinAdapter != null) {
            mWinAdapter.notifyDataSetChanged();
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
        View view = inflater.inflate(R.layout.fragment_awash_clo, container, false);
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
                AWashCloth aWashCloth = AWashCloth.praseJson(response);
                awashInfo = new ArrayList<AWashCloth.WashInfoEntity>();
                awashInfo = aWashCloth.washInfo;

                mWinAdapter = new AWashGridAdapter(getActivity(), awashInfo);
                // 配置适配器
                gvSpringclo.setAdapter(mWinAdapter);
                // 选项监听
                gvSpringclo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        // popwindow
                        if (orderWindow != null && orderWindow.isShowing())
                            orderWindow.dismiss();
                        else {
                            AWashCloth.WashInfoEntity entity = awashInfo.get(i);
                            showOrderWindowTo(view, entity.getWashHead(), entity.getWashName(), entity.getAmount());
                        }
                    }
                });
            }

            @Override
            public void reqError(String error) {
                showMsg(getString(R.string.msg_con_server_error));
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
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
        App.getQueue().cancelAll(Constant.TAG_STRING_REQUEST);
    }
}
