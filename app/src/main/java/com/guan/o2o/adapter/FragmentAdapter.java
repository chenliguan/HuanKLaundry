package com.guan.o2o.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.guan.o2o.common.Contant;

import java.util.ArrayList;

/**
 * Fragment适配器
 *
 * @author Guan
 * @file com.guan.o2o.adapter
 * @date 2015/9/29
 * @Version 1.0
 */
public class FragmentAdapter extends FragmentPagerAdapter {

    public ArrayList<Fragment> fragList;

    public FragmentAdapter(FragmentManager fm, ArrayList<Fragment> fragList) {
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

}
