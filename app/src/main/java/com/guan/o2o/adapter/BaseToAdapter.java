package com.guan.o2o.adapter;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.PopupWindow;

import com.guan.o2o.R;

import java.util.List;

/**
 * @author Guan
 * @file com.guan.aaprojects.adapter
 * @date 2015/8/29
 * @Version 1.0
 */
public abstract class BaseToAdapter<T> extends BaseAdapter {

    private List<T> mList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public BaseToAdapter(Context context, List<T> list) {
        this.mContext = context;
        this.mList = list;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public LayoutInflater getLayoutInflater() {
        return mLayoutInflater;
    }

    public Context getContext() {
        return mContext;
    }

    public List getList() {
        return mList;
    }

    @Override
    public int getCount() {
        if (mList != null && mList.size() > 0) {
            return mList.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        if (mList != null && mList.size() > 0) {
            return mList.get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
