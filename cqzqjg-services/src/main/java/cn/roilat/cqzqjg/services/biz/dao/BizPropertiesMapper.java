package cn.roilat.cqzqjg.services.biz.dao;

import java.util.List;

import cn.roilat.cqzqjg.services.biz.model.BizProperties;
import org.apache.ibatis.annotations.Param;

/**
 * ---------------------------
 * 资产信息表 (BizPropertiesMapper)
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2020-01-01 23:34:40
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public interface BizPropertiesMapper {

    /**
     * 添加资产信息表
     *
     * @param record
     * @return
     */
    int add(BizProperties record);

    /**
     * 删除资产信息表
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改资产信息表
     *
     * @param record
     * @return
     */
    int update(BizProperties record);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    BizProperties findById(Long id);


    /**
     * 根据名称查询
     *
     * @param name
     * @return
     */
    List<BizProperties> findByName(@Param("name") String name);

    /**
     * 基础分页查询
     *
     * @return
     */
    List<BizProperties> findPage();

}