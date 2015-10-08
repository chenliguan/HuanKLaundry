package com.guan.o2o.adapter;

import android.app.Activity;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;

/**
 * 适配器
 *
 * @author Guan
 * @file com.guan.o2o.adapter
 * @date 2015/8/10
 * @Version 1.0
 */
public class ViewPagerAdapter extends PagerAdapter {

    private ArrayList<View> mList;
    private Activity mActivity;

    public ViewPagerAdapter(ArrayList<View> list, Activity activity) {
        this.mList = list;
        this.mActivity = activity;
    }

    @Override
    public int getCount() {
        // 返回页面数目实现有限滑动效果
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public Object instantiateItem(View container, int position) {
        ((ViewPager) container).addView(mList.get(position), 0);
        return mList.get(position);
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView(mList.get(position));
    }
}
