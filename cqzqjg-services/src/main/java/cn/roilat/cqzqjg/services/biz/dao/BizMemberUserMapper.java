package cn.roilat.cqzqjg.services.biz.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.roilat.cqzqjg.services.biz.model.BizMemberCompany;
import cn.roilat.cqzqjg.services.biz.model.BizMemberUser;
import org.apache.ibatis.annotations.Param;

/**
 * --------------------------- 会员单位e用户信息表 (BizMemberUserMapper)
 * --------------------------- 作者： kitty-generator 时间： 2020-01-01 23:34:40 说明：
 * 我是由代码生成器生生成的 ---------------------------
 */
public interface BizMemberUserMapper {

    /**
     * 添加会员单位e用户信息表
     *
     * @param record
     * @return
     */
    int add(BizMemberUser record);

    /**
     * 删除会员单位e用户信息表
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改会员单位e用户信息表
     *
     * @param record
     * @return
     */
    int update(BizMemberUser record);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    BizMemberUser findById(Long id);

    /**
     * 基础分页查询
     *
     * @return
     */
    List<BizMemberUser> findPage();

    /**
     * 条件分页查询
     *
     * @return
     */
    List<BizMemberCompany> findPageByCondition(HashMap<String, Object> map);

    /**
     * 根据登录名查询
     *
     * @param loginName
     * @return
     */
    BizMemberUser findByLoginName(String loginName);

    /**
     * 根据登录名,id查询
     *
     * @param map
     * @return
     */
    BizMemberUser findByLoginNameAndId(@Param("params") Map<String, Object> map);

    /**
     * 根据openId查询
     *
     * @param openId
     * @return
     */
    BizMemberUser findByOpenId(String openId);

    /**
     * 根据id删除
     *
     * @return
     */
    Integer deleteById(@Param("params") Map<String, Object> map);

    /**
     * 禁用用户
     *
     * @return
     */
    Integer forbiddenUserById(@Param("params") Map<String, Object> map);

    /**
     * 重置密码
     *
     * @return
     */
    Integer resetPwdById(@Param("params") Map<String, Object> map);

}