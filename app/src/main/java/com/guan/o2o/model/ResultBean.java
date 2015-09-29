package com.guan.o2o.model;

import com.google.gson.Gson;
import com.guan.o2o.utils.GsonUtil;

/**
 * @author Guan
 * @file com.guan.o2o.model
 * @date 2015/9/20
 * @Version 1.0
 */
public class ResultBean {

    private String result;
    private String msg;

    public ResultBean(String result, String msg) {
        this.result = result;
        this.msg = msg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * Gson解析Json数据
     */
    public static ResultBean praseJson(String reponse) {

        GsonUtil gsonUtil = new GsonUtil();
        ResultBean resultBean = gsonUtil.GsonToBean(reponse, ResultBean.class);
        return resultBean;
    }
}
