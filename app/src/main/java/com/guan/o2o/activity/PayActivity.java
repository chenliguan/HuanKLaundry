package com.guan.o2o.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.guan.o2o.R;
import com.guan.o2o.adapter.KeyBGridAdapter;
import com.guan.o2o.application.App;
import com.guan.o2o.common.Constant;
import com.guan.o2o.utils.ListUtil;
import com.guan.o2o.utils.LogUtil;
import com.guan.o2o.utils.SharedPfeUtil;
import com.guan.o2o.view.CustomMsyhTV;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnTouch;

/**
 * 支付页面
 *
 * @author Guan
 * @file com.guan.o2o.activity
 * @date 2015/10/26
 * @Version 1.0
 */
public class PayActivity extends FrameActivity {

    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.tv_balance_pay)
    TextView tvBalancePay;
    @InjectView(R.id.tv_alipay)
    TextView tvAlipay;
    @InjectView(R.id.bg_balance_ui)
    RelativeLayout bgBalanceUi;
    @InjectView(R.id.bg_alipay_ui)
    TextView bgAlipayUi;
    @InjectView(R.id.tv_focus)
    TextView tvFocus;
    @InjectView(R.id.flyt_in)
    FrameLayout flytIn;
    @InjectView(R.id.cv_balance_amout)
    CustomMsyhTV cvBalanceAmout;
    @InjectView(R.id.tv_to_up)
    TextView tvToUp;
    @InjectView(R.id.pass_a)
    TextView passA;
    @InjectView(R.id.pass_b)
    TextView passB;
    @InjectView(R.id.pass_c)
    TextView passC;
    @InjectView(R.id.pass_d)
    TextView passD;
    @InjectView(R.id.pass_e)
    TextView passE;
    @InjectView(R.id.pass_f)
    TextView passF;

    private PopupWindow mKeyBWindow;
    public List<Integer> keyList;
    public List<Integer> passList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.inject(this);
        /**
         * 初始化变量
         */
        initVariable();
    }

    /**
     * 初始化变量
     */
    private void initVariable() {
        passList = new ArrayList<Integer>();
        tvTitle.setText(R.string.title_pay);
        keyList = new ArrayList<Integer>();
        keyList.add(1);
        keyList.add(2);
        keyList.add(3);
        keyList.add(4);
        keyList.add(5);
        keyList.add(6);
        keyList.add(7);
        keyList.add(8);
        keyList.add(9);
        // null
        keyList.add(10);
        keyList.add(0);
        // delete
        keyList.add(11);
    }

    /**
     * 监听实现
     */
    @OnClick({R.id.iv_back, R.id.tv_balance_pay, R.id.tv_alipay, R.id.tv_to_up, R.id.btn_ok_pay})
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.iv_back:
                // 删除订单和刷新本地数据
                removeAndShare();
                // 向BasketFragment发送广播,更新界面
                sendBroadcast();
                break;

            case R.id.tv_balance_pay:
                tvBalancePay.setBackgroundColor(getResources().getColor(R.color.bg_red));
                tvAlipay.setBackgroundColor(getResources().getColor(R.color.white));
                bgBalanceUi.setVisibility(View.VISIBLE);
                bgAlipayUi.setVisibility(View.INVISIBLE);
                break;

            case R.id.tv_alipay:
                tvBalancePay.setBackgroundColor(getResources().getColor(R.color.white));
                tvAlipay.setBackgroundColor(getResources().getColor(R.color.bg_red));
                tvBalancePay.setTextColor(getResources().getColor(R.color.texts_grey));
                bgBalanceUi.setVisibility(View.INVISIBLE);
                bgAlipayUi.setVisibility(View.VISIBLE);
                /**
                 * 跳转支付宝
                 */
                break;

            case R.id.tv_to_up:
                break;

            case R.id.btn_ok_pay:
                String passString = setListToString();
                LogUtil.showLog("密码：" + passString);
                /**
                 * 提交到服务器，获取密码校对，提交订单
                 */
                // 删除订单和刷新本地数据(保存到“已完成”或者“代付款”等表中)
                removeAndShare();
                // 向BasketFragment发送广播,更新界面
                sendBroadcast();
                break;

            default:
                break;

        }
    }

    /**
     * 删除订单和刷新本地数据
     */
    private void removeAndShare() {
        // 删除暂时密码集合所有数据
        ListUtil.removeAll(passList);
        // 删除全局订单集合所有数据
        ListUtil.removeAll(App.washOrderList);
        // 将集合转化为字符串保存在本地
        SharedPfeUtil.sharedOrderInfo(this);
    }

    /**
     * 将集合转换成字符串
     */
    private String setListToString() {
        StringBuilder sb = null;
        if (passList.size() == Constant.PASS_NUM) {
            sb = new StringBuilder();
            for (Integer in : passList) {
                sb.append(in);
            }
        }
        // 把集合转化为字符串
        return sb != null ? sb.toString() : null;
    }

    /**
     * 向BasketFragment发送广播
     */
    private void sendBroadcast() {
        Intent intent = new Intent(Constant.ACTION_BU_UPDATEUI);
        intent.putExtra(Constant.INTENT_KEY, Constant.VALUE_PAY_ACTIVITY);
        sendBroadcast(intent);
        finish();
    }

    /**
     * 触摸输入支付宝密码
     *
     * @param view
     * @return
     */
    @OnTouch({R.id.tv_focus})
    public boolean onTouch(View view) {
        // 显示软键盘popupWindow
        showKeyBoard(view, keyList);
        return false;
    }

    /**
     * 软键盘popupwindow
     *
     * @param view
     */
    public void showKeyBoard(View view, List list) {
        View contentView = LayoutInflater.from(this).inflate(
                R.layout.view_pop_keyboard, null);
        GridView gvKeyBoard = ButterKnife.findById(contentView, R.id.gv_keyboard);
        KeyBGridAdapter keyBAdapter = new KeyBGridAdapter(this, list);
        gvKeyBoard.setAdapter(keyBAdapter);
        gvKeyBoard.setOnItemClickListener(new OnItemClickListener());

        // PopupWindow显示位置
        mKeyBWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.FILL_PARENT, 404, true);
        // 接收点击事件
        mKeyBWindow.setFocusable(true);
        mKeyBWindow.setOutsideTouchable(true);
        // 必须实现,否则点击外部区域和Back键都无法dismiss
        mKeyBWindow.setBackgroundDrawable(new ColorDrawable(0xb0000000));
        // 显示
        mKeyBWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    /**
     * 软键盘gridview监听
     */
    private class OnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            switch (position) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    judgeInput(position + 1);
                    break;

                case 10:
                    judgeInput(0);
                    break;

                case 11:
                    deleteInput();
                    break;

                default:
                    break;
            }
        }
    }

    /**
     * 软键盘输入判断，顺序地在4个textview显示
     *
     * @param i
     */
    public void judgeInput(int i) {
        // A-->D
        if (passA.getText().equals(""))
            passA.setText("●");
        else if (passB.getText().equals(""))
            passB.setText("●");
        else if (passC.getText().equals(""))
            passC.setText("●");
        else if (passD.getText().equals(""))
            passD.setText("●");
        else if (passE.getText().equals(""))
            passE.setText("●");
        else if (passF.getText().equals(""))
            passF.setText("●");

        LogUtil.showLog("position:" + i);
        LogUtil.showLog("keyList.get(position).toString():" + keyList.get(i).toString());

        // 当输入大于Constant.PASS_NUM位，不作处理
        if (passList.size() < Constant.PASS_NUM)
            passList.add(i);

        LogUtil.showLog("passList:" + passList.get(0).toString());
    }

    /**
     * 删除最后一个输入
     */
    public void deleteInput() {
        // D-->A
        if (!passF.getText().equals(""))
            passF.setText(null);
        if (!passE.getText().equals(""))
            passE.setText(null);
        if (!passD.getText().equals(""))
            passD.setText(null);
        else if (!passC.getText().equals(""))
            passC.setText(null);
        else if (!passB.getText().equals(""))
            passB.setText(null);
        else if (!passA.getText().equals(""))
            passA.setText(null);

        if (passList.size() > 0)
            passList.remove(passList.size() - 1);
    }


    /**
     * 退出程序
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // 删除订单和刷新本地数据(保存到“已完成”或者“代付款”等表中)
            removeAndShare();
            // 向BasketFragment发送广播,更新界面
            sendBroadcast();
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }

    @Override
    public void onBackPressed() {
        // 向BasketFragment发送广播,更新界面
        sendBroadcast();
        super.onBackPressed();
    }
}
