package cn.roilat.cqzqjg.services.biz.service;

import cn.roilat.cqzqjg.core.service.CurdService;
import cn.roilat.cqzqjg.services.biz.model.BizMemberCompany;
import cn.roilat.cqzqjg.services.biz.vo.BizMemberCompanyResp;

import java.util.List;
import java.util.Map;

/**
 * ---------------------------
 * 会员单位信息表 (BizMemberCompanyService)
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2020-01-01 23:34:40
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public interface BizMemberCompanyService extends CurdService<BizMemberCompany> {

    BizMemberCompanyResp findByIdResp(Long id);

    List<BizMemberCompany> findByCondition(Map<String, Object> map);

    Integer update(BizMemberCompany bizMemberCompany);

    Integer deleteById(List<Map<String, Object>> params);
}
