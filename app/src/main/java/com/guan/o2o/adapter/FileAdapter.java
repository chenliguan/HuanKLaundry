package com.guan.o2o.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.guan.o2o.model.FileBean;
import java.util.List;
import butterknife.ButterKnife;

/**
 * @author Guan
 * @file com.guan.o2o.adapter
 * @date 2015/9/21
 * @Version 1.0
 */
public class FileAdapter extends BaseToAdapter {

    private List<FileBean> mList;
    private Context mContext;
    private FileBean mFileBean;

    public FileAdapter(Context context, List<FileBean> list) {
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
//            convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_line, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
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
