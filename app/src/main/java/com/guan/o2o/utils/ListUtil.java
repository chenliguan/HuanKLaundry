package com.guan.o2o.utils;

import com.guan.o2o.application.App;

import java.util.Iterator;
import java.util.List;

/**
 * 集合操作工具类
 * @author Guan
 * @file com.guan.o2o.utils
 * @date 2015/10/28
 * @Version 1.0
 */
public class ListUtil {

    /**
     * 删除集合中所有数据
     */
    public static void removeAll(List list) {
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            // 没有，会报异常
            iter.next();
            iter.remove();
        }
    }
}
