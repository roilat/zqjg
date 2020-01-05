package cn.roilat.cqzqjg.services.biz.service;

import cn.roilat.cqzqjg.core.service.CurdService;
import cn.roilat.cqzqjg.services.biz.model.BizPortalInfo;
import cn.roilat.cqzqjg.services.biz.vo.HomePageVo;
import cn.roilat.cqzqjg.services.biz.vo.News;

import java.util.List;

/**
 * ---------------------------
 * 首页的显示信息表 (BizPortalInfoService)
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2020-01-01 23:34:40
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public interface BizPortalInfoService extends CurdService<BizPortalInfo> {
    /**
     * 查找首页数据
     *
     * @return
     */
    HomePageVo findHomePage();

    /**
     * 更多资讯
     *
     * @return
     */
    HomePageVo findNews(String begTime,String endTime);
}
