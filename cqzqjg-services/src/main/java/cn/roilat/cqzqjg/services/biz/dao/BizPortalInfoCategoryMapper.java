package cn.roilat.cqzqjg.services.biz.dao;

import java.util.List;

import cn.roilat.cqzqjg.services.biz.model.BizPortalInfoCategory;

/**
 * ---------------------------
 * 首页信息分类表 (BizPortalInfoCategoryMapper)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2020-01-01 23:34:40
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public interface BizPortalInfoCategoryMapper {

	/**
	 * 添加首页信息分类表
	 * @param record
	 * @return
	 */
    int add(BizPortalInfoCategory record);

    /**
     * 删除首页信息分类表
     * @param typeCode
     * @return
     */
    int delete(String typeCode);
    
    /**
     * 修改首页信息分类表
     * @param record
     * @return
     */
    int update(BizPortalInfoCategory record);
    
    /**
     * 根据主键查询
     * @param typeCode
     * @return
     */    
    BizPortalInfoCategory findById(String typeCode);

    /**
     * 基础分页查询
     * @param record
     * @return
     */    
    List<BizPortalInfoCategory> findPage();
    
}