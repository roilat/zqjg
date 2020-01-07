package cn.roilat.cqzqjg.wechatsite.controller;

import cn.roilat.cqzqjg.common.util.PasswordUtils;
import cn.roilat.cqzqjg.common.utils.StringUtils;
import cn.roilat.cqzqjg.common.vo.UserBean;
import cn.roilat.cqzqjg.core.http.HttpResult;
import cn.roilat.cqzqjg.services.biz.model.BizMemberUser;
import cn.roilat.cqzqjg.services.biz.service.BizMemberUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

/**
 * @program: zqjg
 * @description: 用户注册
 * @author: liujing
 * @create: 2020-01-07 09:34
 **/
@RestController
@RequestMapping("register")
public class RegisterController {

    private static Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private BizMemberUserService bizMemberUserService;

    @PostMapping(value = "/newUser")
    public HttpResult login(@RequestBody UserBean userBean, HttpServletRequest request) throws IOException {
        logger.info("收到注册用户请求： " + userBean.toString());
        if (StringUtils.isBlank(userBean.getAccount())) {
            return HttpResult.error("用户名不能为空");
        }
        if (StringUtils.isBlank(userBean.getCompanyName())) {
            return HttpResult.error("公司名不能为空");
        }
        if (StringUtils.isBlank(userBean.getPassword())) {
            return HttpResult.error("密码不能为空");
        }
        if (StringUtils.isBlank(userBean.getPhoneNumber())) {
            return HttpResult.error("手机号不能为空");
        }
        BizMemberUser user = bizMemberUserService.findByLoginName(userBean.getAccount());
        if (null != user) {
            return HttpResult.error("登录账号重复");
        }

        //盐
        String salt = PasswordUtils.getSalt();
        //密码加密
        String password = PasswordUtils.encode(userBean.getPassword(), salt);

        BizMemberUser bizMemberUser = new BizMemberUser();
        bizMemberUser.setLoginName(userBean.getAccount());
        bizMemberUser.setCompanyName(userBean.getCompanyName());
        bizMemberUser.setPassword(password);
        bizMemberUser.setSalt(salt);
        bizMemberUser.setCreateTime(new Date());
        //保存用户
        int i = bizMemberUserService.save(bizMemberUser);
        if (i > 0) {
            return HttpResult.ok("注册成功");
        } else {
            return HttpResult.error("注册失败，请重试");
        }
    }
}
