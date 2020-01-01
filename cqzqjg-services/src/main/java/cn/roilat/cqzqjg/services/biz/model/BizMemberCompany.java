package cn.roilat.cqzqjg.services.biz.model;

/**
 * ---------------------------
 * 会员单位信息表 (BizMemberCompany)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2020-01-01 23:34:40
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public class BizMemberCompany {

	/** 主键 */
	private Long id;
	/** 会员类型 */
	private String type;
	/** 加入时间 */
	private java.util.Date joinDate;
	/** 退出时间 */
	private java.util.Date quitDate;
	/** 主要联系人 */
	private String primaryContactPerson;
	/** 主要联系人联系方式 */
	private String primaryContactInfo;
	/** 单位名称 */
	private String companyName;
	/** 企业统一信用代码 */
	private String companyCode;
	/** 企业法人 */
	private String legalPerson;
	/** 所有制类型 */
	private String ownershipPattern;
	/** 场所 */
	private String companyPlace;
	/** 注册时间 */
	private java.util.Date registrationDate;
	/** 注册资本 */
	private String registrationAssets;
	/** 联系电话 */
	private String companyPhone;
	/** 联系地址 */
	private String companyAddress;
	/** 传真 */
	private String companyFax;
	/** 邮箱 */
	private String companyEmail;
	/** 网址 */
	private String companyUrl;
	/** 记录状态(0草稿,1已发布) */
	private String recordStatus;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public java.util.Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(java.util.Date joinDate) {
		this.joinDate = joinDate;
	}

	public java.util.Date getQuitDate() {
		return quitDate;
	}

	public void setQuitDate(java.util.Date quitDate) {
		this.quitDate = quitDate;
	}

	public String getPrimaryContactPerson() {
		return primaryContactPerson;
	}

	public void setPrimaryContactPerson(String primaryContactPerson) {
		this.primaryContactPerson = primaryContactPerson;
	}

	public String getPrimaryContactInfo() {
		return primaryContactInfo;
	}

	public void setPrimaryContactInfo(String primaryContactInfo) {
		this.primaryContactInfo = primaryContactInfo;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getOwnershipPattern() {
		return ownershipPattern;
	}

	public void setOwnershipPattern(String ownershipPattern) {
		this.ownershipPattern = ownershipPattern;
	}

	public String getCompanyPlace() {
		return companyPlace;
	}

	public void setCompanyPlace(String companyPlace) {
		this.companyPlace = companyPlace;
	}

	public java.util.Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(java.util.Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getRegistrationAssets() {
		return registrationAssets;
	}

	public void setRegistrationAssets(String registrationAssets) {
		this.registrationAssets = registrationAssets;
	}

	public String getCompanyPhone() {
		return companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanyFax() {
		return companyFax;
	}

	public void setCompanyFax(String companyFax) {
		this.companyFax = companyFax;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getCompanyUrl() {
		return companyUrl;
	}

	public void setCompanyUrl(String companyUrl) {
		this.companyUrl = companyUrl;
	}

	public String getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
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