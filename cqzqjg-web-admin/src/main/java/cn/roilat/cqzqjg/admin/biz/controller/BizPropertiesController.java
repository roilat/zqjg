package cn.roilat.cqzqjg.admin.biz.controller;

import cn.roilat.cqzqjg.core.http.HttpResult;
import cn.roilat.cqzqjg.core.page.PageResult;
import cn.roilat.cqzqjg.services.biz.model.BizProperties;
import cn.roilat.cqzqjg.services.biz.service.BizPropertiesService;
import cn.roilat.cqzqjg.services.biz.vo.BizPropertiesReqVo;
import cn.roilat.cqzqjg.services.biz.vo.BizPropertiesRespVo;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
@RequestMapping("bizProperties")
public class BizPropertiesController {

    /**
     * 新增成功
     */
    private static final int SAVE_SUCCESS = 0;
    /**
     * 新增失败
     */
    private static final int SAVE_FAIL = 1;
    /**
     * 更新成功
     */
    private static final int UPDATE_SUCCESS = 2;
    /**
     * 更新失败
     */
    private static final int UPDATE_FAIL = 3;
    /**
     * 传入id错误
     */
    private static final int ID_ERROR = 4;

    @Autowired
    private BizPropertiesService bizPropertiesService;

    /**
     * 保存或更新资产信息表
     *
     * @param record
     * @return
     */
    @PostMapping(value = "/save")
    public HttpResult save(@RequestBody BizProperties record) {
        int i = bizPropertiesService.save(record);
        switch (i) {
            case SAVE_SUCCESS:
                return HttpResult.ok("新增成功");
            case SAVE_FAIL:
                return HttpResult.ok("新增失败");
            case UPDATE_SUCCESS:
                return HttpResult.ok("更新成功");
            case UPDATE_FAIL:
                return HttpResult.ok("更新失败");
            case ID_ERROR:
                return HttpResult.ok("传入id错误");
            default:
                return HttpResult.error("后台错误");
        }
    }

    /**
     * 删除资产信息表
     *
     * @param records
     * @return
     */
    @PostMapping(value = "/delete")
    public HttpResult delete(@RequestBody List<BizProperties> records) {
        return HttpResult.ok(bizPropertiesService.delete(records));
    }

    /**
     * 基础分页查询
     *
     * @param pageRequest
     * @return
     */
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody BizPropertiesReqVo pageRequest) {
        PageResult pageResult = bizPropertiesService.findByNamePc(pageRequest);
        return HttpResult.ok("请求成功", pageResult);
    }


    /**
     * 资产详情接口
     */
    @GetMapping(value = {"/getById/{id}"})
    public HttpResult findById(@PathVariable Long id) {
        BizPropertiesRespVo bizPropertiesRespVo = bizPropertiesService.findDetailById(id);
        return HttpResult.ok("请求成功", bizPropertiesRespVo);
    }

    public static void main(String[] args) {
        BizProperties bizProperties = new BizProperties();
        bizProperties.setName("测试资产");
        bizProperties.setType("public");
        bizProperties.setCover("/img/9.jpg");
        bizProperties.setUnit("套");
        bizProperties.setQuantity(20);
        bizProperties.setAddress("成都市九龙坡经纬大道");
        bizProperties.setCreateTime(new Date());
        bizProperties.setCreateBy("xiakun");
        System.out.println(JSON.toJSONString(bizProperties));
    }


}
