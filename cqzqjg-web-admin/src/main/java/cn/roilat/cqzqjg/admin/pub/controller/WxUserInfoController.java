package cn.roilat.cqzqjg.admin.pub.controller;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpUserService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: weixin-java-mp-demo-springboot
 * @description: 微信用户信息
 * @author: liujing
 **/
@RestController
@RequestMapping("/wx/public/user")
public class WxUserInfoController {

    private final WxMpUserService wxMpUserService;

    public WxUserInfoController(WxMpUserService wxMpUserService) {
        this.wxMpUserService = wxMpUserService;
    }

    private static Logger logger = LoggerFactory.getLogger(WxUserInfoController.class);

    private List<String> openIds;

    @GetMapping("/getUsersInfo")
    public void getUserInfos() throws WxErrorException {
        WxMpUserList wxMpUserList = wxMpUserService.userList(null);
        openIds = wxMpUserList.getOpenids();
        for (String openIds : wxMpUserList.getOpenids()) {
            logger.info("用户微信openId: " + openIds.replace("[", "").replace("]", ""));
        }
    }

    @GetMapping("/getUserInfo")
    public void getUserInfo() throws WxErrorException {
        String openId = openIds.get(0);
        WxMpUser wxMpUser = wxMpUserService.userInfo(openId);
        logger.info("用户详细信息: " + wxMpUser.toString());
        List<WxMpUser> list = wxMpUserService.userInfoList(openIds);
        for (WxMpUser wxMpUser1 : list) {
            logger.info("WxMpUser: " + wxMpUser1.toString());
        }
    }

}
