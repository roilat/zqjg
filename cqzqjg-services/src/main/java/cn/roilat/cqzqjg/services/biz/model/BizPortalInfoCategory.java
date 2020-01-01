package cn.roilat.cqzqjg.services.biz.model;

/**
 * ---------------------------
 * 首页信息分类表 (BizPortalInfoCategory)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2020-01-01 23:34:40
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public class BizPortalInfoCategory {

	/** main顶部信息,aboutUs关于我们,news消息资讯,dealScene成交现场,chooseUs选择我们,culture企业文化 */
	private String typeCode;
	/** 内容类型：1多个图片2图片文字3只有文字 */
	private String contentType;
	/** 是否删除  -1：已删除  0：正常 */
	private Integer delFlag;

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

}