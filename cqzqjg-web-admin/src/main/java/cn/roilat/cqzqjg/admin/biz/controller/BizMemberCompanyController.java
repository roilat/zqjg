package cn.roilat.cqzqjg.admin.biz.controller;

import java.util.ArrayList;
import java.util.List;

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
	@PreAuthorize("hasAuthority('biz:memberCompany:add') AND hasAuthority('biz:memberCompany:edit')")
	public HttpResult save(@RequestBody BizMemberCompany record) {
		return HttpResult.ok(bizMemberCompanyService.save(record));
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
     * @param pageRequest
     * @return
     */    
	@PreAuthorize("hasAuthority('biz:memberCompany:view')")
	@PostMapping(value="/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		PageResult result = new PageResult();
		result.setPageNum(pageRequest.getPageNum());
		result.setPageSize(pageRequest.getPageSize());
		result.setTotalSize(10);
		result.setTotalPages(1);
		List<BizMemberCompany> content= new ArrayList<BizMemberCompany>();
		result.setContent(content);
		return HttpResult.ok(result);
//		return HttpResult.ok(bizMemberCompanyService.findPage(pageRequest));
	}
	
    /**
     * 根据主键查询
     * @param id
     * @return
     */ 	
	@PreAuthorize("hasAuthority('biz:memberCompany:view')")
	@GetMapping(value="/findById")
	public HttpResult findById(@RequestParam Long id) {
		return HttpResult.ok(bizMemberCompanyService.findById(id));
	}
}
