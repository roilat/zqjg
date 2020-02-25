package cn.roilat.cqzqjg.admin.pub.controller;

import cn.roilat.cqzqjg.common.utils.StringUtils;
import cn.roilat.cqzqjg.core.http.HttpResult;
import cn.roilat.cqzqjg.core.page.PageResult;
import cn.roilat.cqzqjg.services.system.model.SysPubMaterial;
import cn.roilat.cqzqjg.services.system.sevice.SysPubMaterialService;
import com.alibaba.fastjson.JSON;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpMassNews;
import me.chanjar.weixin.mp.bean.material.*;
import me.chanjar.weixin.mp.bean.result.WxMpMassUploadResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * @description: 永久素材
 * @author: liujing
 * @create: 2019-12-05 17:44
 **/

@RestController
@RequestMapping("/wx/material")
public class WxMpMaterialController {
    private final WxMpService wxMpService;
    private final SysPubMaterialService sysPubMaterialService;

    private static Logger logger = LoggerFactory.getLogger(WxMpMaterialController.class);

    public WxMpMaterialController(WxMpService wxMpService, SysPubMaterialService sysPubMaterialService) {
        this.wxMpService = wxMpService;
        this.sysPubMaterialService = sysPubMaterialService;
    }


    /**
     * 上传素材到腾讯
     *
     * @param mediaType 分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
     * @throws WxErrorException
     * @throws IOException
     */
    @PostMapping("/uploadtowx/{mediaType}")
    public HttpResult uploadMaterialToWx(HttpServletRequest httpServletRequest, @PathVariable String mediaType) {
        HttpResult httpResult = new HttpResult();
        try {
            List<MultipartFile> uploadFiles = ((MultipartHttpServletRequest) httpServletRequest).getFiles("uploadFile");
            String realPath = ResourceUtils.getURL("classpath:").getPath() + "upload/";
            //如果文件夹不存在，则创建该文件夹
            File dir = new File(realPath);
            if (!dir.isDirectory()) {
                dir.mkdirs();
            }
            for (MultipartFile uploadFile : uploadFiles) {
                String filename = uploadFile.getOriginalFilename();
                filename = System.currentTimeMillis() + filename.substring(filename.lastIndexOf("."));
                File fileServer = new File(dir, filename);
                uploadFile.transferTo(fileServer);
                WxMpMaterial wxMaterial = new WxMpMaterial();
                wxMaterial.setFile(fileServer);
                wxMaterial.setName(filename);
                if (WxConsts.MediaFileType.VIDEO.equals(mediaType)) {
                    wxMaterial.setVideoTitle("title");
                    wxMaterial.setVideoIntroduction("test video description");
                }
                //上传到腾讯
                WxMpMaterialUploadResult res = wxMpService.getMaterialService().materialFileUpload(mediaType, wxMaterial);
                Map<String, Object> materialInfo = new HashMap<>();
                materialInfo.put("media_id", res.getMediaId());
                materialInfo.put("length", fileServer.length());
                materialInfo.put("filename", fileServer.getName());
                logger.info("上传素材收到微信响应: " + res.toString());
                if (WxConsts.MediaFileType.IMAGE.equals(mediaType) || WxConsts.MediaFileType.THUMB.equals(mediaType)) {
                    SysPubMaterial sysPubMaterial = new SysPubMaterial();
                    sysPubMaterial.setMediaId(filename);
                    sysPubMaterial.setMaterialId(res.getMediaId());
                    sysPubMaterial.setUrl(res.getUrl());
                    sysPubMaterial.setType(mediaType);
                    sysPubMaterial.setCreateTime(new Date());
                    sysPubMaterial.setPath(realPath + filename);
                    //保存素材到数据库
                    sysPubMaterialService.save(sysPubMaterial);
                }
                if (WxConsts.MediaFileType.THUMB.equals(mediaType)) {
                    SysPubMaterial sysPubMaterial = new SysPubMaterial();
                    sysPubMaterial.setMediaId(filename);
                    sysPubMaterial.setMaterialId(res.getMediaId());
                    sysPubMaterial.setUrl(res.getUrl());
                    sysPubMaterial.setType(dir + mediaType);
                    sysPubMaterial.setCreateTime(new Date());
                    //保存素材到数据库
                    sysPubMaterialService.save(sysPubMaterial);
                }
                httpResult.setMsg("上传素材到腾讯成功");
            }
        } catch (WxErrorException e) {
            logger.error("上传素材到腾讯失败: " + e.getMessage());
            httpResult.setMsg("上传素材到腾讯失败: " + e.getMessage());
        } catch (FileNotFoundException e) {
            logger.error("上传素材到本地失败: " + e.getMessage());
            httpResult.setMsg("上传素材到本地失败: " + e.getMessage());
        } catch (IOException e) {
            logger.error("上传素材到本地失败: " + e.getMessage());
            httpResult.setMsg("上传素材到本地失败: " + e.getMessage());
        }
        return httpResult;
    }

    /**
     * 分页获取图文素材
     *
     * @param offset 素材的该偏移位
     * @param count  返回素材的数量
     */
    @GetMapping("/getMaterial/{offset}/{count}")
    public String getMaterial(@PathVariable Integer offset, @PathVariable Integer count) {
        try {
            WxMpMaterialNewsBatchGetResult wxMpMaterialNewsBatchGetResult = wxMpService.getMaterialService().materialNewsBatchGet(offset, count);
            logger.info("素材总数：" + wxMpMaterialNewsBatchGetResult.getTotalCount() + " 素材的该偏移位: " + wxMpMaterialNewsBatchGetResult.getItemCount());
            for (WxMpMaterialNewsBatchGetResult.WxMaterialNewsBatchGetNewsItem wxMaterialNewsBatchGetNewsItem : wxMpMaterialNewsBatchGetResult.getItems()) {
                logger.info("mediaId: " + wxMaterialNewsBatchGetNewsItem.getMediaId() + " updateTime: " + wxMaterialNewsBatchGetNewsItem.getUpdateTime() + "content:" + wxMaterialNewsBatchGetNewsItem.getContent().toJson());
                return wxMaterialNewsBatchGetNewsItem.toString();
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return "获取失败";
    }


    /**
     * 分页获取多媒体素材
     *
     * @param type     分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
     * @param pageNum  最大支持20条,微信限制
     * @param pageSize 当前页
     */
    @GetMapping("/materialFileBatchGet/{type}/{pageNum}/{pageSize}")
    public HttpResult materialFileBatchGet(@PathVariable String type, @PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        List<SysPubMaterial> list = new ArrayList<>();
        PageResult pageResult = new PageResult();
        try {
            if (pageNum == 0) {
                pageNum = 1;
            }
            if (pageSize == 0) {
                pageSize = 10;
            }
            if (pageSize > 20) {
                pageSize = 20;
            }
            //偏移量
            Integer offset = (pageNum - 1) * pageSize;
            WxMpMaterialFileBatchGetResult wxMpMaterialFileBatchGetResult = wxMpService.getMaterialService().materialFileBatchGet(type, offset, pageSize);
            logger.info("素材总数：" + wxMpMaterialFileBatchGetResult.getTotalCount() + " 素材的该偏移位: " + wxMpMaterialFileBatchGetResult.getItemCount());
            int totalPage = (wxMpMaterialFileBatchGetResult.getTotalCount() + pageSize - 1) / pageSize;
            for (WxMpMaterialFileBatchGetResult.WxMaterialFileBatchGetNewsItem wxMaterialFileBatchGetNewsItem : wxMpMaterialFileBatchGetResult.getItems()) {
                logger.info("mediaId: " + wxMaterialFileBatchGetNewsItem.getMediaId() +
                        " updateTime: " + wxMaterialFileBatchGetNewsItem.getUpdateTime() +
                        "name:" + wxMaterialFileBatchGetNewsItem.getName() +
                        "url: " + wxMaterialFileBatchGetNewsItem.getUrl());
                SysPubMaterial sysPubMaterial = new SysPubMaterial();
                sysPubMaterial.setMediaId(wxMaterialFileBatchGetNewsItem.getMediaId());
                sysPubMaterial.setUrl(wxMaterialFileBatchGetNewsItem.getUrl());
                sysPubMaterial.setLastUpdateTime(new Date());
                sysPubMaterialService.update(sysPubMaterial);
                list.add(sysPubMaterialService.selectByMediaId(sysPubMaterial.getMediaId()));
            }

            pageResult.setPageNum(pageNum);
            pageResult.setPageSize(pageSize);
            pageResult.setTotalPages(totalPage);
            pageResult.setTotalSize(wxMpMaterialFileBatchGetResult.getTotalCount());
            pageResult.setContent(list);

        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return HttpResult.ok("请求成功", pageResult);
    }


    /**
     * 删除材料
     *
     * @param id
     * @return
     */
    @GetMapping("/delMaterial/{id}")
    public HttpResult delMaterial(HttpServletRequest httpServletRequest, @PathVariable Long id) {
        HttpResult httpResult = new HttpResult();
        Boolean flag;
        try {
            SysPubMaterial sysPubMaterial = sysPubMaterialService.findById(id);
            if (null != sysPubMaterial) {
                flag = wxMpService.getMaterialService().materialDelete(sysPubMaterial.getMediaId());
                if (flag) {
                    logger.info("mediaId: " + sysPubMaterial.getMediaId() + " 删除成功!");
                    sysPubMaterialService.deleteById(sysPubMaterial.getId());
                    String path = sysPubMaterial.getPath();
                    if (!StringUtils.isBlank(path)) {
                        //删除本地文件
                        String filePath = sysPubMaterial.getPath();
                        File file = new File(filePath);
                        if (file.exists()) {
                            file.delete();
                        }
                    }
                    httpResult = HttpResult.ok("删除成功");
                } else {
                    logger.info("mediaId: " + sysPubMaterial.getMediaId() + " 删除失败!");
                    httpResult = HttpResult.error("删除失败");
                }
            } else {
                logger.info("id: +" + id + "查找数据库为空，删除失败!");
                httpResult = HttpResult.error("删除失败");
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return httpResult;
    }


    /**
     * 获取素材数量
     */
    @GetMapping("/getmaterialcount")
    public void getMaterialCount() {
        WxMpMaterialCountResult wxMpMaterialCountResult = null;
        try {
            wxMpMaterialCountResult = wxMpService.getMaterialService().materialCount();
            logger.info("voiceCount: " + wxMpMaterialCountResult.getVoiceCount() +
                    "videoCount: " + wxMpMaterialCountResult.getVoiceCount() +
                    "imageCount: " + wxMpMaterialCountResult.getImageCount() +
                    "newsCount: " + wxMpMaterialCountResult.getNewsCount());
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }


    /**
     * 上传图文消息
     *
     * @return
     */
    @PostMapping("/materialNewsUpload")
    public Object[][] materialNewsUpload(@RequestBody WxMpMassNews wxMpMassNews) {
        Object[][] messages = new Object[4][];
        try {
            WxMpMassUploadResult massUploadResult = wxMpService.getMassMessageService().massNewsUpload(wxMpMassNews);
            messages[3] = new Object[]{WxConsts.MassMsgType.MPNEWS, massUploadResult.getMediaId()};
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return messages;
    }


    public static void main(String[] args) {

        WxMpMassNews news = new WxMpMassNews();
        WxMpMassNews.WxMpMassNewsArticle article1 = new WxMpMassNews.WxMpMassNewsArticle();
        article1.setTitle("小邓");
        article1.setContent("星期天");
        article1.setThumbMediaId("u8AX6wiuCq2_Srp_l3JG4GeRVcM8rCq2EAOihyUiJ2E");
        news.addArticle(article1);

        WxMpMassNews.WxMpMassNewsArticle article2 = new WxMpMassNews.WxMpMassNewsArticle();
        article2.setTitle("吃饭");
        article2.setContent("的事情黄掉了");
        article2.setThumbMediaId("u8AX6wiuCq2_Srp_l3JG4GeRVcM8rCq2EAOihyUiJ2E");
        article2.setShowCoverPic(true);
        article2.setAuthor("小邓");
        article2.setContentSourceUrl("www.baidu.com");
        article2.setDigest("吃饭黄了");
        news.addArticle(article2);

        logger.info(JSON.toJSONString(news));
    }

}

