package cn.roilat.cqzqjg.services.system.sevice;

import java.util.List;

import cn.roilat.cqzqjg.core.service.CurdService;
import cn.roilat.cqzqjg.services.system.model.SysDept;

/**
 * 机构管理
 * @author Louis
 * @date Oct 29, 2018
 */
public interface SysDeptService extends CurdService<SysDept> {

	/**
	 * 查询机构树
	 * @param userId 
	 * @return
	 */
	List<SysDept> findTree();
}
