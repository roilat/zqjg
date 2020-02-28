package cn.roilat.cqzqjg.admin.pub.controller;

import cn.roilat.cqzqjg.common.utils.StringUtils;
import cn.roilat.cqzqjg.core.http.HttpResult;
import cn.roilat.cqzqjg.services.system.model.SysPubMaterial;
import cn.roilat.cqzqjg.services.system.sevice.SysPubMaterialService;
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

import java.util.Date;
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

    /**
     * 已发送
     */
    private static final Integer SEND_FLAG_TRUE = 1;

    private final WxMpService wxMpService;
    private final MyWxMpMassMessageService myWxMpMassMessageService;
    private final SysPubMaterialService sysPubMaterialService;

    public MessageController(WxMpService wxMpService, MyWxMpMassMessageService myWxMpMassMessageService, SysPubMaterialService sysPubMaterialService) {
        this.wxMpService = wxMpService;
        this.myWxMpMassMessageService = myWxMpMassMessageService;
        this.sysPubMaterialService = sysPubMaterialService;
    }


    private static Logger logger = LoggerFactory.getLogger(WxUserInfoController.class);


    /**
     * openId 发送消息 （没用上，待删除）
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
    public HttpResult sendText(@RequestBody WxMpMassTagMessage wxMpMassTagMessage) {
        logger.info("收到消息发送参数: " + wxMpMassTagMessage.toJson());
        Map<String, String> mpnews = wxMpMassTagMessage.getMpnews();
        String mediaId = mpnews.get("media_id");
        if (StringUtils.isBlank(mediaId)) {
            return HttpResult.error("请传入图文消息腾讯id");
        }
        wxMpMassTagMessage.setSend_ignore_reprint(0);
        Map<String, Object> filterMap = wxMpMassTagMessage.getFilter();
        filterMap.put("is_to_all", true);
        wxMpMassTagMessage.setFilter(filterMap);
        logger.info(wxMpMassTagMessage.toJson());
        //发送
        WxMpMassSendResult massResult = null;
        try {
            massResult = myWxMpMassMessageService.massGroupMessageSend(wxMpMassTagMessage);
        } catch (WxErrorException e) {
            logger.info("腾讯发送错误: " + e.getMessage());
            return HttpResult.error("腾讯发送错误: " + e.getMessage());
        }
        logger.info("腾讯发送结果: " + massResult.toString());
        //参考腾讯文档，发送成功ErrorCode返回0
        if (!StringUtils.isBlank(massResult.getErrorCode())) {
            //发送成功
            SysPubMaterial sysPubMaterial = sysPubMaterialService.selectByMediaId(mediaId);
            sysPubMaterial.setSendFlag(SEND_FLAG_TRUE);
            sysPubMaterial.setLastUpdateTime(new Date());
            sysPubMaterialService.update(sysPubMaterial);
        }
        return HttpResult.ok("腾讯发送结果: " + massResult.toString());
    }


}
