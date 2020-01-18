package cn.roilat.cqzqjg.services.biz.vo;

/**
 * @program: zqjg
 * @description: 审核请求vo
 * @author: liujing
 * @create: 2020-01-17 16:23
 **/
public class VerifyReqVo {
    /**
     * 审批状态(0待审核,1审核不通过2审核通过)
     */
    private String approveStatus;

    /**
     * 审核说明
     */
    private String approveDesc;

    /**
     * 会员单位id
     */
    private String companyId;

    /**
     * 会员单位名称
     */
    private String companyName;

    /**
     * 用户id
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    public String getApproveDesc() {
        return approveDesc;
    }

    public void setApproveDesc(String approveDesc) {
        this.approveDesc = approveDesc;
    }
}
