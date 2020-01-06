package cn.roilat.cqzqjg.services.biz.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        List<BizPortalInfo> bizPortalInfos = bizPortalInfoMapper.findHomePage();
        HomePageVo homePageVo = new HomePageVo();
//        List<AboutUs> aboutUsList = new ArrayList<>();
//        List<ChooseUs> chooseUsList = new ArrayList<>();
//        List<Culture> cultureList = new ArrayList<>();
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
                        //aboutUsList.add(aboutUs);
                        homePageVo.setAboutUs(aboutUs);
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
                        //chooseUsList.add(chooseUs);
                        homePageVo.setChooseUs(chooseUs);
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
                        //cultureList.add(culture);
                        homePageVo.setCulture(culture);
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
                    case "dealScene":
                        DealScene dealScene = new DealScene();
                        List<String> picPathdealScene = new ArrayList<>();
                        String[] picsdealScene = bizPortalInfo.getPicturePath().split(",");
                        for (String s : picsdealScene) {
                            picPathdealScene.add(s);
                        }
                        dealScene.setTitle(bizPortalInfo.getTitle());
                        dealScene.setMainDesc(bizPortalInfo.getMainDesc());
                        dealScene.setPicturePath(picPathdealScene);
                        dealSceneList.add(dealScene);
                        break;
                    default:
                }
            }
        }
//        homePageVo.setAboutUs(aboutUsList);
//        homePageVo.setChooseUs(chooseUsList);
//        homePageVo.setCulture(cultureList);
        homePageVo.setDealScene(dealSceneList);
        homePageVo.setMain(mainList);
        homePageVo.setNews(newsList);
        return homePageVo;
    }

    @Override
    public PageResult findNews(ConsultationVo consultationVo) {
        HomePageVo homePageVo = new HomePageVo();
        PageResult pageResult = null;
        String begTime = consultationVo.getBegTime();
        String endTime = consultationVo.getEndTime();
        Date begDate;
        Date endDate;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            begDate = simpleDateFormat.parse("1990-01-01 00:00:00");
            endDate = simpleDateFormat.parse("2099-12-31 23:59:59");
            //开始时间
            if (null != begTime && !"".equals(begTime)) {
                begTime = begTime + " 00:00:00";
                begDate = simpleDateFormat.parse(begTime);
                consultationVo.setBegDate(begDate);
            }
            //结束时间
            if (null != endTime && !"".equals(endTime)) {
                endTime = endTime + " 23:59:59";
                endDate = simpleDateFormat.parse(endTime);
                consultationVo.setEndDate(endDate);
            }

            pageResult = MybatisPageHelper.findPage(consultationVo, bizPortalInfoMapper, "findNewsByTime",begDate,endDate);
            List<BizPortalInfo> bizPortalInfos = (List<BizPortalInfo>) pageResult.getContent();
            List<News> newsList = new ArrayList<>();
            for (BizPortalInfo bizPortalInfo : bizPortalInfos) {
                String typeCode = bizPortalInfo.getTypeCode();
                if (null != typeCode && !typeCode.equals("")) {
                    News news = new News();
                    List<String> picPathNews = new ArrayList<>();
                    String[] picsNews = bizPortalInfo.getPicturePath().split(",");
                    for (String s : picsNews) {
                        picPathNews.add(s);
                    }
                    news.setContent(bizPortalInfo.getContent());
                    news.setPicturePath(picPathNews);
                    Date updateTIme = bizPortalInfo.getLastUpdateTime();
                    if (null != updateTIme) {
                        String updateTimeStr = simpleDateFormat.format(updateTIme);
                        news.setUpdateTime(updateTimeStr.substring(0, updateTimeStr.indexOf(" ")));
                    }
                    newsList.add(news);
                }
            }
            homePageVo.setNews(newsList);
            List<HomePageVo> respList = new ArrayList<>();
            respList.add(homePageVo);
            pageResult.setContent(respList);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return pageResult;
    }
}
