package com.guan.o2o.utils;

import android.util.Base64;

import com.guan.o2o.model.FileBean;
import com.guan.o2o.model.ServiceNote;
import com.guan.o2o.model.WashOrder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.List;

/**
 * 数据类型转换类
 * @author Guan
 * @file com.guan.o2o.utils
 * @date 2015/10/24
 * @Version 1.0
 */
public class ConvertUtil {

    /**
     * 将集合转化为字符串
     *
     * @param list
     * @return
     */
    public static String listToString(List list)  {
        GsonUtil gsonUtil = new GsonUtil();
        String listString = gsonUtil.GsonString(list);
        return listString;
    }

    /**
     * 将字符串转化为集合
     *
     * @param listString
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List stringToList(String listString) {
        GsonUtil gsonUtil = new GsonUtil();
        List<WashOrder> list = gsonUtil.gsonToList(listString, WashOrder.class);
        return list;
    }

}
