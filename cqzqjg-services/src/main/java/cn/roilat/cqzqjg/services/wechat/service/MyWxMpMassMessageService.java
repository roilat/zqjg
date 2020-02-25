package cn.roilat.cqzqjg.services.wechat.service;

import cn.roilat.cqzqjg.services.wechat.model.MyWxMpMassOpenIdsMessage;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.result.WxMpMassSendResult;

/**
 * @program: weixin-java-mp-demo-springboot
 * @description:
 * @author: liujing
 **/
public interface MyWxMpMassMessageService {


    /**
     * <pre>
     * 按openId列表群发消息.
     * 详情请见: http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN
     * </pre>
     */
    WxMpMassSendResult massOpenIdsMessageSend(MyWxMpMassOpenIdsMessage message) throws WxErrorException;


}
