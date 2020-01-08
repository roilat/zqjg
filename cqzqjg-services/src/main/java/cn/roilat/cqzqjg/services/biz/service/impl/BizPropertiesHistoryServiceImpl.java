package cn.roilat.cqzqjg.services.biz.service.impl;

import cn.roilat.cqzqjg.core.page.MybatisPageHelper;
import cn.roilat.cqzqjg.core.page.PageRequest;
import cn.roilat.cqzqjg.core.page.PageResult;
import cn.roilat.cqzqjg.services.biz.dao.BizPropertiesHistoryMapper;
import cn.roilat.cqzqjg.services.biz.model.BizPropertiesHistory;
import cn.roilat.cqzqjg.services.biz.service.BizPropertiesHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ---------------------------
 * 资产信息历史信息表 (BizPropertiesHistoryServiceImpl)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2020-01-01 23:34:40
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@Service
public class BizPropertiesHistoryServiceImpl implements BizPropertiesHistoryService {

	@Autowired
	private BizPropertiesHistoryMapper bizPropertiesHistoryMapper;

	@Override
	public int save(BizPropertiesHistory record) {
		if(record.getId() == null || record.getId() == 0) {
			return bizPropertiesHistoryMapper.add(record);
		}
		return bizPropertiesHistoryMapper.update(record);
	}

	@Override
	public int delete(BizPropertiesHistory record) {
		return bizPropertiesHistoryMapper.delete(record.getId());
	}

	@Override
	public int delete(List<BizPropertiesHistory> records) {
		for(BizPropertiesHistory record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public BizPropertiesHistory findById(Long id) {
		return bizPropertiesHistoryMapper.findById(id);
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return MybatisPageHelper.findPage(pageRequest, bizPropertiesHistoryMapper);
	}
	
}
