package cn.roilat.cqzqjg.services.biz.dao;

import java.util.HashMap;
import java.util.List;

import cn.roilat.cqzqjg.services.biz.model.BizPortalInfo;

/**
 * ---------------------------
 * 首页的显示信息表 (BizPortalInfoMapper)
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2020-01-01 23:34:40
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public interface BizPortalInfoMapper {

    /**
     * 添加首页的显示信息表
     *
     * @param record
     * @return
     */
    int add(BizPortalInfo record);

    /**
     * 删除首页的显示信息表
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改首页的显示信息表
     *
     * @param record
     * @return
     */
    int update(BizPortalInfo record);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    BizPortalInfo findById(Long id);

    /**
     * 基础分页查询
     *
     * @return
     */
    List<BizPortalInfo> findHomePage();

    /**
     * 基础分页查询
     *
     * @return
     */
    List<BizPortalInfo> findNewsByTime(HashMap<String, Object> map);

    /**
     * 基础分页查询
     *
     * @return
     */
    List<BizPortalInfo> findPage();

}