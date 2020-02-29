package cn.roilat.cqzqjg.admin.pub.controller;

import cn.roilat.cqzqjg.core.http.HttpResult;
import cn.roilat.cqzqjg.services.wechat.model.MyWxMpMassOpenIdsMessage;
import cn.roilat.cqzqjg.services.wechat.model.WxMpMassTagMessage;
import cn.roilat.cqzqjg.services.wechat.service.MyWxMpMassMessageService;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpMassSendResult;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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


    /**
     * openId 发送消息
     *
     * @param myWxMpMassOpenIdsMessage
     * @throws WxErrorException
     */
    @PostMapping("/send")
    public HttpResult sendTextOpenId(@RequestBody MyWxMpMassOpenIdsMessage myWxMpMassOpenIdsMessage) throws WxErrorException {
        WxMpUserList wxMpUserList = wxMpService.getUserService().userList(null);
        logger.info("收到消息发送参数: " + myWxMpMassOpenIdsMessage.toJson());
        //获取用户列表
        List<String> openIds = wxMpUserList.getOpenids();
        // 发送群发消息
        myWxMpMassOpenIdsMessage.setTouser(openIds);
        //发送
        WxMpMassSendResult massResult = myWxMpMassMessageService.massOpenIdsMessageSend(myWxMpMassOpenIdsMessage);
        logger.debug("腾讯发送结果: " + massResult.toString());
        return HttpResult.ok("腾讯发送结果: " + massResult.toString());
    }


    /**
     * 全部发送
     *
     * @param wxMpMassTagMessage
     * @throws WxErrorException
     */
    @PostMapping("/sendTag")
    public HttpResult sendText(@RequestBody WxMpMassTagMessage wxMpMassTagMessage) throws WxErrorException {
        logger.info("收到消息发送参数: " + wxMpMassTagMessage.toJson());
        wxMpMassTagMessage.setSend_ignore_reprint(0);
        Map<String, Boolean> filterMap = wxMpMassTagMessage.getFilter();
        filterMap.put("is_to_all", true);
        wxMpMassTagMessage.setFilter(filterMap);
        logger.info(wxMpMassTagMessage.toJson());
        //发送
        WxMpMassSendResult massResult = myWxMpMassMessageService.massGroupMessageSend(wxMpMassTagMessage);
        logger.info("腾讯发送结果: " + massResult.toString());
        return HttpResult.ok("腾讯发送结果: " + massResult.toString());
    }


}
