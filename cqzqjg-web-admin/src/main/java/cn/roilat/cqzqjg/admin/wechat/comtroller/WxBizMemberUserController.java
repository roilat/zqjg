package cn.roilat.cqzqjg.admin.wechat.comtroller;

import cn.roilat.cqzqjg.common.util.PasswordUtils;
import cn.roilat.cqzqjg.common.utils.StringUtils;
import cn.roilat.cqzqjg.common.vo.UserBean;
import cn.roilat.cqzqjg.core.http.HttpResult;
import cn.roilat.cqzqjg.core.page.PageRequest;
import cn.roilat.cqzqjg.services.biz.model.BizMemberUser;
import cn.roilat.cqzqjg.services.biz.service.BizMemberUserService;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
@RequestMapping("wx/bizMemberUser")
public class WxBizMemberUserController {

    @Autowired
    private BizMemberUserService bizMemberUserService;

    /**
     * 保存会员单位e用户信息表
     *
     * @param userBean
     * @return
     */
    @PostMapping(value = "/save")
    public HttpResult save(@RequestBody UserBean userBean) {
        if (StringUtils.isBlank(userBean.getAccount())) {
            return HttpResult.error("用户名不能为空");
        }
        if (StringUtils.isBlank(userBean.getAdminUser())) {
            return HttpResult.error("创建人不能为空");
        }
        if (StringUtils.isBlank(userBean.getPassword())) {
            return HttpResult.error("密码不能为空");
        }
        BizMemberUser user = bizMemberUserService.findByLoginName(userBean.getAccount());
        if (null != user) {
            return HttpResult.error("登录账号重复");
        }

        //盐
        String salt = PasswordUtils.getSalt();
        //密码加密
        String password = PasswordUtils.encode(userBean.getPassword(), salt);

        BizMemberUser bizMemberUser = new BizMemberUser();
        bizMemberUser.setLoginName(userBean.getAccount());
        bizMemberUser.setCompanyId(userBean.getCompanyId());
        bizMemberUser.setCompanyName(userBean.getCompanyName());
        bizMemberUser.setPassword(password);
        bizMemberUser.setSalt(salt);
        bizMemberUser.setCreateTime(new Date());
        bizMemberUser.setCreateBy(userBean.getAdminUser());
        bizMemberUser.setNickName(userBean.getNickName());
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
     * 删除会员单位e用户信息表
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
     * 基础分页查询
     *
     * @param pageRequest
     * @return
     */
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        return HttpResult.ok(bizMemberUserService.findPage(pageRequest));
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


    public static void main(String[] args) {
        List<Map<String, Object>> records = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        map1.put("id", 1);
        map1.put("lastUpdateBy", "liujing");

        map2.put("id", 2);
        map2.put("lastUpdateBy", "liujing2");

        records.add(map1);
        records.add(map2);

        System.out.println(JSON.toJSONString(records));


    }

}