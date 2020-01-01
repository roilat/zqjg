package cn.roilat.cqzqjg.services.biz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.roilat.cqzqjg.core.page.MybatisPageHelper;
import cn.roilat.cqzqjg.core.page.PageRequest;
import cn.roilat.cqzqjg.core.page.PageResult;

import cn.roilat.cqzqjg.services.biz.model.BizPortalInfo;
import cn.roilat.cqzqjg.services.biz.dao.BizPortalInfoMapper;
import cn.roilat.cqzqjg.services.biz.service.BizPortalInfoService;

/**
 * ---------------------------
 * 首页的显示信息表 (BizPortalInfoServiceImpl)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2020-01-01 23:34:40
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@Service
public class BizPortalInfoServiceImpl implements BizPortalInfoService {

	@Autowired
	private BizPortalInfoMapper bizPortalInfoMapper;

	@Override
	public int save(BizPortalInfo record) {
		if(record.getId() == null || record.getId() == 0) {
			return bizPortalInfoMapper.add(record);
		}
		return bizPortalInfoMapper.update(record);
	}

	@Override
	public int delete(BizPortalInfo record) {
		return bizPortalInfoMapper.delete(record.getId());
	}

	@Override
	public int delete(List<BizPortalInfo> records) {
		for(BizPortalInfo record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public BizPortalInfo findById(Long id) {
		return bizPortalInfoMapper.findById(id);
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return MybatisPageHelper.findPage(pageRequest, bizPortalInfoMapper);
	}
	
}
