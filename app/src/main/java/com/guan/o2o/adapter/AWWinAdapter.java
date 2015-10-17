package com.guan.o2o.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guan.o2o.R;
import com.guan.o2o.model.WinterCloth;
import com.guan.o2o.utils.CustomMsyhTV;
import com.loopj.android.image.SmartImageView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.guan.o2o.common.HttpPath.getClothIvIfo;

/**
 * 我的主页GridVieW适配器
 *
 * @author Guan
 * @file com.example.guan.adapter
 * @date 2015/10/8
 * @Version 1.0
 */
public class AWWinAdapter extends BaseToAdapter {

    public List<WinterCloth.WashInfoEntity> mList;

    public AWWinAdapter(Context context, List<WinterCloth.WashInfoEntity> list) {
        super(context, list);
        this.mList = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = getLayoutInflater().inflate(R.layout.item_grid_aw_win, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder.tvName.setText(mList.get(position).getWashName());
        holder.cvPrice.setText(mList.get(position).getAmount());

        // 网络请求图片
//        ImageLoader.ImageListener listener = ImageLoader.getImageListener(holder.ivIcon,
//                R.mipmap.ic_default, R.mipmap.ic_default);
//        VolleyHttpRequest.Image_Loader(getClothIvIfo(position + 10), listener);
        holder.sivIcon.setImageUrl(getClothIvIfo(position + 10));

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.siv_Icon)
        SmartImageView sivIcon;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.cv_price)
        CustomMsyhTV cvPrice;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
