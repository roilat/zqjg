package cn.roilat.cqzqjg.services.biz.vo;

import java.util.List;

/**
 * @program: zqjg
 * @description: 成交现场
 * @author: liujing
 * @create: 2020-01-04 22:09
 **/
public class DealScene {
	private Long id;
    private List<String> picturePath;
    private String title;
    private String mainDesc;
	public List<String> getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(List<String> picturePath) {
		this.picturePath = picturePath;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMainDesc() {
		return mainDesc;
	}
	public void setMainDesc(String mainDesc) {
		this.mainDesc = mainDesc;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
