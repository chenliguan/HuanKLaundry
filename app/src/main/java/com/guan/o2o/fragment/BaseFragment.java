package com.guan.o2o.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 提供了fragment的封装后基类，提供context给子类使用
 *
 * @author Guan
 * @file com.guan.o2o.fragment
 * @date 2015/10/8
 * @Version 1.0
 */
public abstract class BaseFragment extends Fragment {

    //根部view
    private View rootView;
    protected Context context;
    private Boolean hasInitData = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 加入rootView,缓存加载后的view,如果有就不重新加载数据
        if (rootView == null) {
            rootView = initView(inflater, container);
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 加入判断是否已经加载数据完成的标志变量,如果已经加载了数据就不重新加载数据。
        if (!hasInitData) {
            initVariable();
            hasInitData = true;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((ViewGroup) rootView.getParent()).removeView(rootView);
    }

    /**
     * 子类实现初始化View操作
     */
    public abstract View initView(LayoutInflater inflater, ViewGroup container);

    /**
     * 子类实现初始化数据操作(子类自己调用)
     */
    public abstract void initVariable();

}
