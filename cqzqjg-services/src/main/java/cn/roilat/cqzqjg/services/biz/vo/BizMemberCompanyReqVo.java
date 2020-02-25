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
	 * 公司名称&联系人
	 */
	private String searchText;

	private String companyName;
	private String primaryContactPerson;
	private String createBy;

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

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

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

}
