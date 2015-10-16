package com.guan.o2o.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.guan.o2o.R;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 我的主页GridVieW适配器
 *
 * @author Guan
 * @file com.example.guan.adapter
 * @date 2015/10/8
 * @Version 1.0
 */
public class MyGridAdapter extends BaseToAdapter {

    private Integer[] mImageInteger = {
            R.drawable.bg_myorder_selector, R.drawable.bg_washcoin_selector,
            R.drawable.bg_myads_selector, R.drawable.bg_share_selector, R.drawable.bg_rcmcode_selector
    };

    public MyGridAdapter(Context context, List list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = getLayoutInflater().inflate(R.layout.item_grid_my, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder.tvName.setText(getList().get(position).toString());
        holder.ivIcon.setImageResource(mImageInteger[position]);

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_icon)
        ImageView ivIcon;
        @InjectView(R.id.tv_name)
        TextView tvName;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
