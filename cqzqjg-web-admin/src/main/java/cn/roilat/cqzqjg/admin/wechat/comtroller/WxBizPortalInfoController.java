package cn.roilat.cqzqjg.admin.wechat.comtroller;

import cn.roilat.cqzqjg.core.http.HttpResult;
import cn.roilat.cqzqjg.core.page.PageRequest;
import cn.roilat.cqzqjg.services.biz.model.BizPortalInfo;
import cn.roilat.cqzqjg.services.biz.service.BizPortalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ---------------------------
 * 首页的显示信息表 (BizPortalInfoController)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2020-01-01 23:34:40
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@RestController
@RequestMapping("wx/bizPortalInfo")
public class WxBizPortalInfoController {

	@Autowired
	private BizPortalInfoService bizPortalInfoService;

	/**
	 * 保存首页的显示信息表
	 * @param record
	 * @return
	 */	
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody BizPortalInfo record) {
		return HttpResult.ok(bizPortalInfoService.save(record));
	}

    /**
     * 删除首页的显示信息表
     * @param records
     * @return
     */
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<BizPortalInfo> records) {
		return HttpResult.ok(bizPortalInfoService.delete(records));
	}

    /**
     * 基础分页查询
     * @param pageRequest
     * @return
     */    
	@PostMapping(value="/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(bizPortalInfoService.findPage(pageRequest));
	}
	
    /**
     * 根据主键查询
     * @param id
     * @return
     */ 	
	@GetMapping(value="/findById")
	public HttpResult findById(@RequestParam Long id) {
		return HttpResult.ok(bizPortalInfoService.findById(id));
	}
}
