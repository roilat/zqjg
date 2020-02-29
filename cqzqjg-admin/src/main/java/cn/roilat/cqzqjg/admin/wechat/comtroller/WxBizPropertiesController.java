package cn.roilat.cqzqjg.admin.wechat.comtroller;

import cn.roilat.cqzqjg.core.http.HttpResult;
import cn.roilat.cqzqjg.core.page.PageRequest;
import cn.roilat.cqzqjg.services.biz.model.BizProperties;
import cn.roilat.cqzqjg.services.biz.service.BizPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ---------------------------
 * 资产信息表 (BizPropertiesController)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2020-01-01 23:34:40
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@RestController
@RequestMapping("wx/bizProperties")
public class WxBizPropertiesController {

	@Autowired
	private BizPropertiesService bizPropertiesService;

	/**
	 * 保存资产信息表
	 * @param record
	 * @return
	 */	
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody BizProperties record) {
		return HttpResult.ok(bizPropertiesService.save(record));
	}

    /**
     * 删除资产信息表
     * @param records
     * @return
     */
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<BizProperties> records) {
		return HttpResult.ok(bizPropertiesService.delete(records));
	}

    /**
     * 基础分页查询
     * @param pageRequest
     * @return
     */    
	@PostMapping(value="/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(bizPropertiesService.findPage(pageRequest));
	}
	
    /**
     * 根据主键查询
     * @param id
     * @return
     */ 	
	@GetMapping(value="/findById")
	public HttpResult findById(@RequestParam Long id) {
		return HttpResult.ok(bizPropertiesService.findById(id));
	}
}
