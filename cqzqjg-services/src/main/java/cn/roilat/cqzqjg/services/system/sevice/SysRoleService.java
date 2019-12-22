package cn.roilat.cqzqjg.services.system.sevice;

import java.util.List;

import cn.roilat.cqzqjg.core.service.CurdService;
import cn.roilat.cqzqjg.services.system.model.SysMenu;
import cn.roilat.cqzqjg.services.system.model.SysRole;
import cn.roilat.cqzqjg.services.system.model.SysRoleMenu;

/**
 * 角色管理
 * @author Louis
 * @date Oct 29, 2018
 */
public interface SysRoleService extends CurdService<SysRole> {

	/**
	 * 查询全部
	 * @return
	 */
	List<SysRole> findAll();

	/**
	 * 查询角色菜单集合
	 * @return
	 */
	List<SysMenu> findRoleMenus(Long roleId);

	/**
	 * 保存角色菜单
	 * @param records
	 * @return
	 */
	int saveRoleMenus(List<SysRoleMenu> records);

	/**
	 * 根据名称查询
	 * @param name
	 * @return
	 */
	List<SysRole> findByName(String name);

}
