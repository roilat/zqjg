package cn.roilat.cqzqjg.services.biz.service.impl;

import cn.roilat.cqzqjg.core.page.MybatisPageHelper;
import cn.roilat.cqzqjg.core.page.PageRequest;
import cn.roilat.cqzqjg.core.page.PageResult;
import cn.roilat.cqzqjg.services.biz.dao.BizMemberCompanyMapper;
import cn.roilat.cqzqjg.services.biz.model.BizMemberCompany;
import cn.roilat.cqzqjg.services.biz.service.BizMemberCompanyService;
import cn.roilat.cqzqjg.services.biz.vo.BizMemberCompanyResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return MybatisPageHelper.findPage(pageRequest, bizMemberCompanyMapper);
    }

    @Override
    public BizMemberCompanyResp findByIdResp(Long id) {
        BizMemberCompany bizMemberCompany = findById(id);
        if (null == bizMemberCompany) {
            return new BizMemberCompanyResp();
        }
        return castVo(bizMemberCompany);
    }

    private BizMemberCompanyResp castVo(BizMemberCompany bizMemberCompany) {
        BizMemberCompanyResp bizMemberUserRespVo = new BizMemberCompanyResp();
        bizMemberUserRespVo.setId(bizMemberCompany.getId());
        bizMemberUserRespVo.setCompanyAddress(bizMemberCompany.getCompanyAddress());
        bizMemberUserRespVo.setCompanyCode(bizMemberCompany.getCompanyCode());
        bizMemberUserRespVo.setCompanyEmail(bizMemberCompany.getCompanyEmail());
        bizMemberUserRespVo.setCompanyFax(bizMemberCompany.getCompanyFax());
        bizMemberUserRespVo.setCompanyName(bizMemberCompany.getCompanyName());
        bizMemberUserRespVo.setLegalPerson(bizMemberCompany.getLegalPerson());
        bizMemberUserRespVo.setCompanyPhone(bizMemberCompany.getCompanyPhone());
        return bizMemberUserRespVo;
    }
}
