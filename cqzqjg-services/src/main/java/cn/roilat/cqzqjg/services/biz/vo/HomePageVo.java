package cn.roilat.cqzqjg.services.biz.vo;

import lombok.Data;

import java.util.List;

/**
 * @program: zqjg
 * @description: 首页vo
 * @author: liujing
 * @create: 2020-01-04 22:25
 **/
@Data
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
}
