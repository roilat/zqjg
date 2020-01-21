package cn.roilat.cqzqjg.services.biz.vo;

/**
 * @program: zqjg
 * @description: 公司Vo
 * @author: liujing
 * @create: 2020-01-08 10:02
 **/
public class BizMemberCompanyResp {

    /**
     * 主键
     */
    private Long id;

    /**
     * 类型
     */
    private String type;

    /**
     * 网址
     */
    private String companyUrl;

    /**
     * 单位名称
     */
    private String companyName;
    /**
     * 企业统一信用代码
     */
    private String companyCode;
    /**
     * 企业法人
     */
    private String legalPerson;
    /**
     * 联系电话
     */
    private String companyPhone;
    /**
     * 传真
     */
    private String companyFax;
    /**
     * 邮箱
     */
    private String companyEmail;
    /**
     * 联系地址
     */
    private String companyAddress;
    /**
     * 更新时间
     */
    private String lastUpdateTime;
    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 主要联系人
     */
    private String primaryContactPerson;

    /**
     * 加入时间
     */
    private String joinDate;

    /**
     * 退出时间
     */
    private String quiteDate;

    /**
     * 所有制类型
     */
    private String ownershipPattern;

    /**
     * 场所
     */
    private String companyPlace;

    /**
     * 注册时间
     */
    private String registrationDate;

    /**
     * 注册资本
     */
    private String registrationAssets;

    public String getRegistrationAssets() {
        return registrationAssets;
    }

    public void setRegistrationAssets(String registrationAssets) {
        this.registrationAssets = registrationAssets;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getCompanyPlace() {
        return companyPlace;
    }

    public void setCompanyPlace(String companyPlace) {
        this.companyPlace = companyPlace;
    }

    public String getOwnershipPattern() {
        return ownershipPattern;
    }

    public void setOwnershipPattern(String ownershipPattern) {
        this.ownershipPattern = ownershipPattern;
    }

    public String getQuiteDate() {
        return quiteDate;
    }

    public void setQuiteDate(String quiteDate) {
        this.quiteDate = quiteDate;
    }

    /**
     * 主要联系人联系方式
     */
    private String primaryContactInfo;

    public String getPrimaryContactInfo() {
        return primaryContactInfo;
    }

    public void setPrimaryContactInfo(String primaryContactInfo) {
        this.primaryContactInfo = primaryContactInfo;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getPrimaryContactPerson() {
        return primaryContactPerson;
    }

    public void setPrimaryContactPerson(String primaryContactPerson) {
        this.primaryContactPerson = primaryContactPerson;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
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

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }
}
