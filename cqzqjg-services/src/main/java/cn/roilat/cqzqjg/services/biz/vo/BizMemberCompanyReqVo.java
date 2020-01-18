package cn.roilat.cqzqjg.services.biz.vo;

import cn.roilat.cqzqjg.core.page.PageRequest;

/**
 * @program: zqjg
 * @description: 会员单位请求VO
 * @author: liujing
 * @create: 2020-01-16 11:09
 **/
public class BizMemberCompanyReqVo extends PageRequest {
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 联系人
     */
    private String primaryContactPerson;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPrimaryContactPerson() {
        return primaryContactPerson;
    }

    public void setPrimaryContactPerson(String primaryContactPerson) {
        this.primaryContactPerson = primaryContactPerson;
    }
}
