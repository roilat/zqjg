package cn.roilat.cqzqjg.admin.wechat.comtroller;

import cn.roilat.cqzqjg.core.http.HttpResult;
import cn.roilat.cqzqjg.core.page.PageRequest;
import cn.roilat.cqzqjg.services.biz.model.BizPortalInfoCategory;
import cn.roilat.cqzqjg.services.biz.service.BizPortalInfoCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ---------------------------
 * 首页信息分类表 (BizPortalInfoCategoryController)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2020-01-01 23:34:40
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@RestController
@RequestMapping("wx/bizPortalInfoCategory")
public class WxBizPortalInfoCategoryController {

	@Autowired
	private BizPortalInfoCategoryService bizPortalInfoCategoryService;

	/**
	 * 保存首页信息分类表
	 * @param record
	 * @return
	 */	
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody BizPortalInfoCategory record) {
		return HttpResult.ok(bizPortalInfoCategoryService.save(record));
	}

    /**
     * 删除首页信息分类表
     * @param records
     * @return
     */
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<BizPortalInfoCategory> records) {
		return HttpResult.ok(bizPortalInfoCategoryService.delete(records));
	}

    /**
     * 基础分页查询
     * @param pageRequest
     * @return
     */    
	@PostMapping(value="/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(bizPortalInfoCategoryService.findPage(pageRequest));
	}
	
    /**
     * 根据主键查询
     * @param typeCode
     * @return
     */ 	
	@GetMapping(value="/findById")
	public HttpResult findById(@RequestParam String typeCode) {
		return HttpResult.ok(bizPortalInfoCategoryService.findById(typeCode));
	}
}
