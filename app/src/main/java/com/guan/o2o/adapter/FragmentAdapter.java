package com.guan.o2o.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.guan.o2o.activity.MainActivity;
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
    public final static int TAB_COUNT = 4;

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int id) {
        switch (id) {
            case MainActivity.TAB_HOME:
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;

            case MainActivity.TAB_BASKET:
                BasketFragment basketFragment = new BasketFragment();
                return basketFragment;

            case MainActivity.TAB_MY:
                MyFragment myFragment = new MyFragment();
                return myFragment;

            case MainActivity.TAB_MORE:
                MoreFragment moreFragment = new MoreFragment();
                return moreFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }
}
