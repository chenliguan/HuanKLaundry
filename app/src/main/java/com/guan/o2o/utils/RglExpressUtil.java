package com.guan.o2o.utils;

import android.text.TextUtils;

/**
 * @author Guan
 * @file com.guan.o2o.utils
 * @date 2015/9/19
 * @Version 1.0
 */
public class RglExpressUtil {

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {

        /*
         *移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
         *联通：130、131、132、152、155、156、185、186
         *电信：133、153、180、189、（1349卫通）
         *总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
         */

        /*
         *"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个,
         *"\\d{9}"代表后面是可以是0～9的数字，有9位
         */
        String telRegex = "[1][358]\\d{9}";
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }

    /**
     * 判断密码字符串是否含有中文字
     *
     * @param string
     * @return boolean
     */
    public static boolean isChineseNo(String string) {
        boolean is = false;
        // 返回字符串的字节长度,一个中文两个字节
        int bytesLength = string.getBytes().length;
        // 返回字符串的字符个数,一个中文算一个字符
        int sLength = string.length();
        int hasNum = bytesLength - sLength;
        if (hasNum == 0) {
            is = false;
        } else if (hasNum > 0) {
            is = true;
        }
        return is;
    }

    /**
     * 以及是否为空
     *
     * @param string
     * @return
     */
    public static boolean isNullNo(String string) {
        boolean is = false;
        if (string == null) {
            is = true;
        } else
            is = false;
        return is;
    }
}
