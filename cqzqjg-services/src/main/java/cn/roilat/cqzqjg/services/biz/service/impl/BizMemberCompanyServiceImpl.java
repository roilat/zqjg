package cn.roilat.cqzqjg.services.biz.service.impl;

import cn.roilat.cqzqjg.core.page.MybatisPageHelper;
import cn.roilat.cqzqjg.core.page.PageRequest;
import cn.roilat.cqzqjg.core.page.PageResult;
import cn.roilat.cqzqjg.services.biz.dao.BizMemberCompanyMapper;
import cn.roilat.cqzqjg.services.biz.model.BizMemberCompany;
import cn.roilat.cqzqjg.services.biz.service.BizMemberCompanyService;
import cn.roilat.cqzqjg.services.biz.vo.BizMemberCompanyResp;
import cn.roilat.cqzqjg.services.biz.vo.BizMemberUserRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ---------------------------
 * 会员单位信息表 (BizMemberCompanyServiceImpl)
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2020-01-01 23:34:40
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@Service
public class BizMemberCompanyServiceImpl implements BizMemberCompanyService {

    @Autowired
    private BizMemberCompanyMapper bizMemberCompanyMapper;

    @Override
    public int save(BizMemberCompany record) {
        if (record.getId() == null || record.getId() == 0) {
            return bizMemberCompanyMapper.add(record);
        }
        return bizMemberCompanyMapper.update(record);
    }

    @Override
    public int delete(BizMemberCompany record) {
        return bizMemberCompanyMapper.delete(record.getId());
    }

    @Override
    public int delete(List<BizMemberCompany> records) {
        for (BizMemberCompany record : records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public BizMemberCompany findById(Long id) {
        return bizMemberCompanyMapper.findById(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        PageResult pageResult = MybatisPageHelper.findPage(pageRequest, bizMemberCompanyMapper);
        List<BizMemberCompany> list = (List<BizMemberCompany>) pageResult.getContent();
        List<BizMemberCompanyResp> respList = new ArrayList<>();
        for (BizMemberCompany bizMemberCompany : list) {
            BizMemberCompanyResp bizMemberCompanyResp = castVo(bizMemberCompany);
            respList.add(bizMemberCompanyResp);
        }
        pageResult.setContent(respList);
        return pageResult;
    }

    @Override
    public BizMemberCompanyResp findByIdResp(Long id) {
        BizMemberCompany bizMemberCompany = findById(id);
        if (null == bizMemberCompany) {
            return new BizMemberCompanyResp();
        }
        return castVo(bizMemberCompany);
    }

    @Override
    public List<BizMemberCompany> findByCondition(Map<String, Object> map) {
        return bizMemberCompanyMapper.findPageByNameAndCode(map);
    }

    @Override
    public Integer update(BizMemberCompany bizMemberCompany) {
        return bizMemberCompanyMapper.update(bizMemberCompany);
    }

    @Override
    public Integer deleteById(List<Map<String, Object>> params) {
        for (Map<String, Object> map : params) {
            bizMemberCompanyMapper.deleteById(map);
        }
        return 1;
    }

    private BizMemberCompanyResp castVo(BizMemberCompany bizMemberCompany) {
        BizMemberCompanyResp bizMemberCompanyResp = new BizMemberCompanyResp();
        bizMemberCompanyResp.setId(bizMemberCompany.getId());
        bizMemberCompanyResp.setCompanyAddress(bizMemberCompany.getCompanyAddress());
        bizMemberCompanyResp.setCompanyCode(bizMemberCompany.getCompanyCode());
        bizMemberCompanyResp.setCompanyEmail(bizMemberCompany.getCompanyEmail());
        bizMemberCompanyResp.setCompanyFax(bizMemberCompany.getCompanyFax());
        bizMemberCompanyResp.setCompanyName(bizMemberCompany.getCompanyName());
        bizMemberCompanyResp.setLegalPerson(bizMemberCompany.getLegalPerson());
        bizMemberCompanyResp.setCompanyPhone(bizMemberCompany.getCompanyPhone());
        Date lastUpTime = bizMemberCompany.getLastUpdateTime();
        if (null != lastUpTime) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String lastUpTimeStr = simpleDateFormat.format(lastUpTime);
            bizMemberCompanyResp.setLastUpdateTime(lastUpTimeStr);
        }
        Date createTime = bizMemberCompany.getCreateTime();
        if (null != lastUpTime) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String createTimeStr = simpleDateFormat.format(createTime);
            bizMemberCompanyResp.setCreateTime(createTimeStr);
        }

        return bizMemberCompanyResp;
    }
}
