package com.guan.o2o.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * 广播轮询Adapter
 *
 * @author Guan
 * @file com.guan.o2o.adapter
 * @date 2015/10/3
 * @Version 1.0
 */
public class PollPagerAdapter extends PagerAdapter {

    private ArrayList<View> mViewlist;

    public PollPagerAdapter(ArrayList<View> viewlist) {
        this.mViewlist = viewlist;
    }

    @Override
    public int getCount() {
        // 设置成最大,实现无限循环
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    /**
     * Warning：不要在这里调用removeView
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // 对ViewPager页号求模取出View列表要显示的项
        position %= mViewlist.size();
        if (position < 0) {
            position = mViewlist.size() + position;
        }

        View _view = mViewlist.get(position);
        // 如果View已经在之前添加到了一个父组件,则必须先remove
        // 否则会抛出IllegalStateException。
        ViewParent viewParent = _view.getParent();
        if (viewParent != null) {
            ViewGroup viewGroup = (ViewGroup) viewParent;
            viewGroup.removeView(_view);
        }
        container.addView(_view);
        return _view;
    }
}
