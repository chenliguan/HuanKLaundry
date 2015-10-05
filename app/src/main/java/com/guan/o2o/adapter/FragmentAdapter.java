package com.guan.o2o.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.guan.o2o.common.Contant;
import com.guan.o2o.fragment.BasketFragment;
import com.guan.o2o.fragment.HomeFragment;
import com.guan.o2o.fragment.MoreFragment;
import com.guan.o2o.fragment.MyFragment;

/**
 * Fragment适配器
 *
 * @author Guan
 * @file com.guan.o2o.adapter
 * @date 2015/9/29
 * @Version 1.0
 */
public class FragmentAdapter extends FragmentPagerAdapter {

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int id) {
        switch (id) {
            case Contant.TAB_HOME:
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;

            case Contant.TAB_BASKET:
                BasketFragment basketFragment = new BasketFragment();
                return basketFragment;

            case Contant.TAB_MY:
                MyFragment myFragment = new MyFragment();
                return myFragment;

            case Contant.TAB_MORE:
                MoreFragment moreFragment = new MoreFragment();
                return moreFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return Contant.TAB_COUNT;
    }
}
