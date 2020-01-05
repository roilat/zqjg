package cn.roilat.cqzqjg.services.biz.vo;

import java.util.List;

/**
 * @program: zqjg
 * @description: 消息资讯
 * @author: liujing
 * @create: 2020-01-04 22:08
 **/
public class News {
	private List<String> picturePath;
	private String content;
    private String updateTime;

	public List<String> getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(List<String> picturePath) {
		this.picturePath = picturePath;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
}
