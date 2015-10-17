package com.guan.o2o.fragment;


import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import com.guan.o2o.R;
import com.guan.o2o.adapter.AWSumAdapter;
import com.guan.o2o.application.App;
import com.guan.o2o.common.Contant;
import com.guan.o2o.common.HttpPath;
import com.guan.o2o.model.WashOrder;
import com.guan.o2o.model.WinterCloth;
import com.guan.o2o.utils.CustomMsyhTV;
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
public class SumCloFragment extends FrameFragment {

    @InjectView(R.id.gv_sum_clo)
    GridView gvSumclo;

    private int mNum;
    private TextView mTvNum;
    private List<WinterCloth.WashInfoEntity> mStringList;
    private AWSumAdapter mSumAdapter;
    public WinterCloth.WashInfoEntity winterClothEntity;
    public VolleyHandler<String> volleyRequest;

    /**
     * Fragment可见时,并在onCreateView之前调用
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser & mSumAdapter != null) {
            mSumAdapter.notifyDataSetChanged();
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
        View view = inflater.inflate(R.layout.fragment_sum_clo, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    /**
     * 初始化变量
     */
    @Override
    public void initVariable() {
        mStringList = new ArrayList();
    }

    /**
     * 绑定数据
     */
    @Override
    public void bindData() {
//        getClothHttpData();

        WinterCloth.WashInfoEntity entity1 = new WinterCloth.WashInfoEntity("帽子","￥5","http://www.heartguard.cn:8080/demo/image7.png");
        WinterCloth.WashInfoEntity entity2 = new WinterCloth.WashInfoEntity("羽绒","￥12","http://www.heartguard.cn:8080/demo/image9.png");
        mStringList.add(entity1);
        mStringList.add(entity2);
        mSumAdapter = new AWSumAdapter(getActivity(), mStringList);
        // 配置适配器
        gvSumclo.setAdapter(mSumAdapter);

        gvSumclo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // popwindow
                if (mPopupWindow != null && mPopupWindow.isShowing())
                    mPopupWindow.dismiss();
                else
                    showOrderWindow(view);
            }
        });
    }

//    /**
//     * 获取网络数据
//     *
//     * @return
//     */
//    public void getClothHttpData() {
//
//        volleyRequest = new VolleyHandler<String>() {
//            @Override
//            public void reqSuccess(String response) {
//                winterCloth = WinterCloth.praseJson(response);
//
////                WinterCloth.WashInfoEntity entity = new WinterCloth.WashInfoEntity("帽子","￥5","http://www.heartguard.cn:8080/demo/image7.png");
////                winterCloth.washInfo.add(entity);
//                mSumAdapter = new AWSumAdapter(getActivity(), winterCloth.washInfo);
//                // 配置适配器
//                gvSumclo.setAdapter(mSumAdapter);
//
//                gvSumclo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        // popwindow
//                        if (mPopupWindow != null && mPopupWindow.isShowing())
//                            mPopupWindow.dismiss();
//                        else
//                            showOrderWindow(view);
//                    }
//                });
//            }
//
//            @Override
//            public void reqError(String error) {
//                showMsg(getString(R.string.msg_con_server_error));
//            }
//        };
//
//        // 请求网络
//        VolleyHttpRequest.String_request("Sum",HttpPath.getClothIfo(), volleyRequest);
//    }

    /**
     * 定义下单popupwindow
     *
     * @param view
     */
    public void showOrderWindow(View view) {
        View contentView = LayoutInflater.from(getActivity()).inflate(
                R.layout.view_pop_bagwash, null);
        CustomMsyhTV cvPrice = ButterKnife.findById(contentView, R.id.cv_price);
        RadioButton rbMin = ButterKnife.findById(contentView, R.id.rb_min);
        mTvNum = ButterKnife.findById(contentView, R.id.tv_num);
        RadioButton rbAdd = ButterKnife.findById(contentView, R.id.rb_add);
        Button btnPay = ButterKnife.findById(contentView, R.id.btn_pay);
        mNum = 1;
        cvPrice.setText(Contant.PRICE_BAGWASH);
        rbMin.setOnClickListener(this);
        rbAdd.setOnClickListener(this);
        btnPay.setOnClickListener(this);

        // PopupWindow显示位置
        mPopupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, 594, true);
        // 接收点击事件
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        // 必须实现,否则点击外部区域和Back键都无法dismiss
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0xb0000000));
        backgroundAlpha(0.5f);
        // 显示
        mPopupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 40);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
    }

    /**
     * 袋洗popupwindow内容的监听实现
     *
     * @param view
     */
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_min:
                if (mNum > 1) {
                    mNum = mNum - 1;
                    mTvNum.setText(String.valueOf(mNum));
                } else
                    mTvNum.setText(String.valueOf(1));
                break;

            case R.id.rb_add:
                mNum = mNum + 1;
                mTvNum.setText(String.valueOf(mNum));
                break;

            case R.id.btn_pay:
                String bagWash = getString(R.string.text_bag_wash);
                if (App.washOrderList.size() == 0)
                    App.washOrderList.add(new WashOrder(bagWash, mNum, Contant.PRICE_BAGWASH));
                else
                    for (int i = 0; i < App.washOrderList.size(); i++) {
                        WashOrder washOrder = App.washOrderList.get(i);
                        if (washOrder.getWashCategory().equals(bagWash)) {
                            washOrder.setWashNum(washOrder.getWashNum() + mNum);
                            break;
                        }
                    }
                mPopupWindow.dismiss();
                break;

            default:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
//        App.getQueue().cancelAll(Contant.TAG_STRING_REQUEST);
//        App.getQueue().cancelAll("Sum");
    }
}
