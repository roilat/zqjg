package cn.roilat.cqzqjg.services.system.sevice;

import java.util.List;
import java.util.Set;

import cn.roilat.cqzqjg.core.service.CurdService;
import cn.roilat.cqzqjg.services.system.model.SysUser;
import cn.roilat.cqzqjg.services.system.model.SysUserRole;

/**
 * 用户管理
 * @author Louis
 * @date Oct 29, 2018
 */
public interface SysUserService extends CurdService<SysUser> {

	SysUser findByName(String username);

	/**
	 * 查找用户的菜单权限标识集合
	 * @param userName
	 * @return
	 */
	Set<String> findPermissions(String userName);

	/**
	 * 查找用户的角色集合
	 * @param userName
	 * @return
	 */
	List<SysUserRole> findUserRoles(Long userId);

}
