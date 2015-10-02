package com.guan.o2o.common;

import com.guan.o2o.R;
import com.guan.o2o.view.CustomView;

import butterknife.InjectView;

/**
 * @author Guan
 * @file com.guan.o2o.common
 * @date 2015/9/19
 * @Version 1.0
 */
public class Contant {

    // 文件内存路径
    public static String FILELOCATION = "/storage/sdcard0/files/";

    /**
     * 请求服务器信息
     */
    // 请求服务器出错
    public final static String MSG_SEIVICE_ERROR = "请求服务器出错";

    /*
     * Handler消息处理
     */
    // Handler失败标志
    public final static int FLAG_LOGN_ERROR = 0;
    // Handler成功标志
    public final static int FLAG_LOGN_SUCCESS = 1;

    /**
     * 验证登录信息
     */
    // 手机号码格式错误
    public final static String MSG_PHONE_ERROR = "请重新输入手机号码";
    // 手机号码或验证码格式错误
    public final static String MSG_PHONE_PASS_ERROR = "请重新输入手机号码或验证码";

    /**
     * 时间
     */
    // 延迟3秒
    public static final long SPLASH_DELAY_MS = 2300;
    // Popupwindow显示时间
    public static final long POPWIN_DELAY_MS = 2300;

    /**
     * sharedpreferences保存表名
     */
    // 保存是否第一次登陆客户端
    public static final String SHARED_NAME_FIRST = "first_pref";
    // 保存用户登录信息
    public static final String SHARED_NAME_LOGIN = "login_data";

    /**
     * volley请求标签
     */
    public static final String TAG_STRING_REQUEST = "StringRequest";
    public static final String TAG_JSON_REQUEST = "jsonObjectRequest";
    public static final String TAG_IMAGE_REQUEST = "ImageRequest";

    /*
     *底部tab的选项
     */
    // TAB数目
    public final static int TAB_COUNT = 4;
    // 主页
    public static final int TAB_HOME = 0;
    // 洗衣篮
    public static final int TAB_BASKET = 1;
    // 我
    public static final int TAB_MY = 2;
    // 更多
    public static final int TAB_MORE = 3;

    /**
     * 更多页面的选项
     */
    // 联系客服
    public static final int CV_CUSTOMER = 0;
    // 常见问题
    public static final int CV_PROBLEM = 1;
    // 服务范围
    public static final int CV_SERVICESCOPE = 2;
    // 关于我们
    public static final int CV_ABOUTUS = 3;
    // 用户协议
    public static final int CV_USERAGREE = 4;
    // 意见反馈
    public static final int CV_FEEDBACK = 5;

    /**
     * Intent识别哪个跳转ACTIVITY/FRAGMENT
     */
    // loginActivity
    public final static String VALUE_LOGIN_ACTIVITY = "LoginActivity";
    // moreFragment
    public final static String VALUE_MAIN_ACTIVITY = "MainActivity";

    // KEY值1
    public final static String INTENT_KEY = "KEY";
    // KEY值2
    public final static String INTENT_PARAM = "PARAM";

    /**
     * 更多页面跳转请求码
     */
    public final static int CODE_CUSTOMER = 0000;
    public final static int CODE_PROBLEM = 0001;
    public final static int CODE_SERVICESCOPE = 0003;
    public final static int CODE_ABOUTUS = 0004;
    public final static int CODE_USERAGREE = 0005;
    public final static int CODE_FEEDBACK = 0006;

}
