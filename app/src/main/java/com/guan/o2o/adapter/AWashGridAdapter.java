package com.guan.o2o.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guan.o2o.R;
import com.guan.o2o.model.AWashCloth;
import com.loopj.android.image.SmartImageView;

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
public class AWashGridAdapter extends BaseToAdapter {

    public List<AWashCloth.WashInfoEntity> mList;

    public AWashGridAdapter(Context context, List<AWashCloth.WashInfoEntity> list) {
        super(context, list);
        this.mList = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = getLayoutInflater().inflate(R.layout.item_grid_a_wash, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder.tvName.setText(mList.get(position).getWashName());
        holder.cvPrice.setText(mList.get(position).getAmount());
        // 网络请求图片
//        ImageLoader.ImageListener listener = ImageLoader.getImageListener(holder.ivIcon,
//                R.mipmap.ic_default, R.mipmap.ic_default);
//        VolleyHttpRequest.Image_Loader(getClothIvIfo(position + 10), listener);
        holder.sivIcon.setImageUrl(mList.get(position).getWashHead(),R.mipmap.ic_default,R.mipmap.ic_default);

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.siv_Icon)
        SmartImageView sivIcon;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.cv_price)
        TextView cvPrice;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
