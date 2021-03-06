package cn.roilat.cqzqjg.wechatsite.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.roilat.cqzqjg.core.http.HttpResult;
import cn.roilat.cqzqjg.core.page.PageResult;
import cn.roilat.cqzqjg.services.biz.service.BizPortalInfoService;
import cn.roilat.cqzqjg.services.biz.vo.BizPortalInfoRespVo;
import cn.roilat.cqzqjg.services.biz.vo.ConsultationVo;
import cn.roilat.cqzqjg.services.biz.vo.HomePageVo;

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
     * 更多资讯列表
     */
    @PostMapping(value = "/consultation")
    public HttpResult consultation(@RequestBody ConsultationVo consultationVo) throws IOException {
        PageResult pageResult = bizPortalInfoService.findNews(consultationVo);
        return HttpResult.ok("请求成功", pageResult);
    }


    /**
     * 资讯详情
     */
    @GetMapping(value = "/getById/{id}")
    public HttpResult consultationById(@PathVariable Long id) throws IOException {
        BizPortalInfoRespVo bizPortalInfoRespVo = bizPortalInfoService.findByIdResp(id);
        return HttpResult.ok("请求成功", bizPortalInfoRespVo);
    }

}
