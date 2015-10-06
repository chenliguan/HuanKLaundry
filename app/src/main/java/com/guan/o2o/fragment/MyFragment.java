package com.guan.o2o.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guan.o2o.R;

/**
 * 我的主页Fragment
 *
 * @author Guan
 * @file com.guan.o2o.fragment
 * @date 2015/10/6
 * @Version 1.0
 */
public class MyFragment extends Fragment {


    public MyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my, container, false);
    }


}
