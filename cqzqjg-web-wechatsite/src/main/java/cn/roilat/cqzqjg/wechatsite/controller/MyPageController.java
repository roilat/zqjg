package cn.roilat.cqzqjg.wechatsite.controller;

import cn.roilat.cqzqjg.core.http.HttpResult;
import cn.roilat.cqzqjg.services.biz.service.BizMemberCompanyService;
import cn.roilat.cqzqjg.services.biz.service.BizMemberUserService;
import cn.roilat.cqzqjg.services.biz.vo.BizMemberCompanyResp;
import cn.roilat.cqzqjg.services.biz.vo.BizMemberUserRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @program: zqjg
 * @description: 我的页面
 * @author: liujing
 * @create: 2020-01-07 16:41
 **/
@RestController
@RequestMapping("my")
public class MyPageController {

    @Autowired
    private BizMemberUserService bizMemberUserService;
    @Autowired
    private BizMemberCompanyService bizMemberCompanyService;

    @GetMapping(value = "/page/{username}")
    public HttpResult login(@PathVariable String username) throws IOException {
        BizMemberUserRespVo user = bizMemberUserService.findByNameMyPage(username);
        if (null == user) {
            return HttpResult.error("用户不存在");
        }
        return HttpResult.ok("查询成功", user);
    }

    @GetMapping(value = "/company/{id}")
    public HttpResult companyDetail(@PathVariable Long id) throws IOException {
        BizMemberCompanyResp bizMemberCompanyResp = bizMemberCompanyService.findByIdResp(id);
        if (null == bizMemberCompanyResp) {
            return HttpResult.error("用户不存在");
        }
        return HttpResult.ok("查询成功", bizMemberCompanyResp);
    }

    @GetMapping(value = "/removeWechat/{id}")
    public HttpResult removeWechat(@PathVariable Long id) throws IOException {
        Boolean result = bizMemberUserService.removeWechat(id);
        if (result) {
            return HttpResult.ok("解绑成功");
        } else {
            return HttpResult.ok("解绑失败");
        }
    }

}
