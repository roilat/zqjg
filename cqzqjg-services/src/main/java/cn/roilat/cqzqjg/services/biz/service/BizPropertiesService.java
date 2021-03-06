package cn.roilat.cqzqjg.services.biz.service;

import cn.roilat.cqzqjg.core.page.PageResult;
import cn.roilat.cqzqjg.core.service.CurdService;
import cn.roilat.cqzqjg.services.biz.model.BizProperties;
import cn.roilat.cqzqjg.services.biz.vo.AssetsReqVo;
import cn.roilat.cqzqjg.services.biz.vo.BizPropertiesReqVo;
import cn.roilat.cqzqjg.services.biz.vo.BizPropertiesRespVo;

/**
 * ---------------------------
 * 资产信息表 (BizPropertiesService)
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2020-01-01 23:34:40
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public interface BizPropertiesService extends CurdService<BizProperties> {


    /**
     * 资产列表
     *
     * @return
     */
    PageResult findByName(AssetsReqVo assetsReqVo);


    /**
     * 资产列表
     *
     * @return
     */
    PageResult findByNamePc(BizPropertiesReqVo bizPropertiesReqVo);

    /**
     * 资产详情
     *
     * @return
     */
    BizPropertiesRespVo findDetailById(Long id);

    /**
     * 资产详情
     * @param id 资产id
     * @return
     */
    BizPropertiesRespVo findDetailByIdPc(Long id);

}
