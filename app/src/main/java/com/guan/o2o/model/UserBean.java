package com.guan.o2o.model;

import com.google.gson.Gson;
import com.guan.o2o.utils.GsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Guan
 * @file com.guan.o2o.model
 * @date 2015/9/20
 * @Version 1.0
 */
public class UserBean {

    /**
     * registerPhone : 13751338740
     * registerCode : 13751338740
     * interesting : ["gprivate Stringame","music","sports"]
     */
    private String registerPhone;
    private String registerCode;
    private List<String> interesting;

    public UserBean(String registerPhone, String registerCode, List<String> interesting) {
        this.registerPhone = registerPhone;
        this.registerCode = registerCode;
        this.interesting = interesting;
    }

    public void setRegisterPhone(String registerPhone) {
        this.registerPhone = registerPhone;
    }

    public void setRegisterCode(String registerCode) {
        this.registerCode = registerCode;
    }

    public void setInteresting(List<String> interesting) {
        this.interesting = interesting;
    }

    public String getRegisterPhone() {
        return registerPhone;
    }

    public String getRegisterCode() {
        return registerCode;
    }

    public List<String> getInteresting() {
        return interesting;
    }

    /**
     * Gson封装json数据
     */
    public static String toJson(String registerPhone, String registerCode) {

        List<String> list = new ArrayList<String>();
        list.add("game");
        list.add("music");
        list.add("sports");

        /**
         * 封装的是json对象
         */
        UserBean userBean = new UserBean(registerPhone, registerCode, list);
        /**
         * 封装的是json数组
         */
        List<UserBean> userBeanList = new ArrayList<UserBean>();
        userBeanList.add(userBean);

        GsonUtil gsonUtil = new GsonUtil();
        String gsonString = gsonUtil.GsonString(userBean);

        return gsonString;
    }

    /**
     * Gson解析Json数据
     */
    public void praseJson() {

    }
}
