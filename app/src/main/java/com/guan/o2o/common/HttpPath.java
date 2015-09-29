package com.guan.o2o.common;

public class HttpPath {

    // 请求网路地址
    public final static String IP = "http://172.28.89.98:8080";

    // login
    public static String getLoginIfo(String loginPhone, String loginCode) {
        return IP + "/demo/myClive?phone=13800138000";
    }

    // getCode
    public static String getCodeIfo(String loginPhone) {
        return IP + "/demo/myClive?phone=13800138000";
    }
}
