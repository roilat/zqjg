package cn.roilat.cqzqjg.admin.biz.controller;

import cn.roilat.cqzqjg.common.util.PasswordUtils;
import cn.roilat.cqzqjg.common.utils.StringUtils;
import cn.roilat.cqzqjg.common.vo.UserBean;
import cn.roilat.cqzqjg.core.http.HttpResult;
import cn.roilat.cqzqjg.services.biz.model.BizMemberUser;
import cn.roilat.cqzqjg.services.biz.service.BizMemberUserService;
import cn.roilat.cqzqjg.services.biz.vo.BizMemberReqVo;
import cn.roilat.cqzqjg.services.biz.vo.VerifyReqVo;
import cn.roilat.cqzqjg.services.system.sevice.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ---------------------------
 * 会员单位e用户信息表 (BizMemberUserController)
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2020-01-01 23:34:40
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@RestController
@RequestMapping("bizMemberUser")
public class BizMemberUserController {

    @Autowired
    private BizMemberUserService bizMemberUserService;

    @Autowired
    private SysDictService sysDictService;

    /**
     * 已绑定微信
     */
    private static final String HAVE_WECHAT = "1";
    /**
     * 未绑定微信
     */
    private static final String NO_WECHAT = "0";


    /**
     * 保存会员单位e用户信息表
     *
     * @param userBean
     * @return
     */
    @PostMapping(value = "/save")
    public HttpResult save(@RequestBody UserBean userBean) {
        BizMemberUser bizMemberUser = new BizMemberUser();
        bizMemberUser.setIfWechatLogin(NO_WECHAT);
        if (StringUtils.isBlank(userBean.getLoginName())) {
            return HttpResult.error("用户名不能为空");
        }
        if (StringUtils.isBlank(userBean.getAdminUser())) {
            return HttpResult.error("创建人不能为空");
        }
        if (!StringUtils.isBlank(userBean.getIfWechatLogin())) {
            if (HAVE_WECHAT.equals(userBean.getIfWechatLogin().replaceAll(" ", ""))) {
                //绑定微信
                if (StringUtils.isBlank(userBean.getOpenId()) || StringUtils.isBlank(userBean.getWechat())) {
                    return HttpResult.error("绑定微信时请传入openId和微信号");
                } else {
                    bizMemberUser.setOpenId(userBean.getOpenId());
                    bizMemberUser.setIfWechatLogin(HAVE_WECHAT);
                    bizMemberUser.setWechat(userBean.getWechat());
                }
            }
        }
        BizMemberUser user = bizMemberUserService.findByLoginName(userBean.getLoginName());
        if (null != user) {
            return HttpResult.error("登录账号重复");
        }
        //盐
        String salt = PasswordUtils.getSalt();
        //密码加密,后台默认密码Abc@123
        String password = PasswordUtils.encode("Abc@123", salt);

        bizMemberUser.setLoginName(userBean.getLoginName());
        bizMemberUser.setCompanyName(userBean.getCompanyName());
        bizMemberUser.setPassword(password);
        bizMemberUser.setSalt(salt);
        bizMemberUser.setCreateTime(new Date());
        bizMemberUser.setCreateBy(userBean.getAdminUser());
        bizMemberUser.setNickName(userBean.getNickName());
        bizMemberUser.setAvatar(userBean.getAvatar());
        bizMemberUser.setPhoneNumber(userBean.getPhoneNumber());
        //管理员新建用户默认不删除
        bizMemberUser.setDelFlag(0);

        //保存用户
        int i = bizMemberUserService.save(bizMemberUser);
        if (i > 0) {
            return HttpResult.ok("注册成功");
        } else {
            return HttpResult.error("注册失败，请重试");
        }
    }

    /**
     * 删除会员单位e用户信息表
     *
     * @param records
     * @return
     */
    @PostMapping(value = "/delete")
    public HttpResult delete(@RequestBody List<BizMemberUser> records) {
        return HttpResult.ok(bizMemberUserService.delete(records));
    }

    /**
     * 删除会员单位e用户信息表
     *
     * @param records
     * @return
     */
    @PostMapping(value = "/deleteById")
    public HttpResult deleteById(@RequestBody List<Map<String, Object>> records) {
        return HttpResult.ok("删除成功", bizMemberUserService.deleteById(records));
    }

    /**
     * 禁用或解禁会员
     *
     * @param records
     * @return
     */
    @PostMapping(value = "/lockUserById")
    public HttpResult lockUserById(@RequestBody List<Map<String, Object>> records) {
        return bizMemberUserService.forbiddenUserById(records);
    }

    /**
     * 重置密码
     *
     * @param records
     * @return
     */
    @PostMapping(value = "/resetPwdById")
    public HttpResult resetPwdById(@RequestBody List<Map<String, Object>> records) {
        return bizMemberUserService.resetPwdById(records);
    }


    /**
     * 更新会员单位e用户信息表
     *
     * @param records
     * @return
     */
    @PostMapping(value = "/update")
    public HttpResult update(@RequestBody BizMemberUser records) {
        Long id = records.getId();
        if (null == id) {
            return HttpResult.error("id不能为空！");
        }
        // 用户信息
        BizMemberUser user = bizMemberUserService.findById(id);
        // 账号不存在、密码错误
        if (user == null) {
            return HttpResult.error("账号不存在");
        }
        if (StringUtils.isBlank(records.getLastUpdateBy())) {
            return HttpResult.error("更新人不能为空");
        }
        if (!StringUtils.isBlank(records.getLoginName())) {
            //登录名
            if (null != bizMemberUserService.findByLoginName(records.getLoginName())) {
                return HttpResult.error("登录名称重复");
            }
            user.setLoginName(records.getLoginName());
        }
        if (!StringUtils.isBlank(records.getCompanyName())) {
            //公司名
            user.setCompanyName(records.getCompanyName());
        }
        if (!StringUtils.isBlank(records.getNickName())) {
            //客户昵称
            user.setNickName(records.getNickName());
        }
        if (!StringUtils.isBlank(records.getIfWechatLogin())) {
            //是否绑定微信
            user.setIfWechatLogin(records.getIfWechatLogin());
        }
        if (!StringUtils.isBlank(records.getApproveStatus())) {
            //批状态(0待审核,1审核不通过2审核通过)
            user.setApproveStatus(records.getApproveStatus());
        }
        if (!StringUtils.isBlank(records.getIfLocked())) {
            //是否锁定
            user.setIfLocked(records.getIfLocked());
        }
        if (!StringUtils.isBlank(records.getLastUpdateBy())) {
            //更新人
            user.setLastUpdateBy(records.getLastUpdateBy());
        }
        if (!StringUtils.isBlank(records.getOpenId())) {
            //微信id
            user.setOpenId(records.getOpenId());
        }
        if (null != records.getDelFlag()) {
            // 是否删除 -1：已删除 0：正常
            user.setDelFlag(records.getDelFlag());
        }
        if (!StringUtils.isBlank(records.getAvatar())) {
            //微信id
            user.setAvatar(records.getAvatar());
        }
        //更新时间
        user.setLastUpdateTime(new Date());
        return HttpResult.ok("更新成功", bizMemberUserService.save(user));
    }

    /**
     * 会员类型
     *
     * @return
     */
    @GetMapping(value = "/companyType")
    public HttpResult getCompanyType() {
        return HttpResult.ok("请求成功", sysDictService.findCompanyType());
    }

    /**
     * 基础分页查询
     *
     * @param bizMemberReqVo
     * @return
     */
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody BizMemberReqVo bizMemberReqVo) {
        return HttpResult.ok("请求成功", bizMemberUserService.findPageResp(bizMemberReqVo));
    }

    /**
	 * 会员审核
	 *
	 * @return
	 */
	@PostMapping(value = "/verifyUser")
	public HttpResult verifyUser(@RequestBody VerifyReqVo records) {
		if (StringUtils.isBlank(records.getApproveStatus())) {
			return HttpResult.error("审核状态不能为空");
		}
		if (StringUtils.isBlank(records.getApproveDesc())) {
			return HttpResult.error("审核意见不能为空");
		}
		if (StringUtils.isBlank(records.getId())) {
			return HttpResult.error("用户id不能为空");
		}
		Boolean resultFlag = bizMemberUserService.verifyUser(records);
		if (resultFlag) {
			return HttpResult.ok("审核成功");
		} else {
			return HttpResult.error("审核失败");
		}
	}

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/findById")
    public HttpResult findById(@RequestParam Long id) {
        return HttpResult.ok(bizMemberUserService.findById(id));
    }
}
