package cn.roilat.cqzqjg.services.system.sevice;

import java.util.List;

import cn.roilat.cqzqjg.core.service.CurdService;
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
}
