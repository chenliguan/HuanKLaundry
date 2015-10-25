package com.guan.o2o.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.guan.o2o.common.Constant;

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
    private Boolean hasInitData = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 加入rootView,缓存加载后的view,如果有就不重新加载数据
        if (rootView == null)
            rootView = initView(inflater, container);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 加入判断是否已经加载数据完成的标志变量,如果已经加载了数据就不重新加载数据。
        if (!hasInitData) {
            initVariable();
            bindData();
            hasInitData = true;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((ViewGroup) rootView.getParent()).removeView(rootView);
    }

    /**
     * Toast公共方法
     * @param pMsg
     */
    public void showMsg(String pMsg) {
        Toast.makeText(getActivity(), pMsg, Toast.LENGTH_SHORT).show();
    }
    /**
     * intent 跳转Activity公共方法
     *
     * @param pClass
     */
    public void openActivity(Class<?> pClass) {
        Intent _intent = new Intent(getActivity(), pClass);
        startActivity(_intent);
    }

    /**
     * intent 跳转Activity并finish()公共方法
     * @param pClass
     */
    public void openActivityFn(Class<?> pClass) {
        Intent _intent = new Intent(getActivity(),pClass);
        startActivity(_intent);
        getActivity().finish();
    }

    /**
     * startActivityForResult()发请求，跳转到Activity
     *
     * @param pClass
     * @param value
     * @param requestCode
     */
    public void requestActivity(Class<?> pClass, String value, int requestCode) {
        // 第二个参数是请求码,是一个唯一值
        Intent _intent = new Intent(getActivity(), pClass);
        _intent.putExtra(Constant.INTENT_PARAM, value);
        startActivityForResult(_intent, requestCode);
    }


    /**
     * 设置添加屏幕的背景透明度,0.0-1.0
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getActivity().getWindow().setAttributes(lp);
    }

    /**
     * 子类实现初始化View操作(子类自己调用)
     */
    public abstract View initView(LayoutInflater inflater, ViewGroup container);

    /**
     * 子类实现初始化数据操作(子类自己调用)
     */
    public abstract void initVariable();

    /**
     * 子类实现绑定/设置数据操作(子类自己调用)
     */
    public abstract void bindData();

}
