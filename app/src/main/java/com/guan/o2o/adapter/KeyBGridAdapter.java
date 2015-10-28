package com.guan.o2o.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guan.o2o.R;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 软键盘GridVieW适配器
 *
 * @author Guan
 * @file com.example.guan.adapter
 * @date 2015/10/8
 * @Version 1.0
 */
public class KeyBGridAdapter extends BaseToAdapter {

    private int mCurrentType;
    private List<Integer> mList;
    private final int ITEM_NUM = 3;
    private final int ITEM_NORMAL = 0;
    private final int ITEM_NULL = 1;
    private final int ITEM_DELETE = 2;

    public KeyBGridAdapter(Context context, List list) {
        super(context, list);
        mList = list;
    }

    /**
     * 根据position判断item是普通项还是添加项
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (position == 9)
            return ITEM_NULL;
        else if (position == 11)
            return ITEM_DELETE;
        else
            return ITEM_NORMAL;
    }

    @Override
    public int getViewTypeCount() {
        return ITEM_NUM;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View normalView;
        View nullView;
        View deleteView;
        mCurrentType = getItemViewType(position);

        if (mCurrentType == ITEM_NORMAL) {
            // 普通项
            normalView = convertView;
            final NormalHolder normalHolder;
            if (normalView != null) {
                normalHolder = (NormalHolder) normalView.getTag();
            } else {
                normalView = getLayoutInflater().inflate(R.layout.item_grid_key_nml, null);
                normalHolder = new NormalHolder(normalView);
                normalView.setTag(normalHolder);
            }
            convertView = normalView;

            normalHolder.btNmlNum.setText(getList().get(position).toString());
        } else if (mCurrentType == ITEM_NULL) {
            // 空白项
            nullView = convertView;
            final NullHolder nullHolder;
            if (nullView != null) {
                nullHolder = (NullHolder) nullView.getTag();
            } else {
                nullView = getLayoutInflater().inflate(R.layout.item_grid_key_null, null);
                nullHolder = new NullHolder(nullView);
                nullView.setTag(nullHolder);
            }
            convertView = nullView;
        } else if (mCurrentType == ITEM_DELETE) {
            // 删除项
            deleteView = convertView;
            final DeleteHolder deleteHolder;
            if (deleteView != null) {
                deleteHolder = (DeleteHolder) deleteView.getTag();
            } else {
                deleteView = getLayoutInflater().inflate(R.layout.item_grid_key_dlt, null);
                deleteHolder = new DeleteHolder(deleteView);
                deleteView.setTag(deleteHolder);
            }
            convertView = deleteView;
//            deleteHolder.btDltNum.setText(getList().get(position).toString());
            deleteHolder.btDltNum.setText("删除");
        }

        return convertView;
    }

    static class NormalHolder {
        @InjectView(R.id.bt_nml_num)
        TextView btNmlNum;

        NormalHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    static class NullHolder {
        @InjectView(R.id.bt_null_num)
        TextView btNullNum;

        NullHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    static class DeleteHolder {
        @InjectView(R.id.bt_dlt_num)
        TextView btDltNum;

        DeleteHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
