package cn.roilat.cqzqjg.services.wechat.service.impl;

import cn.roilat.cqzqjg.services.wechat.model.MyWxMpMassOpenIdsMessage;
import cn.roilat.cqzqjg.services.wechat.model.WxMpMassTagMessage;
import cn.roilat.cqzqjg.services.wechat.service.MyWxMpMassMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpMassSendResult;
import me.chanjar.weixin.mp.enums.WxMpApiUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: weixin-java-mp-demo-springboot
 * @description:
 * @author: liujing
 **/
@Slf4j
@RequiredArgsConstructor
@Service
public class MyWxMpMassMessageServiceImpl implements MyWxMpMassMessageService {
    @Autowired
    private WxMpService wxMpService;


    @Override
    public WxMpMassSendResult massOpenIdsMessageSend(MyWxMpMassOpenIdsMessage message) throws WxErrorException {
        String responseContent = this.wxMpService.post(WxMpApiUrl.MassMessage.MESSAGE_MASS_SEND_URL, message.toJson());
        return WxMpMassSendResult.fromJson(responseContent);
    }

    @Override
    public WxMpMassSendResult massGroupMessageSend(WxMpMassTagMessage message) throws WxErrorException {
        String responseContent = this.wxMpService.post(WxMpApiUrl.MassMessage.MESSAGE_MASS_SENDALL_URL, message.toJson());
        return WxMpMassSendResult.fromJson(responseContent);
    }
}
