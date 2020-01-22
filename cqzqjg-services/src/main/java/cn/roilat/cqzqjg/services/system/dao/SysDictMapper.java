package cn.roilat.cqzqjg.services.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.roilat.cqzqjg.services.system.model.SysDict;

public interface SysDictMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysDict record);

    int insertSelective(SysDict record);

    SysDict selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysDict record);

    int updateByPrimaryKey(SysDict record);
    
    List<SysDict> findPage();
    
    List<SysDict> findPageByLabel(@Param(value="label") String label);

    List<SysDict> findByLable(@Param(value="label") String label);

    List<SysDict> findCompanyType();

	/**
	 * @param type
	 * @return
	 */
	List<SysDict> findByType(@Param(value="type")String type);

}