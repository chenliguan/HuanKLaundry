package com.guan.o2o.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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
    private Context context;
    private TextView mTvNum;
    private WashOrder washOrder;
    private ArrayList<View> mListViews;
    public PopupWindow mPopupWindow;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    /**
     * 自定义progressDialog
     * @param context
     * @param msg
     * @return
     */
    public Dialog createLoadingDialog(Context context, String msg) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_loading_dialog, null);
        LinearLayout layout = ButterKnife.findById(view, R.id.dialog_view);
        final ImageView imageView = ButterKnife.findById(view,R.id.iv_loading);
        TextView tvTips = ButterKnife.findById(view, R.id.tv_tips);
        // 加载动画
        Animation animation = AnimationUtils.loadAnimation(
                context, R.anim.anim_loading);
        imageView.startAnimation(animation);
        tvTips.setText(msg);
        // 创建自定义样式dialog
        final Dialog loadingDialog = new Dialog(context, R.style.Loading_dialog);
        // 不可用“返回键”取消
        loadingDialog.setCancelable(false);
        // 设置布局
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
        loadingDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                imageView.clearAnimation();
            }
        });
        return loadingDialog;
    }

}
