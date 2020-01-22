package cn.roilat.cqzqjg.admin.pub.controller;

import com.alibaba.fastjson.JSON;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 永久素材
 * @author: liujing
 * @create: 2019-12-05 17:44
 **/

@RestController
@RequestMapping("/wx/material")
public class WxMpMaterialController {
    private final WxMpService wxMpService;

    private static Logger logger = LoggerFactory.getLogger(WxMpMaterialController.class);

    public WxMpMaterialController(WxMpService wxMpService) {
        this.wxMpService = wxMpService;
    }

    /**
     * 上传素材
     * 可不用该接口直接上传到腾讯即可
     *
     * @param mediaType 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
     * @throws WxErrorException
     * @throws IOException
     */
    @GetMapping("/upload/{mediaType}")
    public void uploadMaterial(HttpServletRequest httpServletRequest, @PathVariable String mediaType) {
        //1、设置文件到本地的文件夹位置
        String realPath = "";
        //2、获取上传的文件名为uploadFile的list，这个文件名是upload页面上input的name
        List<MultipartFile> uploadFiles = ((MultipartHttpServletRequest) httpServletRequest).getFiles("uploadFile");
        //3、开始将文件移动到目标路径下
        try {
            realPath = ResourceUtils.getURL("classpath:").getPath() + "upload/";
            //如果文件夹不存在，则创建该文件夹
            File dir = new File(realPath);
            if (!dir.isDirectory()) {
                dir.mkdirs();
            }
            for (MultipartFile uploadFile : uploadFiles) {
                String filename = uploadFile.getOriginalFilename();
                filename = System.currentTimeMillis() + filename.substring(filename.lastIndexOf("."));
                File fileServer = new File(dir, filename);
                String path = realPath + filename;
                uploadFile.transferTo(fileServer);
                //TODO 保存图片到信息到数据库
                logger.debug("文件路径: " + path + " 媒体文件类型: " + mediaType);
            }
        } catch (Exception e) {
            logger.error("上传文件时异常！", e);
        }
    }

    /**
     * 上传素材到腾讯
     *
     * @param mediaType
     * @throws WxErrorException
     * @throws IOException
     */
    @GetMapping("/uploadtowx/{mediaType}")
    public void uploadMaterialToWx(HttpServletRequest httpServletRequest, @PathVariable String mediaType) {
        try {
            List<MultipartFile> uploadFiles = ((MultipartHttpServletRequest) httpServletRequest).getFiles("uploadFile");
            String realPath = ResourceUtils.getURL("classpath:").getPath() + "upload/";
            /* 如果文件夹不存在，则创建该文件夹 */
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
                if (WxConsts.MediaFileType.IMAGE.equals(mediaType) || WxConsts.MediaFileType.THUMB.equals(mediaType)) {
                    //TODO 保存腾讯图片url,mediaId到数据库
                    logger.debug("上传图片: " + res.getUrl());
                }
                if (WxConsts.MediaFileType.THUMB.equals(mediaType)) {
                    //TODO 保存腾讯图片url,mediaId到数据库
                    logger.debug("上传图片: " + res.getUrl());
                }
                Map<String, Object> materialInfo = new HashMap<>();
                materialInfo.put("media_id", res.getMediaId());
                materialInfo.put("length", fileServer.length());
                materialInfo.put("filename", fileServer.getName());
                logger.debug("上传素材收到微信响应: " + res.toString());
            }
        } catch (WxErrorException e) {
            logger.error("上传素材到腾讯失败: " + e.getMessage());
        } catch (FileNotFoundException e) {
            logger.error("上传素材到本地失败: " + e.getMessage());
        } catch (IOException e) {
            logger.error("上传素材到本地失败: " + e.getMessage());
        }
    }


    /**
     * 上传临时素材到腾讯
     *
     * @param mediaType
     * @throws WxErrorException
     * @throws IOException
     */
    @GetMapping("/mediaUpload/{mediaType}")
    public void mediaUpload(HttpServletRequest httpServletRequest, @PathVariable String mediaType) {
        try {
            List<MultipartFile> uploadFiles = ((MultipartHttpServletRequest) httpServletRequest).getFiles("uploadFile");
            String realPath = ResourceUtils.getURL("classpath:").getPath() + "upload/";
            /* 如果文件夹不存在，则创建该文件夹 */
            File dir = new File(realPath);
            if (!dir.isDirectory()) {
                dir.mkdirs();
            }
            for (MultipartFile uploadFile : uploadFiles) {
                String filename = uploadFile.getOriginalFilename();
                filename = System.currentTimeMillis() + filename.substring(filename.lastIndexOf("."));
                File fileServer = new File(dir, filename);
                uploadFile.transferTo(fileServer);
                //上传到腾讯
                WxMediaUploadResult res = wxMpService.getMaterialService().mediaUpload(mediaType, fileServer);

                Map<String, Object> materialInfo = new HashMap<>();
                materialInfo.put("url", res.getUrl());
                materialInfo.put("type", res.getType());
                materialInfo.put("mediaId", res.getMediaId());
                materialInfo.put("thumbMediaId", res.getThumbMediaId());
                materialInfo.put("createdAt", res.getCreatedAt());

                logger.debug("上传临时素材收到微信响应: " + res.toString());
            }
        } catch (WxErrorException e) {
            logger.error("上传临时素材到腾讯失败: " + e.getMessage());
        } catch (FileNotFoundException e) {
            logger.error("上传临时素材到本地失败: " + e.getMessage());
        } catch (IOException e) {
            logger.error("上传临时素材到本地失败: " + e.getMessage());
        }
    }


    /**
     * 上传图文消息内的图片获取URL
     *
     * @param mediaType
     * @throws WxErrorException
     * @throws IOException
     */
    @GetMapping("/mediaImgUpload")
    public void mediaImgUpload(HttpServletRequest httpServletRequest, @PathVariable String mediaType) {
        try {
            List<MultipartFile> uploadFiles = ((MultipartHttpServletRequest) httpServletRequest).getFiles("uploadFile");
            String realPath = ResourceUtils.getURL("classpath:").getPath() + "upload/";
            /* 如果文件夹不存在，则创建该文件夹 */
            File dir = new File(realPath);
            if (!dir.isDirectory()) {
                dir.mkdirs();
            }
            for (MultipartFile uploadFile : uploadFiles) {
                String filename = uploadFile.getOriginalFilename();
                filename = System.currentTimeMillis() + filename.substring(filename.lastIndexOf("."));
                File fileServer = new File(dir, filename);
                uploadFile.transferTo(fileServer);
                //上传到腾讯
                WxMediaImgUploadResult res = wxMpService.getMaterialService().mediaImgUpload(fileServer);

                Map<String, Object> materialInfo = new HashMap<>();
                materialInfo.put("url", res.getUrl());

                logger.debug("上传临时素材收到微信响应: " + res.toString());
            }
        } catch (WxErrorException e) {
            logger.error("上传临时素材收到到腾讯失败: " + e.getMessage());
        } catch (FileNotFoundException e) {
            logger.error("上传临时素材收到到本地失败: " + e.getMessage());
        } catch (IOException e) {
            logger.error("上传临时素材收到到本地失败: " + e.getMessage());
        }
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
            logger.debug("素材总数：" + wxMpMaterialNewsBatchGetResult.getTotalCount() + " 素材的该偏移位: " + wxMpMaterialNewsBatchGetResult.getItemCount());
            for (WxMpMaterialNewsBatchGetResult.WxMaterialNewsBatchGetNewsItem wxMaterialNewsBatchGetNewsItem : wxMpMaterialNewsBatchGetResult.getItems()) {
                logger.debug("mediaId: " + wxMaterialNewsBatchGetNewsItem.getMediaId() + " updateTime: " + wxMaterialNewsBatchGetNewsItem.getUpdateTime() + "content:" + wxMaterialNewsBatchGetNewsItem.getContent().toJson());
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
     * @param type   分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
     * @param offset
     * @param count
     */
    @GetMapping("/materialFileBatchGet/{type}/{offset}/{count}")
    public void materialFileBatchGet(@PathVariable String type, @PathVariable Integer offset, @PathVariable Integer count) {
        try {
            WxMpMaterialFileBatchGetResult wxMpMaterialFileBatchGetResult = wxMpService.getMaterialService().materialFileBatchGet(type, offset, count);
            logger.debug("素材总数：" + wxMpMaterialFileBatchGetResult.getTotalCount() + " 素材的该偏移位: " + wxMpMaterialFileBatchGetResult.getItemCount());
            for (WxMpMaterialFileBatchGetResult.WxMaterialFileBatchGetNewsItem wxMaterialFileBatchGetNewsItem : wxMpMaterialFileBatchGetResult.getItems()) {
                logger.debug("mediaId: " + wxMaterialFileBatchGetNewsItem.getMediaId() +
                        " updateTime: " + wxMaterialFileBatchGetNewsItem.getUpdateTime() +
                        "name:" + wxMaterialFileBatchGetNewsItem.getName() +
                        "url: " + wxMaterialFileBatchGetNewsItem.getUrl());
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }


    @GetMapping("/delMaterial/{mediaId}")
    public void delMaterial(@PathVariable String mediaId) {
        Boolean flag = null;
        try {
            flag = wxMpService.getMaterialService().materialDelete(mediaId);
            if (flag) {
                logger.debug("mediaId: " + mediaId + " 删除成功!");
            } else {
                logger.debug("mediaId: " + mediaId + " 删除失败!");
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }


    @GetMapping("/getmaterialcount")
    public void getMaterialCount() {
        WxMpMaterialCountResult wxMpMaterialCountResult = null;
        try {
            wxMpMaterialCountResult = wxMpService.getMaterialService().materialCount();
            logger.debug("voiceCount: " + wxMpMaterialCountResult.getVoiceCount() +
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

        logger.debug(JSON.toJSONString(news));
    }


}

