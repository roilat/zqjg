package cn.roilat.cqzqjg.services.biz.service.impl;

import cn.roilat.cqzqjg.core.page.MybatisPageHelper;
import cn.roilat.cqzqjg.core.page.PageRequest;
import cn.roilat.cqzqjg.core.page.PageResult;
import cn.roilat.cqzqjg.services.biz.dao.BizMemberUserMapper;
import cn.roilat.cqzqjg.services.biz.model.BizMemberUser;
import cn.roilat.cqzqjg.services.biz.service.BizMemberUserService;
import cn.roilat.cqzqjg.services.biz.vo.BizMemberUserRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * --------------------------- 会员单位e用户信息表 (BizMemberUserServiceImpl)
 * --------------------------- 作者： kitty-generator 时间： 2020-01-01 23:34:40 说明：
 * 我是由代码生成器生生成的 ---------------------------
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
    public Integer deleteById(List<Map<String, Object>> params) {
        for (Map<String, Object> map : params) {
            bizMemberUserMapper.deleteById(map);
        }
        return 1;
    }

    @Override
    public List<BizMemberUserRespVo> findPageResp(PageRequest pageRequest) {
        List<BizMemberUserRespVo> respList = new ArrayList<>();
        List<BizMemberUser> bizMemberUsers = bizMemberUserMapper.findPage();
        if (null == bizMemberUsers || bizMemberUsers.size() == 0) {
            return respList;
        }
        for (BizMemberUser bizMemberUser : bizMemberUsers) {
            BizMemberUserRespVo bizMemberUserRespVo = castVo(bizMemberUser);
            respList.add(bizMemberUserRespVo);

        }
        return respList;
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
        if (null == bizMemberUser) {
            return new BizMemberUserRespVo();
        }
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

    @Override
    public BizMemberUser findByOpenId(String openId) {
        return bizMemberUserMapper.findByOpenId(openId);
    }

    @Override
    public Boolean removeWechat(Long id) {
        Boolean result = false;
        BizMemberUser user = findById(id);
        if (null != user) {
            //解除微信绑定，将用户表中微信绑定字段置空
            //微信头像
            //user.setAvatar("");
            //微信号
            user.setWechat("");
            //是否绑定微信
            user.setIfWechatLogin("0");
            //更新时间
            user.setLastUpdateTime(new Date());
            //更新人
            user.setLastUpdateBy(user.getLoginName());
            Integer i = save(user);
            if (i > 0) {
                result = true;
            }
        }
        return result;
    }


}
