package com.guan.o2o.model;

/**
 * @author Guan
 * @file com.guan.o2o.model
 * @date 2015/10/5
 * @Version 1.0
 */
public class WashOrder {

    private String washCategory;
    private String washNum;
    private String washMoney;

    public WashOrder(String washCategory, String washNum, String washMoney) {
        this.washCategory = washCategory;
        this.washNum = washNum;
        this.washMoney = washMoney;
    }

    public String getWashCategory() {
        return washCategory;
    }

    public void setWashCategory(String washCategory) {
        this.washCategory = washCategory;
    }

    public String getWashNum() {
        return washNum;
    }

    public void setWashNum(String washNum) {
        this.washNum = washNum;
    }

    public String getWashMoney() {
        return washMoney;
    }

    public void setWashMoney(String washMoney) {
        this.washMoney = washMoney;
    }
}
