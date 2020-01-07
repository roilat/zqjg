package cn.roilat.cqzqjg.wechatsite.controller;

import cn.roilat.cqzqjg.core.http.HttpResult;
import cn.roilat.cqzqjg.core.page.PageResult;
import cn.roilat.cqzqjg.services.biz.service.BizPropertiesService;
import cn.roilat.cqzqjg.services.biz.vo.AssetsReqVo;
import cn.roilat.cqzqjg.services.biz.vo.BizPropertiesRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
     * 资产接口列表
     */
    @PostMapping(value = {"/getPage"})
    public HttpResult assets(@RequestBody AssetsReqVo assetsReqVo) throws IOException {
        PageResult pageResult = bizPropertiesService.findByName(assetsReqVo);
        return HttpResult.ok("请求成功", pageResult);
    }

    /**
     * 资产详情接口
     */
    @GetMapping(value = {"/getById/{id}"})
    public HttpResult assetsById(@PathVariable Long id) throws IOException {
        BizPropertiesRespVo bizPropertiesRespVo = bizPropertiesService.findDetailById(id);
        return HttpResult.ok("请求成功", bizPropertiesRespVo);
    }


}
