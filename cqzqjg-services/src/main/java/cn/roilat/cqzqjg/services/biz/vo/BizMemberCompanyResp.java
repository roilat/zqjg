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
}
