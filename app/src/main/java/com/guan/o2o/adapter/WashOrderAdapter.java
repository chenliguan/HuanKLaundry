package com.guan.o2o.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.guan.o2o.R;
import com.guan.o2o.model.WashOrder;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 洗衣篮Adapter
 *
 * @author Guan
 * @file com.guan.o2o.adapter
 * @date 2015/9/21
 * @Version 1.0
 */
public class WashOrderAdapter extends BaseToAdapter<WashOrder> {

    private Context mContext;
    private int mCurrentType;
    private List<WashOrder> mList;
    private ImageView mIvBasketNull;

    private final int ITEM_NORMAL = 0;
    private final int ITEM_FIRST = 1;
    private final int ITEM_NUM = 2;

    public WashOrderAdapter(Context context, List<WashOrder> list, ImageView ivBasketNull) {
        super(context, list);
        mContext = context;
        mList = list;
        mIvBasketNull = ivBasketNull;
    }

    /**
     * 复写父类方法
     *
     * @return
     */
    @Override
    public int getCount() {
        if (mList.size() == 0) {
            mIvBasketNull.setVisibility(View.VISIBLE);
            return 0;
        } else {
            return mList.size() + 1;
        }
    }

    /**
     * 根据position判断item是普通项还是添加项
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        int count = mList.size();
        if (position < count) {
            return ITEM_NORMAL;
        } else {
            return ITEM_FIRST;
        }
    }

    @Override
    public int getViewTypeCount() {
        return ITEM_NUM;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View normalView = null;
        View firstView = null;
        mCurrentType = getItemViewType(position);

        if (mCurrentType == ITEM_NORMAL) {
            // 普通项
            normalView = convertView;
            NormalHolder holder;
            if (normalView != null) {
                holder = (NormalHolder) normalView.getTag();
            } else {
                normalView = LayoutInflater.from(mContext).inflate(R.layout.item_basket_normal, null);
                holder = new NormalHolder(normalView);
                normalView.setTag(holder);
            }
            convertView = normalView;

        } else if (mCurrentType == ITEM_FIRST) {
            // 添加的第一项
            firstView = convertView;
            FirstHolder holder;
            if (firstView != null) {
                holder = (FirstHolder) firstView.getTag();
            } else {
                firstView = LayoutInflater.from(mContext).inflate(R.layout.item_basket_first, null);
                holder = new FirstHolder(firstView);
                firstView.setTag(holder);
            }
            convertView = firstView;
        }

        return convertView;
    }

    static class NormalHolder {
        @InjectView(R.id.iv_cloth)
        ImageView ivCloth;
        @InjectView(R.id.rb_min)
        RadioButton rbMin;
        @InjectView(R.id.rb_add)
        RadioButton rbAdd;
        @InjectView(R.id.tv_single_cost)
        TextView tvSingleCost;
        @InjectView(R.id.iv_delete)
        ImageView ivDelete;

        NormalHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    static class FirstHolder {
        @InjectView(R.id.iv_laundry_coupon)
        ImageView ivLaundryCoupon;
        @InjectView(R.id.tv_write_info)
        TextView tvWriteInfo;
        @InjectView(R.id.iv_write_info)
        ImageView ivWriteInfo;
        @InjectView(R.id.rlyt_write)
        RelativeLayout rlytWrite;
        @InjectView(R.id.btn_pay)
        Button btnPay;

        FirstHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
