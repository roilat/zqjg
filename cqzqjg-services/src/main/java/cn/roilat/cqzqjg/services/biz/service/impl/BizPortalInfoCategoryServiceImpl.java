package cn.roilat.cqzqjg.services.biz.service.impl;

import cn.roilat.cqzqjg.core.page.MybatisPageHelper;
import cn.roilat.cqzqjg.core.page.PageRequest;
import cn.roilat.cqzqjg.core.page.PageResult;
import cn.roilat.cqzqjg.services.biz.dao.BizPortalInfoCategoryMapper;
import cn.roilat.cqzqjg.services.biz.model.BizPortalInfoCategory;
import cn.roilat.cqzqjg.services.biz.service.BizPortalInfoCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ---------------------------
 * 首页信息分类表 (BizPortalInfoCategoryServiceImpl)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2020-01-01 23:34:40
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@Service
public class BizPortalInfoCategoryServiceImpl implements BizPortalInfoCategoryService {

	@Autowired
	private BizPortalInfoCategoryMapper bizPortalInfoCategoryMapper;

	@Override
	public int save(BizPortalInfoCategory record) {
		if(record.getTypeCode() == null || record.getTypeCode() == "") {
			return bizPortalInfoCategoryMapper.add(record);
		}
		return bizPortalInfoCategoryMapper.update(record);
	}

	@Override
	public int delete(BizPortalInfoCategory record) {
		return bizPortalInfoCategoryMapper.delete(record.getTypeCode());
	}

	@Override
	public int delete(List<BizPortalInfoCategory> records) {
		for(BizPortalInfoCategory record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public BizPortalInfoCategory findById(String id) {
		return bizPortalInfoCategoryMapper.findById(id);
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return MybatisPageHelper.findPage(pageRequest, bizPortalInfoCategoryMapper);
	}

	@Override
	public BizPortalInfoCategory findById(Long id) {
		return null;
	}
	
}
