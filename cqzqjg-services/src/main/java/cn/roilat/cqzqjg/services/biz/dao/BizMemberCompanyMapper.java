package cn.roilat.cqzqjg.services.biz.dao;

import java.util.List;

import cn.roilat.cqzqjg.services.biz.model.BizMemberCompany;

/**
 * ---------------------------
 * 会员单位信息表 (BizMemberCompanyMapper)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2020-01-01 23:34:40
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public interface BizMemberCompanyMapper {

	/**
	 * 添加会员单位信息表
	 * @param record
	 * @return
	 */
    int add(BizMemberCompany record);

    /**
     * 删除会员单位信息表
     * @param id
     * @return
     */
    int delete(Long id);
    
    /**
     * 修改会员单位信息表
     * @param record
     * @return
     */
    int update(BizMemberCompany record);
    
    /**
     * 根据主键查询
     * @param id
     * @return
     */    
    BizMemberCompany findById(Long id);

    /**
     * 基础分页查询
     * @param record
     * @return
     */    
    List<BizMemberCompany> findPage();
    
}