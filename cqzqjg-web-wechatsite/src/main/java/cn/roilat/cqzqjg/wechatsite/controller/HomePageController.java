package cn.roilat.cqzqjg.wechatsite.controller;

import cn.roilat.cqzqjg.core.http.HttpResult;
import cn.roilat.cqzqjg.services.biz.service.BizPortalInfoService;
import cn.roilat.cqzqjg.services.biz.vo.HomePageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: zqjg
 * @description: 首页controller
 * @author: liujing
 * @create: 2020-01-04 00:10
 **/
@RestController
@RequestMapping("home")
public class HomePageController {

    @Autowired
    BizPortalInfoService bizPortalInfoService;

    /**
     * 首页接口
     */
    @GetMapping(value = "/getPage")
    public HttpResult homePage(HttpServletResponse response, HttpServletRequest request) throws IOException {
        HomePageVo homePageVo = bizPortalInfoService.findHomePage();
        return HttpResult.ok("请求成功", homePageVo);
    }

    /**
     * 更多资讯
     */
    @GetMapping(value = "/consultation")
    public HttpResult consultation(@RequestParam(required = false) String begTime, @RequestParam(required = false) String endTime, HttpServletResponse response, HttpServletRequest request) throws IOException {
        HomePageVo homePageVo = bizPortalInfoService.findNews(begTime,endTime);
        return HttpResult.ok("请求成功", homePageVo);
    }


}
