package com.guan.o2o.fragment;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.guan.o2o.R;
import com.guan.o2o.adapter.AWashGridAdapter;
import com.guan.o2o.adapter.MyGridAdapter;
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

import static com.guan.o2o.utils.RglExpressUtil.isMobileNO;

/**
 * 春秋装Fragment
 *
 * @author Guan
 * @file com.guan.o2o.fragment
 * @date 2015/10/14
 * @Version 1.0
 */
public class SpringCloFragment extends BaseFragment {

    @InjectView(R.id.gv_spring_clo)
    GridView gvSpringclo;

    private AWashGridAdapter mAWGAdapter;
    private Dialog mLoadingDialog;
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
        // 加载中对话框显示
        mLoadingDialog = createLoadingDialog(getActivity(),"加载中…");
        mLoadingDialog.show();
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
                winterCloth = WinterCloth.praseJson(response);
                mAWGAdapter = new AWashGridAdapter(getActivity(), winterCloth.washInfo);
                // 配置适配器
                gvSpringclo.setAdapter(mAWGAdapter);
                // 停留1.5秒,隐退
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Loading对话框关闭
                        mLoadingDialog.dismiss();
                    }
                }, Contant.LOADING_DELAY_MS);
            }

            @Override
            public void reqError(String error) {
                showMsg("连接服务器出错");
                if(mLoadingDialog == null && mLoadingDialog.isShowing()) {
                    mLoadingDialog.dismiss();
                }
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

    /**
     * 得到自定义的progressDialog
     * @param context
     * @param msg
     * @return
     */
    public Dialog createLoadingDialog(Context context, String msg) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_loading_dialog, null);
        LinearLayout layout = ButterKnife.findById(view, R.id.dialog_view);
        final ImageView imageView = ButterKnife.findById(view,R.id.iv_loading);
        TextView tvTips = ButterKnife.findById(view,R.id.tv_tips);
        // 加载动画
        Animation animation = AnimationUtils.loadAnimation(
                context, R.anim.anim_loading);
        // 使用ImageView显示动画
        imageView.startAnimation(animation);
        // 加载信息
        tvTips.setText(msg);
        // 创建自定义样式dialog
        Dialog loadingDialog = new Dialog(context, R.style.Loading_dialog);
//        // 不可用“返回键”取消
//        loadingDialog.setCancelable(false);
        // 设置布局
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT));
        loadingDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                imageView.clearAnimation();
            }
        });
        return loadingDialog;
    }

    @Override
    public void onStop() {
        super.onStop();
        App.getQueue().cancelAll(Contant.TAG_STRING_REQUEST);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
