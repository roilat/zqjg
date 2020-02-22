package cn.roilat.cqzqjg.services.system.sevice.impl;

import cn.roilat.cqzqjg.core.page.MybatisPageHelper;
import cn.roilat.cqzqjg.core.page.PageRequest;
import cn.roilat.cqzqjg.core.page.PageResult;
import cn.roilat.cqzqjg.services.system.dao.SysPubMaterialMapper;
import cn.roilat.cqzqjg.services.system.model.SysPubMaterial;
import cn.roilat.cqzqjg.services.system.sevice.SysPubMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: zqjg
 * @description:
 * @author: liujing
 * @create: 2020-02-10 14:50
 **/
@Service
public class SysPubMaterialServiceImpl implements SysPubMaterialService {
    @Autowired
    SysPubMaterialMapper sysPubMaterialMapper;


    @Override
    public int save(SysPubMaterial record) {
        return sysPubMaterialMapper.insert(record);
    }

    @Override
    public int delete(SysPubMaterial record) {
        return sysPubMaterialMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysPubMaterial> records) {
        for (SysPubMaterial record : records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public SysPubMaterial findById(Long id) {
        return sysPubMaterialMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        PageResult pageResult = MybatisPageHelper.findPage(pageRequest, sysPubMaterialMapper);
        return pageResult;
    }

    @Override
    public int update(SysPubMaterial sysPubMaterial) {
        SysPubMaterial sysPubMaterialLocal = sysPubMaterialMapper.selectByMediaId(sysPubMaterial.getMediaId());
        if (null == sysPubMaterialLocal) {
            //为空新增一条数据
            String materialId = String.valueOf(System.currentTimeMillis());
            sysPubMaterial.setMaterialId(materialId);
            sysPubMaterialMapper.insert(sysPubMaterial);
        } else {
            //更新数据
            sysPubMaterialMapper.updateByMaterialId(sysPubMaterial);
        }
        return 1;
    }

    @Override
    public SysPubMaterial selectByMediaId(String mediaId) {
        return sysPubMaterialMapper.selectByMediaId(mediaId);
    }

    @Override
    public int deleteById(Long id) {
        return sysPubMaterialMapper.deleteByPrimaryKey(id);
    }
}
