package cn.roilat.cqzqjg.services.biz.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.roilat.cqzqjg.services.biz.vo.BizMemberUserRespVo;
import cn.roilat.cqzqjg.services.biz.vo.BizPortalInfoRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.roilat.cqzqjg.core.page.MybatisPageHelper;
import cn.roilat.cqzqjg.core.page.PageRequest;
import cn.roilat.cqzqjg.core.page.PageResult;

import cn.roilat.cqzqjg.services.biz.model.BizMemberUser;
import cn.roilat.cqzqjg.services.biz.dao.BizMemberUserMapper;
import cn.roilat.cqzqjg.services.biz.service.BizMemberUserService;

/**
 * ---------------------------
 * 会员单位e用户信息表 (BizMemberUserServiceImpl)
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2020-01-01 23:34:40
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@Service
public class BizMemberUserServiceImpl implements BizMemberUserService {

    @Autowired
    private BizMemberUserMapper bizMemberUserMapper;

    @Override
    public int save(BizMemberUser record) {
        if (record.getId() == null || record.getId() == 0) {
            return bizMemberUserMapper.add(record);
        }
        return bizMemberUserMapper.update(record);
    }

    @Override
    public int delete(BizMemberUser record) {
        return bizMemberUserMapper.delete(record.getId());
    }

    @Override
    public int delete(List<BizMemberUser> records) {
        for (BizMemberUser record : records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public BizMemberUser findById(Long id) {
        return bizMemberUserMapper.findById(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, bizMemberUserMapper);
    }

    @Override
    public BizMemberUser findByLoginName(String loginName) {
        return bizMemberUserMapper.findByLoginName(loginName);
    }

    @Override
    public BizMemberUserRespVo findByNameMyPage(String loginName) {
        BizMemberUser bizMemberUser = bizMemberUserMapper.findByLoginName(loginName);
        BizMemberUserRespVo bizMemberUserRespVo = castVo(bizMemberUser);
        return bizMemberUserRespVo;
    }

    private BizMemberUserRespVo castVo(BizMemberUser bizMemberUser) {
        BizMemberUserRespVo bizMemberUserRespVo = new BizMemberUserRespVo();
        bizMemberUserRespVo.setId(bizMemberUser.getId());
        bizMemberUserRespVo.setCompanyName(bizMemberUser.getCompanyName());
        bizMemberUserRespVo.setCompanyId(bizMemberUser.getCompanyId());
        bizMemberUserRespVo.setLoginName(bizMemberUser.getLoginName());
        bizMemberUserRespVo.setNickName(bizMemberUser.getNickName());
        bizMemberUserRespVo.setAvatar(bizMemberUser.getAvatar());
        bizMemberUserRespVo.setIfWechatLogin(bizMemberUser.getIfWechatLogin());
        bizMemberUserRespVo.setApproveStatus(bizMemberUser.getApproveStatus());
        bizMemberUserRespVo.setIfLocked(bizMemberUser.getIfLocked());
        bizMemberUserRespVo.setDelFlag(bizMemberUser.getDelFlag());
        Date lastUpTime = bizMemberUser.getLastUpdateTime();
        if (null != lastUpTime) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String lastUpTimeStr = simpleDateFormat.format(lastUpTime);
            bizMemberUserRespVo.setLastUpdateTime(lastUpTimeStr);
        }
        Date createTime = bizMemberUser.getCreateTime();
        if (null != lastUpTime) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String createTimeStr = simpleDateFormat.format(createTime);
            bizMemberUserRespVo.setCreateTime(createTimeStr);
        }
        return bizMemberUserRespVo;
    }

}
