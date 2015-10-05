package com.guan.o2o.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.guan.o2o.R;
import com.guan.o2o.model.FileBean;
import com.guan.o2o.model.WashOrder;

import java.util.List;

import butterknife.ButterKnife;

/**
 * @author Guan
 * @file com.guan.o2o.adapter
 * @date 2015/9/21
 * @Version 1.0
 */
public class WashOrderAdapter extends BaseToAdapter<WashOrder> {

    private List<WashOrder> mList;
    private Context mContext;
    private FileBean mFileBean;
    private View mViewItem;
    private int mCurrentType;
    private ImageView mIvBasketNull;

    private final int ITEM0 = 0;
    private final int ITEM1 = 1;
    private final int ITEM_NUM = 2;

    public WashOrderAdapter(Context context, List<WashOrder> list, View view_basketfrag_item, ImageView ivBasketNull) {
        super(context, list);
        mContext = context;
        mList = list;
        mViewItem = view_basketfrag_item;
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
     * 根据position判断item是listview普通项还是添加项
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        int count = mList.size();
        if (position < count) {
            return ITEM0;
        } else {
            return ITEM1;
        }
    }

    @Override
    public int getViewTypeCount() {
        return ITEM_NUM;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        mCurrentType = getItemViewType(position);

        if (mCurrentType == ITEM1) {
            // 添加项
            convertView = mViewItem;
        } else {
            // 普通项
            ViewHolder holder;
            if (convertView != null) {
                holder = (ViewHolder) convertView.getTag();
            } else {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_wash_order, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            }
        }

        return convertView;
    }

    static class ViewHolder {
//        @InjectView(R.id.iv_file)
//        ImageView ivFile;
//        @InjectView(R.id.tv_file_name)
//        TextView tvFileName;
//        @InjectView(R.id.tv_down)
//        TextView tvDown;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

}
