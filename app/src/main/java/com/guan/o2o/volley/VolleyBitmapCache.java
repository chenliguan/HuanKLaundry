package com.guan.o2o.volley;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

/**
 * 使用LruCache实现图片缓存
 *
 * @author Guan
 * @file com.guan.o2o.volley
 * @date 2015/9/28
 * @Version 1.0
 */
@SuppressLint("NewApi")
public class VolleyBitmapCache implements ImageCache {

    private LruCache<String, Bitmap> cache;

    /**
     * 构造方法实现LruCache缓存图片
     */
    public VolleyBitmapCache() {
        //设置最大的 尺寸值
        int maxSize = 10 * 1024 * 1024;
        cache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
    }

    /**
     * 得到Bitmap
     *
     * @param url
     * @return
     */
    @Override
    public Bitmap getBitmap(String url) {
        return cache.get(url);
    }

    /**
     * 设置Bitmap
     *
     * @param url
     * @param bitmap
     */
    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        cache.put(url, bitmap);
    }

}
