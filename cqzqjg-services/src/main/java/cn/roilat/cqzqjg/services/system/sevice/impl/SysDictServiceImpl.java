package cn.roilat.cqzqjg.services.system.sevice.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.roilat.cqzqjg.services.biz.vo.BizMemberReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.roilat.cqzqjg.services.system.dao.SysDictMapper;
import cn.roilat.cqzqjg.services.system.model.SysDict;
import cn.roilat.cqzqjg.services.system.sevice.SysDictService;
import cn.roilat.cqzqjg.core.page.ColumnFilter;
import cn.roilat.cqzqjg.core.page.MybatisPageHelper;
import cn.roilat.cqzqjg.core.page.PageRequest;
import cn.roilat.cqzqjg.core.page.PageResult;

@Service
public class SysDictServiceImpl implements SysDictService {

    @Autowired
    private SysDictMapper sysDictMapper;

    @Override
    public int save(SysDict record) {
        if (record.getId() == null || record.getId() == 0) {
            return sysDictMapper.insertSelective(record);
        }
        return sysDictMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delete(SysDict record) {
        return sysDictMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysDict> records) {
        for (SysDict record : records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public SysDict findById(Long id) {
        return sysDictMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        ColumnFilter columnFilter = pageRequest.getColumnFilter("label");
        if (columnFilter != null) {
            return MybatisPageHelper.findPage(pageRequest, sysDictMapper, "findPageByLabel", columnFilter.getValue());
        }
        return MybatisPageHelper.findPage(pageRequest, sysDictMapper);
    }

    @Override
    public List<SysDict> findByLable(String lable) {
        return sysDictMapper.findByLable(lable);
    }

    @Override
    public List<Map<String, String>> findCompanyType() {
        List<Map<String, String>> respList = new ArrayList<>();
        List<SysDict> list = sysDictMapper.findCompanyType();
        if (null != list && list.size() > 0) {
            for (SysDict sysDict : list) {
                Map<String, String> map = new HashMap<>();
                map.put("value", sysDict.getValue());
                map.put("label", sysDict.getLabel());
                respList.add(map);
            }
        }
        return respList;
    }

}
