package com.guan.o2o.fragment;


import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.guan.o2o.R;
import com.guan.o2o.adapter.AWSprAdapter;
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
public class SpringCloFragment extends BaseFragment {

    @InjectView(R.id.gv_spr_clo)
    GridView gvSprclo;

    private AWSprAdapter mSprAdapter;
    private Dialog mLoadingDialog;
    public WinterCloth winterCloth;
    public List<WinterCloth.WashInfoEntity> washInfo;
    public VolleyHandler<String> volleyRequest;

    /**
     * Fragment可见时,并在onCreateView之前调用
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser & mSprAdapter != null) {
            mSprAdapter.notifyDataSetChanged();
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
        // 加载中对话框显示
//        mLoadingDialog = createLoadingDialog(getActivity(),"加载中…");
//        mLoadingDialog.show();
    }

    /**
     * 绑定数据
     */
    @Override
    public void bindData() {
        // 请求网络获取数据
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

                if (response == null) {
                    showMsg("加载失败");
                } else {
                    winterCloth = WinterCloth.praseJson(response);
                    washInfo = new ArrayList<WinterCloth.WashInfoEntity>();
                    washInfo = winterCloth.washInfo;
                    mSprAdapter = new AWSprAdapter(getActivity(), washInfo);
//                // 停留2秒,隐退
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        mLoadingDialog.dismiss();
//                    }
//                }, Contant.POPWIN_DELAY_MS);

                    // 配置适配器
                    gvSprclo.setAdapter(mSprAdapter);

                    gvSprclo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                            // popwindow
//                            if (mPopupWindow != null && mPopupWindow.isShowing())
//                                mPopupWindow.dismiss();
//                            else
//                                showOrderWindow(view);
                        }
                    });
                }
            }

            @Override
            public void reqError(String error) {
                showMsg("连接服务器出错");
//                if (mLoadingDialog == null && mLoadingDialog.isShowing())
//                    mLoadingDialog.dismiss();
            }
        };

        // 请求网络
        VolleyHttpRequest.String_request("Spring", HttpPath.getClothIfo(), volleyRequest);
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtil.showLog("SpringCloFragment:onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
//        App.getQueue().cancelAll(Contant.TAG_STRING_REQUEST);
        App.getQueue().cancelAll("Spring");
        LogUtil.showLog("SpringCloFragment:onDestroyView()");
    }
}
