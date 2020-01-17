package cn.roilat.cqzqjg.admin.wechat.comtroller;

import cn.roilat.cqzqjg.common.utils.StringUtils;
import cn.roilat.cqzqjg.core.http.HttpResult;
import cn.roilat.cqzqjg.core.page.PageRequest;
import cn.roilat.cqzqjg.services.biz.model.BizMemberCompany;
import cn.roilat.cqzqjg.services.biz.service.BizMemberCompanyService;
import cn.roilat.cqzqjg.services.biz.vo.BizMemberCompanyReqVo;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ---------------------------
 * 会员单位信息表 (BizMemberCompanyController)
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2020-01-01 23:34:40
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@RestController
@RequestMapping("wx/bizMemberCompany")
public class WxBizMemberCompanyController {

    @Autowired
    private BizMemberCompanyService bizMemberCompanyService;

    /**
     * 保存会员单位信息表
     *
     * @param record
     * @return
     */
    @PostMapping(value = "/save")
    public HttpResult save(@RequestBody BizMemberCompany record) {
        if (StringUtils.isBlank(record.getPrimaryContactPerson())) {
            return HttpResult.error("主要联系人不能为空");
        }
        if (StringUtils.isBlank(record.getPrimaryContactInfo())) {
            return HttpResult.error("主要联系方式不能为空");
        }
        if (StringUtils.isBlank(record.getCompanyName())) {
            return HttpResult.error("单位名称不能为空");
        }
        if (StringUtils.isBlank(record.getCompanyCode())) {
            return HttpResult.error("企业统一信用代码不能为空");
        }
        if (null == record.getRegistrationDate()) {
            return HttpResult.error("企业注册时间不能为空");
        }
        if (StringUtils.isBlank(record.getCompanyPhone())) {
            return HttpResult.error("企业联系电话不能为空");
        }
        if (StringUtils.isBlank(record.getCompanyAddress())) {
            return HttpResult.error("企业注册地址不能为空");
        }
        if (StringUtils.isBlank(record.getCreateBy())) {
            return HttpResult.error("创建人不能为空");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("companyName", record.getCompanyName());
        map.put("companyCode", record.getCompanyCode());
        List<BizMemberCompany> list = bizMemberCompanyService.findByCondition(map);
        if (null != list && list.size() > 0) {
            return HttpResult.error("公司名或注册机构号重复");
        }
        Integer i = bizMemberCompanyService.save(record);
        if (null != i && i > 0) {
            return HttpResult.ok("保存成功");
        } else {
            return HttpResult.error("保存失败请稍后再试");
        }
    }

    /**
     * 删除会员单位信息表
     *
     * @param records
     * @return
     */
    @PostMapping(value = "/delete")
    public HttpResult delete(@RequestBody List<BizMemberCompany> records) {
        return HttpResult.ok(bizMemberCompanyService.delete(records));
    }

    /**
     * 基础分页查询
     *
     * @param bizMemberCompanyReqVo
     * @return
     */
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody BizMemberCompanyReqVo bizMemberCompanyReqVo) {
        return HttpResult.ok(bizMemberCompanyService.findPageByName(bizMemberCompanyReqVo));
    }

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/findById")
    public HttpResult findById(@RequestParam Long id) {
        return HttpResult.ok(bizMemberCompanyService.findByIdResp(id));
    }

    /**
     * 更新会员单位e用户信息表
     *
     * @param records
     * @return
     */
    @PostMapping(value = "/update")
    public HttpResult update(@RequestBody BizMemberCompany records) {
        Long id = records.getId();
        if (null == id) {
            return HttpResult.error("id不能为空！");
        }
        // 用户信息
        BizMemberCompany company = bizMemberCompanyService.findById(id);
        // 账号不存在、密码错误
        if (company == null) {
            return HttpResult.error("公司不存在");
        }
        if (StringUtils.isBlank(records.getLastUpdateBy())) {
            return HttpResult.error("更新人不能为空");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("companyName", "");
        map.put("companyCode", "");
        if (!StringUtils.isBlank(records.getCompanyName())) {
            //公司名
            map.put("companyName", records.getCompanyName());
        }
        if (!StringUtils.isBlank(records.getCompanyName())) {
            //信用机构号
            map.put("companyCode", records.getCompanyCode());
        }
        List<BizMemberCompany> list = bizMemberCompanyService.findByCondition(map);
        if (null != list && list.size() > 0) {
            return HttpResult.error("公司名或注册机构号重复");
        }
        //更新时间
        records.setLastUpdateTime(new Date());
        return HttpResult.ok("更新成功", bizMemberCompanyService.update(records));
    }

    /**
     * 删除会员单位e用户信息表
     *
     * @param records
     * @return
     */
    @PostMapping(value = "/deleteById")
    public HttpResult deleteById(@RequestBody List<Map<String, Object>> records) {
        return HttpResult.ok("删除成功", bizMemberCompanyService.deleteById(records));
    }


    public static void main(String[] args) {
        BizMemberCompany bizMemberCompany = new BizMemberCompany();
        bizMemberCompany.setPrimaryContactPerson("悟空");
        bizMemberCompany.setPrimaryContactInfo("15870632119");
        bizMemberCompany.setCompanyName("西游记");
        bizMemberCompany.setCompanyCode("10010");
        bizMemberCompany.setLegalPerson("唐僧");
        bizMemberCompany.setOwnershipPattern("国有制");
        bizMemberCompany.setCompanyPlace("女儿国");
        bizMemberCompany.setRegistrationDate(new Date());
        bizMemberCompany.setRegistrationAssets("100万");
        bizMemberCompany.setCompanyPhone("10086");
        bizMemberCompany.setCompanyAddress("东土大唐");
        bizMemberCompany.setCompanyUrl("www.google.com");
        bizMemberCompany.setCreateBy("二师兄");
        bizMemberCompany.setCreateTime(new Date());
        bizMemberCompany.setDelFlag(0);

        System.out.println(JSON.toJSONString(bizMemberCompany));


    }


}
