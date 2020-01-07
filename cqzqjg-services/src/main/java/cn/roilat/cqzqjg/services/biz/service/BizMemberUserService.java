package cn.roilat.cqzqjg.services.biz.service;

import cn.roilat.cqzqjg.services.biz.model.BizMemberUser;
import cn.roilat.cqzqjg.core.service.CurdService;
import cn.roilat.cqzqjg.services.biz.vo.BizMemberUserRespVo;

/**
 * --------------------------- 会员单位e用户信息表 (BizMemberUserService)
 * --------------------------- 作者： kitty-generator 时间： 2020-01-01 23:34:40 说明：
 * 我是由代码生成器生生成的 ---------------------------
 */
public interface BizMemberUserService extends CurdService<BizMemberUser> {

	BizMemberUser findByLoginName(String loginName);

	BizMemberUserRespVo findByNameMyPage(String loginName);

	BizMemberUser findByOpenId(String openId);

}
