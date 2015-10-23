package com.guan.o2o.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import com.guan.o2o.R;
import com.guan.o2o.application.App;
import com.guan.o2o.common.Contant;
import com.guan.o2o.model.WashOrder;
import com.guan.o2o.utils.CustomMsyhTV;
import com.loopj.android.image.SmartImageView;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * 框架类封装业务相关的方法
 *
 * @author Guan
 * @file com.guan.o2o.fragment
 * @date 2015/10/8
 * @Version 1.0
 */
public abstract class FrameFragment extends BaseFragment implements View.OnClickListener {

    private int mNum;
    private String mWashName;
    private String mAmount;
    private String mWashHeadUrl;
    private TextView mTvNum;
    public ImageView ivPopBasket;
    public PopupWindow orderWindow;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

//    /**
//     * 自定义progressDialog
//     *
//     * @param context
//     * @param msg
//     * @return
//     */
//    public Dialog createLoadingDialog(Context context, String msg) {
//        View view = LayoutInflater.from(context).inflate(R.layout.view_loading_dialog, null);
//        LinearLayout layout = ButterKnife.findById(view, R.id.dialog_view);
//        final ImageView imageView = ButterKnife.findById(view, R.id.iv_loading);
//        TextView tvTips = ButterKnife.findById(view, R.id.tv_tips);
//        // 加载动画
//        Animation animation = AnimationUtils.loadAnimation(
//                context, R.anim.anim_loading);
//        imageView.startAnimation(animation);
//        tvTips.setText(msg);
//        // 创建自定义样式dialog
//        final Dialog loadingDialog = new Dialog(context, R.style.Loading_dialog);
//        // 不可用“返回键”取消
//        loadingDialog.setCancelable(false);
//        // 设置布局
//        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
//        loadingDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialogInterface) {
//                imageView.clearAnimation();
//            }
//        });
//        return loadingDialog;
//    }

    /**
     * 定义下单popupwindow
     *
     * @param view
     */
    public void showOrderWindow(View view, String washHeadUrl, String washName, String amount) {
        View contentView = LayoutInflater.from(getActivity()).inflate(
                R.layout.view_pop_bagwash, null);
        SmartImageView sivWashHead = ButterKnife.findById(contentView, R.id.siv_wash_head);
        TextView tvWash = ButterKnife.findById(contentView, R.id.tv_bag);
        CustomMsyhTV cvPrice = ButterKnife.findById(contentView, R.id.cv_price);
        RadioButton rbMin = ButterKnife.findById(contentView, R.id.rb_min);
        mTvNum = ButterKnife.findById(contentView, R.id.tv_num);
        RadioButton rbAdd = ButterKnife.findById(contentView, R.id.rb_add);
        Button btnAdd = ButterKnife.findById(contentView, R.id.btn_add);
        // 获取到AWashActivity的ImageView属性
        ivPopBasket = (ImageView) getActivity().findViewById(R.id.iv_pop_basket);

        mNum = 1;
        mWashHeadUrl = washHeadUrl;
        mWashName = washName;
        mAmount = amount;
        tvWash.setText(mWashName);
        cvPrice.setText(amount);
        sivWashHead.setImageUrl(washHeadUrl, R.mipmap.ic_pop_bag, R.mipmap.ic_default);
        rbMin.setOnClickListener(this);
        rbAdd.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        // PopupWindow显示位置
        orderWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, 594, true);
        // 接收点击事件
        orderWindow.setFocusable(true);
        orderWindow.setOutsideTouchable(true);
        // 必须实现,否则点击外部区域和Back键都无法dismiss
        orderWindow.setBackgroundDrawable(new ColorDrawable(0xb0000000));
        backgroundAlpha(0.5f);
        // 显示
        orderWindow.showAtLocation(view, Gravity.BOTTOM, 0, 40);
        orderWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
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

            case R.id.btn_add:
                if (App.washOrderList.size() == 0)
                    App.washOrderList.add(new WashOrder(mWashName, mWashHeadUrl, mNum, mAmount));
                else
                    for (int i = 0; i < App.washOrderList.size(); i++) {
                        WashOrder washOrder = App.washOrderList.get(i);
                        if (washOrder.getWashCategory().equals(mWashName))
                            washOrder.setWashNum(washOrder.getWashNum() + mNum);
                        else
                            App.washOrderList.add(new WashOrder(mWashName, mWashHeadUrl, mNum, mAmount));
                        break;
                    }
                orderWindow.dismiss();
                ivPopBasket.setImageResource(R.mipmap.ic_pop_basket_p);
                showMsg(getString(R.string.text_added_basket));
                break;

            default:
                break;
        }
    }
}
