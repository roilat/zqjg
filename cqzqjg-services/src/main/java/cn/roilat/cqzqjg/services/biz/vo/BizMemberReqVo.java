package cn.roilat.cqzqjg.services.biz.vo;

import cn.roilat.cqzqjg.core.page.PageRequest;

/**
 * @program: zqjg
 * @description: 会员请求VO
 * @author: liujing
 * @create: 2020-01-16 22:05
 **/
public class BizMemberReqVo extends PageRequest {

	/**
	 * 公司名称
	 */
	private String companyName;

	/**
	 * 昵称
	 */
	private String nickName;

	/**
	 * 登录名
	 */
	private String loginName;

	/**
	 * 手机号
	 */
	private String phoneNumber;

	private String searchText;

	/**
	 * 审批状态(0待审核,1审核不通过2审核通过)
	 */
	private Integer approveStatus;
	private String createBy;

	public Integer getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

}
