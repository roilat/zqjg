package cn.roilat.cqzqjg.admin.pub.controller;

import cn.roilat.cqzqjg.services.wechat.model.MyWxMpMassOpenIdsMessage;
import cn.roilat.cqzqjg.services.wechat.service.MyWxMpMassMessageService;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpMassSendResult;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: weixin-java-mp-demo-springboot
 * @description: 微信消息
 * @author: liujing
 **/
@RestController
@RequestMapping("/wx/message")
public class MessageController {
    private final WxMpService wxMpService;
    private final MyWxMpMassMessageService myWxMpMassMessageService;

    public MessageController(WxMpService wxMpService, MyWxMpMassMessageService myWxMpMassMessageService) {
        this.wxMpService = wxMpService;
        this.myWxMpMassMessageService = myWxMpMassMessageService;
    }


    private static Logger logger = LoggerFactory.getLogger(WxUserInfoController.class);


    @PostMapping("/send")
    public void sendText(@RequestBody MyWxMpMassOpenIdsMessage myWxMpMassOpenIdsMessage) throws WxErrorException {
        WxMpUserList wxMpUserList = wxMpService.getUserService().userList(null);
        //获取用户列表
        List<String> openIds = wxMpUserList.getOpenids();
        // 发送群发消息
        myWxMpMassOpenIdsMessage.setTouser(openIds);
        //发送
        WxMpMassSendResult massResult = myWxMpMassMessageService.massOpenIdsMessageSend(myWxMpMassOpenIdsMessage);
        logger.debug("massResult: " + massResult.toString());
    }

}
