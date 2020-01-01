package cn.roilat.cqzqjg.services.biz.model;

/**
 * ---------------------------
 * 资产信息表 (BizProperties)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2020-01-01 23:34:40
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public class BizProperties {

	/** 主键 */
	private Long id;
	/** 资产名称 */
	private String name;
	/** 资产类型 */
	private String type;
	/** 封面图片 */
	private String cover;
	/** 单位 */
	private String unit;
	/** 资产数量 */
	private Integer quantity;
	/** 所在地区 */
	private String address;
	/** 内容描述 */
	private String content;
	/** 开始持有时间 */
	private java.util.Date startOwnDate;
	/** 最后持有时间 */
	private java.util.Date lastOwnDate;
	/** 创建人 */
	private String createBy;
	/** 创建时间 */
	private java.util.Date createTime;
	/** 更新人 */
	private String lastUpdateBy;
	/** 更新时间 */
	private java.util.Date lastUpdateTime;
	/** 是否删除  -1：已删除  0：正常 */
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

	public java.util.Date getStartOwnDate() {
		return startOwnDate;
	}

	public void setStartOwnDate(java.util.Date startOwnDate) {
		this.startOwnDate = startOwnDate;
	}

	public java.util.Date getLastOwnDate() {
		return lastOwnDate;
	}

	public void setLastOwnDate(java.util.Date lastOwnDate) {
		this.lastOwnDate = lastOwnDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public java.util.Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(java.util.Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

}