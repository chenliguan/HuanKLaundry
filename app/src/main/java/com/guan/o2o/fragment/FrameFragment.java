package com.guan.o2o.fragment;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import com.guan.o2o.R;
import com.guan.o2o.application.App;
import com.guan.o2o.common.Constant;
import com.guan.o2o.model.WashOrder;
import com.guan.o2o.utils.ConvertUtil;
import com.guan.o2o.utils.SharedPfeUtil;
import com.guan.o2o.view.CustomMsyhTV;
import com.loopj.android.image.SmartImageView;

import java.io.IOException;

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
    private boolean isNeedPopTo;
    private boolean isNeedPopM;
    private TextView mTvNum;
    public ImageView ivPopBasket;
    public ImageView ivHave;
    public PopupWindow orderWindow;
    public PopupWindow tipsWindow;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isNeedPopTo = false;
        isNeedPopM = false;
    }

    /**
     * 定义提示PopupWindow
     *
     * @param view
     */
    public void showTipsWindow(View view,String tipT,String tipC) {
        View contentView = LayoutInflater.from(getActivity()).inflate(
                R.layout.view_pop_tip, null);
        TextView tipTitle = ButterKnife.findById(contentView, R.id.tv_tip_title);
        TextView tipContent = ButterKnife.findById(contentView, R.id.tv_tip_content);
        tipTitle.setText(tipT);
        tipContent.setText(tipC);

        tipsWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT, true);
        tipsWindow.setOutsideTouchable(true);
        // 必须实现,否则点击外部区域和Back键都无法dismiss
        tipsWindow.setBackgroundDrawable(new BitmapDrawable());
        backgroundAlpha(0.5f);
        // 设置好参数之后再show
        tipsWindow.showAtLocation(view, Gravity.CENTER_VERTICAL, 0, 0);
        // 隐退监听
        tipsWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
        // 停留2秒,隐退
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tipsWindow.dismiss();
            }
        }, Constant.POPWIN_DELAY_MS);
    }

    /**
     * 定义AWashAtivity的下单popupwindow
     *
     * @param view
     */
    public void showOrderWindowTo(View view, String washHeadUrl, String washName, String amount) {
        isNeedPopTo = true;
        // 获取到AWashActivity的ImageView属性
        ivPopBasket = (ImageView) getActivity().findViewById(R.id.iv_pop_basket);
        showOrderWindow(view, washHeadUrl, washName, amount);
    }

    /**
     * 定义MainAtivity的下单popupwindow
     *
     * @param view
     */
    public void showOrderWindowM(View view, String washHeadUrl, String washName, String amount) {
        isNeedPopM = true;
        // 获取到MainActivity的ImageView属性
        ivHave = (ImageView) getActivity().findViewById(R.id.iv_have);
        showOrderWindow(view, washHeadUrl, washName, amount);
    }

    /**
     * 定义通用下单popupwindow
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
        backgroundAlpha(0.5f);
        // 必须实现,否则点击外部区域和Back键都无法dismiss
        orderWindow.setBackgroundDrawable(new ColorDrawable(0xb0000000));
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
                        WashOrder washOrder = (WashOrder) App.washOrderList.get(i);
                        if (washOrder.getWashCategory().equals(mWashName))
                            washOrder.setWashNum(washOrder.getWashNum() + mNum);
                        else
                            App.washOrderList.add(new WashOrder(mWashName, mWashHeadUrl, mNum, mAmount));
                        break;
                    }
                orderWindow.dismiss();

                // 将集合转化为字符串保存在本地
                SharedPfeUtil.sharedOrderInfo(getActivity());
                showMsg(getString(R.string.text_added_basket));
                if (isNeedPopTo) {
                    ivPopBasket.setImageResource(R.mipmap.ic_pop_basket_p);
                    isNeedPopTo = false;
                }
                if (isNeedPopM) {
                    ivHave.setVisibility(View.VISIBLE);
                    isNeedPopM = false;
                }
                break;

            default:
                break;
        }
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
}
