package cn.roilat.cqzqjg.services.biz.service.impl;

import cn.roilat.cqzqjg.common.util.PasswordUtils;
import cn.roilat.cqzqjg.common.utils.StringUtils;
import cn.roilat.cqzqjg.core.http.HttpResult;
import cn.roilat.cqzqjg.core.page.MybatisPageHelper;
import cn.roilat.cqzqjg.core.page.PageRequest;
import cn.roilat.cqzqjg.core.page.PageResult;
import cn.roilat.cqzqjg.services.biz.dao.BizMemberUserMapper;
import cn.roilat.cqzqjg.services.biz.model.BizMemberUser;
import cn.roilat.cqzqjg.services.biz.service.BizMemberUserService;
import cn.roilat.cqzqjg.services.biz.vo.BizMemberReqVo;
import cn.roilat.cqzqjg.services.biz.vo.BizMemberUserRespVo;
import cn.roilat.cqzqjg.services.biz.vo.VerifyReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

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
    public HttpResult forbiddenUserById(List<Map<String, Object>> params) {
        for (Map<String, Object> map : params) {
            //用户id
            Long id = (Long) map.get("id");
            if (null == id) {
                return HttpResult.error("id为空");
            }
            BizMemberUser bizMemberUser = findById(id);
            if (null == bizMemberUser) {
                return HttpResult.error("用户不存在");
            }
        }
        for (Map<String, Object> map : params) {
            bizMemberUserMapper.forbiddenUserById(map);
        }
        return HttpResult.ok("锁定或解锁用户成功");
    }

    @Override
    public HttpResult resetPwdById(List<Map<String, Object>> params) {
        for (Map<String, Object> map : params) {
            //用户id
            Integer id = (Integer) map.get("id");
            if (null == id) {
                return HttpResult.error("id为空");
            }
            BizMemberUser bizMemberUser = findById(Long.valueOf(id));
            if (null == bizMemberUser) {
                return HttpResult.error("用户不存在");
            }
        }
        for (Map<String, Object> map : params) {
            //盐
            String salt = PasswordUtils.getSalt();
            //密码加密,后台默认密码Abc@123
            String password = PasswordUtils.encode("Abc@123", salt);
            map.put("password", password);
            map.put("salt", salt);
            bizMemberUserMapper.resetPwdById(map);
        }
        return HttpResult.ok("密码重置成功");
    }

    @Override
    public PageResult findPageResp(BizMemberReqVo bizMemberReqVo) {
        Map<String, Object> map = new HashMap<>();
        if (!StringUtils.isBlank(bizMemberReqVo.getCompanyName())) {
            map.put("companyName", bizMemberReqVo.getCompanyName());
        }
        if (!StringUtils.isBlank(bizMemberReqVo.getLoginName())) {
            map.put("loginName", bizMemberReqVo.getLoginName());
        }
        if (!StringUtils.isBlank(bizMemberReqVo.getNickName())) {
            map.put("nickName", bizMemberReqVo.getNickName());
        }
        if (!StringUtils.isBlank(bizMemberReqVo.getPhoneNumber())) {
            map.put("phoneNumber", bizMemberReqVo.getPhoneNumber());
        }

        if (null != bizMemberReqVo.getApproveStatus()) {
            map.put("approveStatus", bizMemberReqVo.getApproveStatus());
        }

        PageResult pageResult = MybatisPageHelper.findPage(bizMemberReqVo, bizMemberUserMapper, "findPageByCondition", map);
        List<BizMemberUser> list = (List<BizMemberUser>) pageResult.getContent();
        List<BizMemberUserRespVo> respList = new ArrayList<>();
        if (null != list && list.size() > 0) {
            for (BizMemberUser bizMemberUser : list) {
                BizMemberUserRespVo bizMemberUserRespVo = castVo(bizMemberUser);
                respList.add(bizMemberUserRespVo);

            }
            pageResult.setContent(respList);
        }

        return pageResult;
    }

    @Override
    public Boolean verifyUser(VerifyReqVo verifyReqVo) {
        Map<String, Object> map = new HashMap<>();
        //用户id
        Long id = null;
        if (!StringUtils.isBlank(verifyReqVo.getApproveStatus())) {
            map.put("approveStatus", verifyReqVo.getApproveStatus());
        }
        if (!StringUtils.isBlank(verifyReqVo.getApproveDesc())) {
            map.put("approveDesc", verifyReqVo.getApproveDesc());
        }
        if (!StringUtils.isBlank(verifyReqVo.getId())) {
            map.put("id", verifyReqVo.getId());
            id = Long.valueOf(verifyReqVo.getId());
        }
        BizMemberUser bizMemberUser = findById(id);
        if (null == bizMemberUser) {
            return false;
        }
        bizMemberUser.setApproveDesc(verifyReqVo.getApproveDesc());
        bizMemberUser.setApproveStatus(verifyReqVo.getApproveStatus());
        bizMemberUser.setCompanyId(Integer.valueOf(verifyReqVo.getCompanyId()));
        bizMemberUser.setCompanyName(verifyReqVo.getCompanyName());
        Integer i = save(bizMemberUser);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
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
        bizMemberUserRespVo.setPhoneNumber(bizMemberUser.getPhoneNumber());
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
