package cn.roilat.cqzqjg.services.biz.vo;

import java.util.Date;

/**
 * @program: zqjg
 * @description: 资产详情vo
 * @author: liujing
 * @create: 2020-01-06 22:46
 **/
public class BizPropertiesRespVo {
    /**
     * 主键
     */
    private Long id;
    /**
     * 资产名称
     */
    private String name;
    /**
     * 资产类型
     */
    private String type;
    /**
     * 封面图片
     */
    private String cover;
    /**
     * 单位
     */
    private String unit;
    /**
     * 资产数量
     */
    private Integer quantity;
    /**
     * 所在地区
     */
    private String address;
    /**
     * 内容描述
     */
    private String content;
    /**
     * 开始持有时间
     */
    private java.util.Date startOwnDate;
    /**
     * 最后持有时间
     */
    private java.util.Date lastOwnDate;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    private java.util.Date createTime;
    /**
     * 更新人
     */
    private String lastUpdateBy;
    /**
     * 更新时间
     */
    private String lastUpdateTime;
    /**
     * 是否删除  -1：已删除  0：正常
     */
    private Integer delFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getStartOwnDate() {
        return startOwnDate;
    }

    public void setStartOwnDate(Date startOwnDate) {
        this.startOwnDate = startOwnDate;
    }

    public Date getLastOwnDate() {
        return lastOwnDate;
    }

    public void setLastOwnDate(Date lastOwnDate) {
        this.lastOwnDate = lastOwnDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}
