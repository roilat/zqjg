package cn.roilat.cqzqjg.admin.biz.controller;

import java.util.*;

import cn.roilat.cqzqjg.common.utils.StringUtils;
import cn.roilat.cqzqjg.services.biz.vo.BizMemberCompanyReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.roilat.cqzqjg.core.http.HttpResult;
import cn.roilat.cqzqjg.core.page.PageRequest;
import cn.roilat.cqzqjg.core.page.PageResult;
import cn.roilat.cqzqjg.services.biz.model.BizMemberCompany;
import cn.roilat.cqzqjg.services.biz.service.BizMemberCompanyService;

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
@RequestMapping("bizMemberCompany")
public class BizMemberCompanyController {

	@Autowired
	private BizMemberCompanyService bizMemberCompanyService;

	/**
	 * 保存会员单位信息表
	 * @param record
	 * @return
	 */	
	@PostMapping(value="/save")
	@PreAuthorize("hasAuthority('biz:memberCompany:add') OR hasAuthority('biz:memberCompany:edit')")
	public HttpResult save(@RequestBody BizMemberCompany record) {
		if (StringUtils.isBlank(record.getPrimaryContactPerson())) {
			return HttpResult.error("联系人不能为空");
		}
		if (StringUtils.isBlank(record.getPrimaryContactInfo())) {
			return HttpResult.error("联系方式不能为空");
		}
		if (StringUtils.isBlank(record.getCompanyName())) {
			return HttpResult.error("单位名称不能为空");
		}
		if (StringUtils.isBlank(record.getCompanyCode())) {
			return HttpResult.error("企业统一信用代码不能为空");
		}
//		if (null == record.getRegistrationDate()) {
//			return HttpResult.error("企业注册时间不能为空");
//		}
//		if (StringUtils.isBlank(record.getCompanyPhone())) {
//			return HttpResult.error("企业联系电话不能为空");
//		}
//		if (StringUtils.isBlank(record.getCompanyAddress())) {
//			return HttpResult.error("企业注册地址不能为空");
//		}
		//新增时，才做此判断
		if (StringUtils.isBlank(record.getCreateBy()) && record.getId() == null) {
			return HttpResult.error("创建人不能为空");
		}
		Map<String, Object> map = new HashMap<>();
		map.put("companyName", record.getCompanyName());
		map.put("companyCode", record.getCompanyCode());
		List<BizMemberCompany> list = bizMemberCompanyService.findByCondition(map);
		if (null != list && list.size() > 0 && !list.get(0).getId().equals(record.getId())) {
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
     * @param records
     * @return
     */
	@PreAuthorize("hasAuthority('biz:memberCompany:delete')")
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<BizMemberCompany> records) {
		return HttpResult.ok(bizMemberCompanyService.delete(records));
	}

    /**
     * 基础分页查询
     * @param bizMemberCompanyReqVo
     * @return
     */    
	@PreAuthorize("hasAuthority('biz:memberCompany:view')")
	@PostMapping(value="/findPage")
	public HttpResult findPage(@RequestBody BizMemberCompanyReqVo bizMemberCompanyReqVo) {
//		PageResult result = new PageResult();
//		result.setPageNum(pageRequest.getPageNum());
//		result.setPageSize(pageRequest.getPageSize());
//		result.setTotalSize(10);
//		result.setTotalPages(1);
//		List<BizMemberCompany> content= new ArrayList<BizMemberCompany>();
//		result.setContent(content);
//		return HttpResult.ok(result);
//		return HttpResult.ok(bizMemberCompanyService.findPage(pageRequest));
		return HttpResult.ok(bizMemberCompanyService.findPageByName(bizMemberCompanyReqVo));
	}
	
    /**
     * 根据主键查询
     * @param id
     * @return
     */ 	
	@PreAuthorize("hasAuthority('biz:memberCompany:view')")
	@GetMapping(value="/findById")
	public HttpResult findById(@RequestParam Long id) {
		return HttpResult.ok(bizMemberCompanyService.findByIdResp(id));
	}

	/**
	 * 更新会员单位e用户信息表
	 *
	 * @param records
	 * @return
	 */
	@PreAuthorize("hasAuthority('biz:memberCompany:edit')")
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
	@PreAuthorize("hasAuthority('biz:memberCompany:view')")
	@PostMapping(value = "/deleteById")
	public HttpResult deleteById(@RequestBody List<Map<String, Object>> records) {
		return HttpResult.ok("删除成功", bizMemberCompanyService.deleteById(records));
	}
}
