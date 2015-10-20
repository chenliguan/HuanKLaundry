package com.guan.o2o.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.guan.o2o.R;
import com.guan.o2o.common.HttpPath;
import com.guan.o2o.model.ServiceNote;
import com.loopj.android.image.SmartImageView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author Guan
 * @file com.guan.o2o.adapter
 * @date 2015/9/21
 * @Version 1.0
 */
public class ServiceNoteAdapter extends BaseToAdapter {

    private List<ServiceNote.MeansInfoEntity> mList;
    private Context mContext;

    public ServiceNoteAdapter(Context context, List<ServiceNote.MeansInfoEntity> list) {
        super(context, list);
        mContext = context;
        mList = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_service_note, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder.svNum.setImageUrl(HttpPath.getNumfo(position + 1));
        holder.tvNote.setText(mList.get(position).getTextMeans());

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.sv_num)
        SmartImageView svNum;
        @InjectView(R.id.tv_note)
        TextView tvNote;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
