package cn.roilat.cqzqjg.services.system.sevice;

import java.util.List;
import java.util.Map;

import cn.roilat.cqzqjg.core.service.CurdService;
import cn.roilat.cqzqjg.services.biz.vo.BizMemberReqVo;
import cn.roilat.cqzqjg.services.system.model.SysDict;

/**
 * 字典管理
 * @author Louis
 * @date Oct 29, 2018
 */
public interface SysDictService extends CurdService<SysDict> {

	/**
	 * 根据名称查询
	 * @param lable
	 * @return
	 */
	List<SysDict> findByLable(String lable);


	/**
	 * 查询会员类型
	 * @return
	 */
	List<Map<String, String>> findCompanyType();
}
