package cn.roilat.cqzqjg.services.biz.model;

/**
 * ---------------------------
 * 资产信息历史信息表 (BizPropertiesHistory)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2020-01-01 23:34:40
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public class BizPropertiesHistory {

	/** 主键 */
	private Long id;
	/** 所属资产id */
	private Long properyId;
	/** 新资产名称 */
	private String newName;
	/** 旧资产名称 */
	private String oldName;
	/** 新封面图片 */
	private String newCover;
	/** 旧封面图片 */
	private String oldCover;
	/** 新资产类型 */
	private String newType;
	/** 旧资产类型 */
	private String oldType;
	/** 新单位 */
	private String newUnit;
	/** 旧单位 */
	private String oldUnit;
	/** 新资产数量 */
	private Integer newQuantity;
	/** 旧资产数量 */
	private Integer oldQuantity;
	/** 新所在地区 */
	private String newAddress;
	/** 旧所在地区 */
	private String oldAddress;
	/** 新内容描述 */
	private String newContent;
	/** 旧内容描述 */
	private String oldContent;
	/** 创建人 */
	private String createBy;
	/** 创建时间 */
	private java.util.Date createTime;
	/** 是否删除  -1：已删除  0：正常 */
	private Integer delFlag;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProperyId() {
		return properyId;
	}

	public void setProperyId(Long properyId) {
		this.properyId = properyId;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	public String getNewCover() {
		return newCover;
	}

	public void setNewCover(String newCover) {
		this.newCover = newCover;
	}

	public String getOldCover() {
		return oldCover;
	}

	public void setOldCover(String oldCover) {
		this.oldCover = oldCover;
	}

	public String getNewType() {
		return newType;
	}

	public void setNewType(String newType) {
		this.newType = newType;
	}

	public String getOldType() {
		return oldType;
	}

	public void setOldType(String oldType) {
		this.oldType = oldType;
	}

	public String getNewUnit() {
		return newUnit;
	}

	public void setNewUnit(String newUnit) {
		this.newUnit = newUnit;
	}

	public String getOldUnit() {
		return oldUnit;
	}

	public void setOldUnit(String oldUnit) {
		this.oldUnit = oldUnit;
	}

	public Integer getNewQuantity() {
		return newQuantity;
	}

	public void setNewQuantity(Integer newQuantity) {
		this.newQuantity = newQuantity;
	}

	public Integer getOldQuantity() {
		return oldQuantity;
	}

	public void setOldQuantity(Integer oldQuantity) {
		this.oldQuantity = oldQuantity;
	}

	public String getNewAddress() {
		return newAddress;
	}

	public void setNewAddress(String newAddress) {
		this.newAddress = newAddress;
	}

	public String getOldAddress() {
		return oldAddress;
	}

	public void setOldAddress(String oldAddress) {
		this.oldAddress = oldAddress;
	}

	public String getNewContent() {
		return newContent;
	}

	public void setNewContent(String newContent) {
		this.newContent = newContent;
	}

	public String getOldContent() {
		return oldContent;
	}

	public void setOldContent(String oldContent) {
		this.oldContent = oldContent;
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

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

}