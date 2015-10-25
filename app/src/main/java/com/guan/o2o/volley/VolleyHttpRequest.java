package com.guan.o2o.volley;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.guan.o2o.application.App;
import com.guan.o2o.common.Constant;

import org.json.JSONObject;

import java.util.Map;

/**
 * 封装所有的请求调用
 *
 * @author Guan
 * @file com.guan.o2o.volley
 * @date 2015/9/28
 * @Version 1.0
 */
public class VolleyHttpRequest {

    /**
     * 1.1 StringRequest GET方式
     *
     * @param url           地址
     * @param volleyRequest 回调函数
     */
    public static void String_request(String url, VolleyHandler<String> volleyRequest) {
        Volley_StringRequest(Method.GET, url, null, volleyRequest);
    }

    /**
     * 1.2 StringRequset POST方式
     *
     * @param url           地址
     * @param map           参数
     * @param volleyRequest 回调函数
     */
    public static void String_request(String url, final Map<String, String> map, VolleyHandler<String> volleyRequest) {
        Volley_StringRequest(Method.POST, url, map, volleyRequest);
    }

    /**
     * 2.1 JsonObjectRequest GET 请求
     *
     * @param url           请求地址
     * @param volleyRequest 回调函数对象
     */
    public static void JsonObject_Request(String url, VolleyHandler<JSONObject> volleyRequest) {
        Volley_JsonObjectRequest(Method.GET, url, null, volleyRequest);
    }

    /**
     * 2.2 JsonObjectRequest POST 请求
     *
     * @param url           请求地址
     * @param jsonObject    请求参数
     * @param volleyRequest 回调函数对象
     */
    public static void JsonObject_Request(String url, JSONObject jsonObject, VolleyHandler<JSONObject> volleyRequest) {
        Volley_JsonObjectRequest(Method.POST, url, jsonObject, volleyRequest);
    }

    /**
     * 3.1 ImageRequest 默认大小原图不变
     *
     * @param url           地址
     * @param volleyRequest 回调函数
     */
    public static void Image_request(String url, VolleyHandler<Bitmap> volleyRequest) {
        Volley_ImageRequest(url, 0, 0, volleyRequest);
    }

    /**
     * 3.2 ImageRequest 自定义的缩放
     *
     * @param url           地址
     * @param maxWidth      最大宽度
     * @param maxHeight     最大高度
     * @param volleyRequest 回调函数
     */
    public static void Image_request(String url, int maxWidth, int maxHeight, VolleyHandler<Bitmap> volleyRequest) {
        Volley_ImageRequest(url, maxWidth, maxHeight, volleyRequest);
    }

    /**
     * 4.1 自定义图片的宽度值
     *
     * @param url
     * @param imageListener
     * @param maxWidth
     * @param maxHidth
     */
    public static void Image_Loader(String url, ImageListener imageListener, int maxWidth, int maxHidth) {
        Volley_ImageLoader(url, imageListener, maxWidth, maxHidth);
    }

    /**
     * 4.2 默认值,原始比例
     *
     * @param url           地址
     * @param imageListener 图片监听
     */
    public static void Image_Loader(String url, ImageListener imageListener) {
        Volley_ImageLoader(url, imageListener, 0, 0);
    }

    /**
     * 1 封装 StringRequest 数据请求
     *
     * @param method        方式
     * @param url           地址
     * @param params        参数
     * @param volleyRequest 回调对象
     */
    private static void Volley_StringRequest(int method, String url, final Map<String, String> params, VolleyHandler<String> volleyRequest) {
        StringRequest stringrequest = new StringRequest(method, url, volleyRequest.reqLis, volleyRequest.reqErr) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        stringrequest.setTag(Constant.TAG_STRING_REQUEST);
        App.getQueue().add(stringrequest);
    }

    /**
     * 2 封装 JsonObjectRequest 请求方法
     *
     * 客户端以json串的post请求方式进行提交,服务端返回json串（JsonObject）
     *
     * @param method        方式
     * @param url           地址
     * @param jsonObject    参数
     * @param volleyRequest 回调函数对象
     */
    private static void Volley_JsonObjectRequest(int method, String url, JSONObject jsonObject, VolleyHandler<JSONObject> volleyRequest) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(method, url, jsonObject, volleyRequest.reqLis, volleyRequest.reqErr);
        jsonObjectRequest.setTag(Constant.TAG_JSON_REQUEST);
        App.getQueue().add(jsonObjectRequest);
    }

    /**
     * 3 封装 ImageRequest 请求方法
     * <p/>
     * 请求单张图片使用ImageRequest即可
     *
     * @param url           地址
     * @param maxWidth      最大宽度
     * @param maxHeight     最大高度
     * @param volleyRequest 回调函数对象
     */
    private static void Volley_ImageRequest(String url, int maxWidth, int maxHeight, VolleyHandler<Bitmap> volleyRequest) {
        ImageRequest imageRequest = new ImageRequest(url, volleyRequest.reqLis, maxWidth, maxHeight, Config.RGB_565, volleyRequest.reqErr);
        imageRequest.setTag(Constant.TAG_IMAGE_REQUEST);
        App.getQueue().add(imageRequest);
    }

    /**
     * 4 封装 ImageLoader 方法
     * <p/>
     * 请求大量图片建议使用 ImageLoader
     *
     * @param url           地址
     * @param imageListener 图片监听
     * @param maxWidth
     * @param maxHidth
     */
    private static void Volley_ImageLoader(String url, ImageListener imageListener, int maxWidth, int maxHidth) {
        // 设置图片缓存:体现ImageLoader的优势,使用LruBitmap + ImageCache实现
        ImageLoader imageLoader = new ImageLoader(App.getQueue(),
                new VolleyBitmapCache());
        // 加载图片图片监听（默认图片，错误图片）和imageView
        imageLoader.get(url, imageListener, maxWidth, maxHidth);
    }

    /**
     * 5 自定义GsonRequest类
     *
     * 因为：Volley中默认并不支持使用自家的GSON来解析数据
     * 扩展自定义方法:涉及到匿名类型T故需要model借助对象来实现
     */
}
