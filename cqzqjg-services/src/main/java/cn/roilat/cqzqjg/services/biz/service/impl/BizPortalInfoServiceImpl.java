package cn.roilat.cqzqjg.services.biz.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.roilat.cqzqjg.services.biz.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.roilat.cqzqjg.core.page.MybatisPageHelper;
import cn.roilat.cqzqjg.core.page.PageRequest;
import cn.roilat.cqzqjg.core.page.PageResult;

import cn.roilat.cqzqjg.services.biz.model.BizPortalInfo;
import cn.roilat.cqzqjg.services.biz.dao.BizPortalInfoMapper;
import cn.roilat.cqzqjg.services.biz.service.BizPortalInfoService;

/**
 * ---------------------------
 * 首页的显示信息表 (BizPortalInfoServiceImpl)
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2020-01-01 23:34:40
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@Service
public class BizPortalInfoServiceImpl implements BizPortalInfoService {

    @Autowired
    private BizPortalInfoMapper bizPortalInfoMapper;

    @Override
    public int save(BizPortalInfo record) {
        if (record.getId() == null || record.getId() == 0) {
            return bizPortalInfoMapper.add(record);
        }
        return bizPortalInfoMapper.update(record);
    }

    @Override
    public int delete(BizPortalInfo record) {
        return bizPortalInfoMapper.delete(record.getId());
    }

    @Override
    public int delete(List<BizPortalInfo> records) {
        for (BizPortalInfo record : records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public BizPortalInfo findById(Long id) {
        return bizPortalInfoMapper.findById(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, bizPortalInfoMapper);
    }

    @Override
    public HomePageVo findHomePage() {
        List<BizPortalInfo> bizPortalInfos = bizPortalInfoMapper.findPage();
        HomePageVo homePageVo = new HomePageVo();
        List<AboutUs> aboutUsList = new ArrayList<>();
        List<ChooseUs> chooseUsList = new ArrayList<>();
        List<Culture> cultureList = new ArrayList<>();
        List<DealScene> dealSceneList = new ArrayList<>();
        List<Main> mainList = new ArrayList<>();
        List<News> newsList = new ArrayList<>();
        for (BizPortalInfo bizPortalInfo : bizPortalInfos) {
            String typeCode = bizPortalInfo.getTypeCode();
            if (null != typeCode && !typeCode.equals("")) {
                switch (typeCode) {
                    case "main":
                        Main main = new Main();
                        List<String> picPathMainList = new ArrayList<>();
                        main.setMainDesc(bizPortalInfo.getMainDesc());
                        String[] picsMain = bizPortalInfo.getPicturePath().split(",");
                        for (String s : picsMain) {
                            picPathMainList.add(s);
                        }
                        main.setPicturePath(picPathMainList);
                        main.setSubDesc(bizPortalInfo.getSubDesc());
                        main.setTitle(bizPortalInfo.getTitle());
                        mainList.add(main);
                        break;
                    case "aboutUs":
                        AboutUs aboutUs = new AboutUs();
                        List<String> picPathAbout = new ArrayList<>();
                        String[] picsAboutUs = bizPortalInfo.getPicturePath().split(",");
                        for (String s : picsAboutUs) {
                            picPathAbout.add(s);
                        }
                        aboutUs.setContent(bizPortalInfo.getContent());
                        aboutUs.setPicturePath(picPathAbout);
                        aboutUsList.add(aboutUs);
                        break;
                    case "chooseUs":
                        ChooseUs chooseUs = new ChooseUs();
                        List<String> picPathChooseUs = new ArrayList<>();
                        String[] picsChooseUs = bizPortalInfo.getPicturePath().split(",");
                        for (String s : picsChooseUs) {
                            picPathChooseUs.add(s);
                        }
                        chooseUs.setContent(bizPortalInfo.getContent());
                        chooseUs.setPicturePath(picPathChooseUs);
                        chooseUsList.add(chooseUs);
                        break;
                    case "culture":
                        Culture culture = new Culture();
                        List<String> picPathCulture = new ArrayList<>();
                        String[] picsCulture = bizPortalInfo.getPicturePath().split(",");
                        for (String s : picsCulture) {
                            picPathCulture.add(s);
                        }
                        culture.setContent(bizPortalInfo.getContent());
                        culture.setPicturePath(picPathCulture);
                        culture.setTitle(bizPortalInfo.getTitle());
                        cultureList.add(culture);
                        break;
                    case "news":
                        News news = new News();
                        List<String> picPathNews = new ArrayList<>();
                        String[] picsNews = bizPortalInfo.getPicturePath().split(",");
                        for (String s : picsNews) {
                            picPathNews.add(s);
                        }
                        news.setContent(bizPortalInfo.getContent());
                        news.setPicturePath(picPathNews);
                        newsList.add(news);
                        break;
                    default:
                }
            }
        }
        homePageVo.setAboutUs(aboutUsList);
        homePageVo.setChooseUs(chooseUsList);
        homePageVo.setCulture(cultureList);
        homePageVo.setDealScene(dealSceneList);
        homePageVo.setMain(mainList);
        homePageVo.setNews(newsList);
        return homePageVo;
    }
}
