package cn.roilat.cqzqjg.services.biz.service.impl;

import java.util.List;

import cn.roilat.cqzqjg.services.biz.vo.AssetsReqVo;
import cn.roilat.cqzqjg.services.biz.vo.HomePageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.roilat.cqzqjg.core.page.MybatisPageHelper;
import cn.roilat.cqzqjg.core.page.PageRequest;
import cn.roilat.cqzqjg.core.page.PageResult;

import cn.roilat.cqzqjg.services.biz.model.BizProperties;
import cn.roilat.cqzqjg.services.biz.dao.BizPropertiesMapper;
import cn.roilat.cqzqjg.services.biz.service.BizPropertiesService;

/**
 * ---------------------------
 * 资产信息表 (BizPropertiesServiceImpl)
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2020-01-01 23:34:40
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@Service
public class BizPropertiesServiceImpl implements BizPropertiesService {

    @Autowired
    private BizPropertiesMapper bizPropertiesMapper;

    @Override
    public int save(BizProperties record) {
        if (record.getId() == null || record.getId() == 0) {
            return bizPropertiesMapper.add(record);
        }
        return bizPropertiesMapper.update(record);
    }

    @Override
    public int delete(BizProperties record) {
        return bizPropertiesMapper.delete(record.getId());
    }

    @Override
    public int delete(List<BizProperties> records) {
        for (BizProperties record : records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public BizProperties findById(Long id) {
        return bizPropertiesMapper.findById(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, bizPropertiesMapper);
    }

    @Override
    public PageResult findByName(AssetsReqVo assetsReqVo) {
        return MybatisPageHelper.findPage(assetsReqVo, bizPropertiesMapper, "findByName", assetsReqVo.getName());
    }

}
