package cn.roilat.cqzqjg.services.biz.vo;

import cn.roilat.cqzqjg.core.page.PageRequest;

/**
 * @program: zqjg
 * @description: 资产详情reqVo
 * @author: liujing
 * @create: 2020-01-21 10:15
 **/
public class BizPropertiesReqVo extends PageRequest {

    /**
     * 资产名称
     */
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
