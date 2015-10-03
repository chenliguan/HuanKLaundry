package com.guan.o2o.handler;

import android.os.Handler;
import android.os.Message;

import com.guan.o2o.common.Contant;
import com.guan.o2o.fragment.HomeFragment;

import java.lang.ref.WeakReference;

/**
 * @author Guan
 * @file com.guan.o2o.handler
 * @date 2015/10/3
 * @Version 1.0
 */
public class ImageHandler extends Handler {

    // 当前选项
    private int currentItem = 0;
    // 使用弱引用避免Handler泄露,这里的泛型参数可以是Activity/Fragment等
    private WeakReference<HomeFragment> mWeakReference;

    public ImageHandler(HomeFragment fragment) {
        mWeakReference = new WeakReference<HomeFragment>(fragment);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        HomeFragment homeFragment = mWeakReference.get();
        if (homeFragment == null) {
            return;
        }
        // 检查消息队列并移除未发送的消息,避免在复杂环境下消息出现重复等问题。
        if (homeFragment.handler.hasMessages(Contant.MSG_UPDATE_IMAGE)) {
            homeFragment.handler.removeMessages(Contant.MSG_UPDATE_IMAGE);
        }
        switch (msg.what) {
            case Contant.MSG_UPDATE_IMAGE:
                currentItem++;
                homeFragment.viewpager.setCurrentItem(currentItem);
                //准备下次播放
                homeFragment.handler.sendEmptyMessageDelayed(Contant.MSG_UPDATE_IMAGE,Contant. MSG_DELAY);
                break;
            case Contant.MSG_KEEP_SILENT:
                //只要不发送消息就暂停了
                break;
            case Contant.MSG_BREAK_SILENT:
                homeFragment.handler.sendEmptyMessageDelayed(Contant.MSG_UPDATE_IMAGE, Contant.MSG_DELAY);
                break;
            case Contant.MSG_PAGE_CHANGED:
                //记录当前的页号,避免播放的时候页面显示不正确。
                currentItem = msg.arg1;
                break;
            default:
                break;
        }
    }
}
