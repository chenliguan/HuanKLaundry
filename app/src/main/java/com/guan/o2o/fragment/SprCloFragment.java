package com.guan.o2o.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;

import com.guan.o2o.R;
import com.guan.o2o.activity.MainActivity;
import com.guan.o2o.adapter.AWashGridAdapter;
import com.guan.o2o.application.App;
import com.guan.o2o.common.Contant;
import com.guan.o2o.common.HttpPath;
import com.guan.o2o.model.AWashCloth;
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
public class SprCloFragment extends FrameFragment {

    @InjectView(R.id.gv_spr_clo)
    GridView gvSpringclo;
    @InjectView(R.id.pb_loading)
    ProgressBar pbLoading;
    @InjectView(R.id.rlyt_loading)
    LinearLayout rlytLoading;

    private AWashGridAdapter mWinAdapter;
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
        if (isVisibleToUser & mWinAdapter != null)
            mWinAdapter.notifyDataSetChanged();
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
        rlytLoading.setVisibility(View.VISIBLE);
        // 获取网络数据
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

                rlytLoading.setVisibility(View.INVISIBLE);
                if (response == null) {
                    showMsg(getString(R.string.msg_loading_error));
                } else {
                    // 解析数据
                    AWashCloth aWashCloth = AWashCloth.praseJson(response);
                    awashInfo = new ArrayList<AWashCloth.WashInfoEntity>();
                    awashInfo = aWashCloth.washInfo;

                    mWinAdapter = new AWashGridAdapter(getActivity(), awashInfo);
                    // 配置适配器
                    gvSpringclo.setAdapter(mWinAdapter);
                    gvSpringclo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            // popwindow
                            if (orderWindow != null && orderWindow.isShowing())
                                orderWindow.dismiss();
                            else{
                                AWashCloth.WashInfoEntity entity = awashInfo.get(i);
                                showOrderWindow(view, entity.getWashHead(), entity.getWashName(), entity.getAmount());
                            }
                        }
                    });
                }
            }

            @Override
            public void reqError(String error) {
                showMsg(getString(R.string.msg_con_server_error));
                rlytLoading.setVisibility(View.INVISIBLE);
            }
        };

        // 请求网络
        VolleyHttpRequest.String_request(Contant.TAG_STRING_REQUEST, HttpPath.getClothIfo(), volleyRequest);
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
        App.getQueue().cancelAll(Contant.TAG_STRING_REQUEST);
    }
}