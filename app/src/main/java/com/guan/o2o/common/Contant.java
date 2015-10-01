package com.guan.o2o.common;

/**
 * @author Guan
 * @file com.guan.o2o.common
 * @date 2015/9/19
 * @Version 1.0
 */
public class Contant {

    // 文件内存路径
    public static String FILELOCATION = "/storage/sdcard0/files/";

    // TAG
    public final static String TAG = "TAG";

    // Handler成功标志
    public final static int FLAG_LOGN_SUCCESS = 1111;

    // Handler失败标志
    public final static int FLAG_LOGN_ERROR = 0000;

    // 第一次登录标志
    public final static int FLAG_LOGN_FIRST = 0001;

    // 请求服务器出错
    public final static String MSG_SEIVICE_ERROR = "请求服务器出错";

    // 延迟3秒
    public static final long SPLASH_DELAY_MILLIS = 3000;

    // 手机号码格式错误
    public final static String MSG_PHONE_ERROR = "请重新输入手机号码";

    // 手机号码或验证码格式错误
    public final static String MSG_PHONE_PASSWORD_ERROR = "请重新输入手机号码或验证码";

    // 保存是否第一次登陆客户端
    public static final String SHAREDPREFERENCES_NAME_FIRST = "first_pref";

    // 保存用户登录信息
    public static final String SHAREDPREFERENCES_NAME_LOGIN = "login_data";

}
