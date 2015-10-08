package com.guan.o2o.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.guan.o2o.common.Contant;
import com.guan.o2o.fragment.BasketFragment;
import com.guan.o2o.fragment.HomeFragment;
import com.guan.o2o.fragment.MoreFragment;
import com.guan.o2o.fragment.MyHomeFragment;
import com.guan.o2o.utils.LogUtil;

import java.util.List;

/**
 * Fragment适配器
 *
 * @author Guan
 * @file com.guan.o2o.adapter
 * @date 2015/9/29
 * @Version 1.0
 */
public class FragmentAdapter extends FragmentPagerAdapter {

    public List<Fragment> fragList;

    public FragmentAdapter(FragmentManager fm, List<Fragment> fragList) {
        super(fm);
        this.fragList = fragList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragList.get(position);
    }

    @Override
    public int getCount() {
        return Contant.TAB_COUNT;
    }

//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        //super.destroyItem(container, position, object);
//    }
}
