package com.guan.o2o.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author Guan
 * @file com.guan.o2o.utils
 * @date 2015/10/6
 * @Version 1.0
 */
public class CustomTextView extends TextView {

    public CustomTextView(Context context) {
        super(context);
        init(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /***
     * 设置字体
     *
     * @return
     */
    public void init(Context context) {
        AssetManager assetMgr = context.getAssets();
        Typeface font = Typeface.createFromAsset(assetMgr,"fonts/msyh.ttf");
        setTypeface(font);
    }
}
