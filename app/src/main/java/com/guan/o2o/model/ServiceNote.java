package com.guan.o2o.model;

import com.guan.o2o.utils.GsonUtil;

import java.util.List;

/**
 * @author Guan
 * @file com.guan.o2o.model
 * @date 2015/10/17
 * @Version 1.0
 */
public class ServiceNote {


    /**
     * MeansInfo : [{"ImageMeans":"http://www.heartguard.cn:8080/demo/means1.png","TextMeans":"邀请好友，您和您的好友各得30元洗衣优惠劵礼包；"},{"ImageMeans":"http://www.heartguard.cn:8080/demo/means2.png","TextMeans":"好友对话优惠卷礼包：通过您发出的分享邀请，在邀请页面候总输入手机号兑换：或注册后在App\u201c优惠劵、兑换\u201d页填写您的邀请码兑换。好友必须是新用户且未使用过洗衣服务；"},{"ImageMeans":"http://www.heartguard.cn:8080/demo/means3.png","TextMeans":"好友首次洗衣服付费成功后3天，您将获得30元优惠倦礼包奖励。洗衣付费成功定义好友使用支付宝成功支付订单（使用赠送的优惠劵全额支付的订单除外），支付完成后账户无欠款；"},{"ImageMeans":"http://www.heartguard.cn:8080/demo/means4.png","TextMeans":"您总计课获得不超过价值600元的邀请奖励；"},{"ImageMeans":"http://www.heartguard.cn:8080/demo/means5.png","TextMeans":"非正常途径获得的优惠劵奖励无效，麦子洗衣保留最终解释权；"}]
     */

    public List<MeansInfoEntity> MeansInfo;

    public static class MeansInfoEntity {
        /**
         * ImageMeans : http://www.heartguard.cn:8080/demo/means1.png
         * TextMeans : 邀请好友，您和您的好友各得30元洗衣优惠劵礼包；
         */
        private String ImageMeans;
        private String TextMeans;

        public void setImageMeans(String ImageMeans) {
            this.ImageMeans = ImageMeans;
        }

        public void setTextMeans(String TextMeans) {
            this.TextMeans = TextMeans;
        }

        public String getImageMeans() {
            return ImageMeans;
        }

        public String getTextMeans() {
            return TextMeans;
        }
    }

    /**
     * Gson解析Json数据
     */
    public static ServiceNote praseJson(String reponse) {
        GsonUtil gsonUtil = new GsonUtil();
        ServiceNote serviceNote = gsonUtil.GsonToBean(reponse, ServiceNote.class);
        return serviceNote;
    }
}
