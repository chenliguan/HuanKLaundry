package com.guan.o2o.model;

/**
 * @author Guan
 * @file com.guan.o2o.model
 * @date 2015/10/5
 * @Version 1.0
 */
public class WashOrder {

    private String washCategory;
    private int washNum;
    private String washPrice;

    public WashOrder(String washCategory, int washNum, String washPrice) {
        this.washCategory = washCategory;
        this.washNum = washNum;
        this.washPrice = washPrice;
    }

    public String getWashCategory() {
        return washCategory;
    }

    public void setWashCategory(String washCategory) {
        this.washCategory = washCategory;
    }

    public int getWashNum() {
        return washNum;
    }

    public void setWashNum(int washNum) {
        this.washNum = washNum;
    }

    public String getWashPrice() {
        return washPrice;
    }

    public void setWashPrice(String washPrice) {
        this.washPrice = washPrice;
    }
}
