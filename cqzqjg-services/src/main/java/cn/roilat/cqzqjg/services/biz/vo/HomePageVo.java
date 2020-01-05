package cn.roilat.cqzqjg.services.biz.vo;

import java.util.List;

/**
 * @program: zqjg
 * @description: 首页vo
 * @author: liujing
 * @create: 2020-01-04 22:25
 **/
public class HomePageVo {
    //private List<AboutUs> aboutUs;
    private AboutUs aboutUs;
    //private List<ChooseUs> chooseUs;
    private ChooseUs chooseUs;
    //private List<Culture> culture;
    private Culture culture;
    private List<DealScene> dealScene;
    private List<HomePageVo> homePageVo;
    private List<Main> main;
    private List<News> news;
	public List<AboutUs> getAboutUs() {
		return aboutUs;
	}
	public void setAboutUs(List<AboutUs> aboutUs) {
		this.aboutUs = aboutUs;
	}
	public List<ChooseUs> getChooseUs() {
		return chooseUs;
	}
	public void setChooseUs(List<ChooseUs> chooseUs) {
		this.chooseUs = chooseUs;
	}
	public List<Culture> getCulture() {
		return culture;
	}
	public void setCulture(List<Culture> culture) {
		this.culture = culture;
	}
	public List<DealScene> getDealScene() {
		return dealScene;
	}
	public void setDealScene(List<DealScene> dealScene) {
		this.dealScene = dealScene;
	}
	public List<HomePageVo> getHomePageVo() {
		return homePageVo;
	}
	public void setHomePageVo(List<HomePageVo> homePageVo) {
		this.homePageVo = homePageVo;
	}
	public List<Main> getMain() {
		return main;
	}
	public void setMain(List<Main> main) {
		this.main = main;
	}
	public List<News> getNews() {
		return news;
	}
	public void setNews(List<News> news) {
		this.news = news;
	}
    
}
