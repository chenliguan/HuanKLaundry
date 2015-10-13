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
import com.guan.o2o.application.App;
import com.guan.o2o.common.Contant;
import com.guan.o2o.model.WashOrder;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 洗衣篮Adapter
 *
 * @author Guan
 * @file com.guan.o2o.adapter
 * @date 2015/9/21
 * @Version 1.0
 */
public class WashOrderAdapter extends BaseToAdapter<WashOrder> {

    private int mNum;
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
        } else
            return mList.size() + 1;
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        View normalView;
        View firstView;
        mCurrentType = getItemViewType(position);

        if (mCurrentType == ITEM_NORMAL) {
            // 普通项
            normalView = convertView;
            final NormalHolder normalHolder;
            if (normalView != null) {
                normalHolder = (NormalHolder) normalView.getTag();
            } else {
                normalView = LayoutInflater.from(mContext).inflate(R.layout.item_basket_normal, null);
                normalHolder = new NormalHolder(normalView);
                normalView.setTag(normalHolder);
            }
            convertView = normalView;

            WashOrder washOrder = (WashOrder) getItem(position);
//            normalHolder.ivCloth.setImageResource();
            mNum = washOrder.getWashNum();
            normalHolder.tvNum.setText(washOrder.getWashNum() + "");
            normalHolder.tvSinglePrice.setText(washOrder.getWashPrice());
            normalHolder.tvCategory.setText(washOrder.getWashCategory());

            normalHolder.rbMin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mNum > 1) {
                        mNum = mNum - 1;
                        normalHolder.tvNum.setText(String.valueOf(mNum));
                        App.washOrderList.get(position).setWashNum(mNum);
                    } else {
                        normalHolder.tvNum.setText(String.valueOf(1));
                        App.washOrderList.get(position).setWashNum(1);
                    }
                }
            });
            normalHolder.rbAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mNum = mNum + 1;
                    normalHolder.tvNum.setText(String.valueOf(mNum));
                    App.washOrderList.get(position).setWashNum(mNum);
                }
            });
            normalHolder.ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    App.washOrderList.remove(position);
                    // 更新页面
                    notifyDataSetChanged();
                }
            });

        } else if (mCurrentType == ITEM_FIRST) {
            // 添加的第一项
            firstView = convertView;
            final FirstHolder firstHolder;
            if (firstView != null) {
                firstHolder = (FirstHolder) firstView.getTag();
            } else {
                firstView = LayoutInflater.from(mContext).inflate(R.layout.item_basket_first, null);
                firstHolder = new FirstHolder(firstView);
                firstView.setTag(firstHolder);
            }
            convertView = firstView;
        }

        return convertView;
    }

    /**
     * 普通项
     */
    static class NormalHolder {
        @InjectView(R.id.iv_cloth)
        ImageView ivCloth;
        @InjectView(R.id.tv_category)
        TextView tvCategory;
        @InjectView(R.id.rb_min)
        RadioButton rbMin;
        @InjectView(R.id.tv_num)
        TextView tvNum;
        @InjectView(R.id.rb_add)
        RadioButton rbAdd;
        @InjectView(R.id.tv_single_price)
        TextView tvSinglePrice;
        @InjectView(R.id.iv_delete)
        ImageView ivDelete;

        NormalHolder(View view) {
            ButterKnife.inject(this, view);
        }

//        @OnClick({R.id.rb_min, R.id.rb_add})
//        public void onClick(View view) {
//            switch (view.getId()) {
//                case R.id.rb_min:
//                    if (mNum > 1)
//                        tvNum.setText(String.valueOf(mNum--));
//                    else
//                        tvNum.setText(String.valueOf(1));
//                    break;
//
//                case R.id.rb_add:
//                    tvNum.setText(String.valueOf(mNum++));
//                    break;
//
//                default:
//                    break;
//            }
//        }
    }

    /**
     * 添加的第一项
     */
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
