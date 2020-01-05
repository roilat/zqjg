package cn.roilat.cqzqjg.wechatsite.controller;

import cn.roilat.cqzqjg.core.http.HttpResult;
import cn.roilat.cqzqjg.services.biz.model.BizProperties;
import cn.roilat.cqzqjg.services.biz.service.BizPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @program: zqjg
 * @description: 资产页面
 * @author: liujing
 * @create: 2020-01-05 00:24
 **/
@RestController
@RequestMapping("assets")
public class AssetsPageController {

    @Autowired
    BizPropertiesService bizPropertiesService;

    /**
     * 资产接口
     */
    @GetMapping(value = "/getPage")
    public HttpResult homePage(@RequestParam(required = false) String name, HttpServletResponse response, HttpServletRequest request) throws IOException {
        System.out.println("hello");
        List<BizProperties> bizPropertiesList = bizPropertiesService.findByName(name);
        return HttpResult.ok("请求成功", bizPropertiesList);
    }


}
