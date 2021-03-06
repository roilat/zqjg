package cn.roilat.cqzqjg.admin.pub.controller;

import cn.roilat.cqzqjg.common.utils.StringUtils;
import cn.roilat.cqzqjg.core.http.HttpResult;
import cn.roilat.cqzqjg.core.page.PageResult;
import cn.roilat.cqzqjg.services.system.model.SysPubMaterial;
import cn.roilat.cqzqjg.services.system.sevice.SysPubMaterialService;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpMassNews;
import me.chanjar.weixin.mp.bean.material.*;
import me.chanjar.weixin.mp.bean.result.WxMpMassUploadResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${windows.public}")
    private String windowsImagesPath;

    @Value("${linux.public}")
    private String linuxImagesPath;

    /**
     * 未发送
     */
    private static final Integer SEND_FLAG_FALSE = 0;

    private static Logger logger = LoggerFactory.getLogger(WxMpMaterialController.class);

    /**
     * 临时
     */
    private final static Integer TEMPORARY = 0;
    /**
     * 永久
     */
    private final static Integer PERPETUAL = 1;

    /**
     * 单图文
     */
    private final static Integer SINGLE = 0;

    /**
     * 多图文
     */
    private final static Integer MULTIPLE = 1;

    /**
     * 图文消息内图片
     */
    private final static String CONTENT_PIC = "pic";

    /**
     * 消息错误码
     */
    private final static Integer RES_ERROR = 500;

    /**
     * 消息成功
     */
    private final static Integer RES_SUCESS = 500;

    public WxMpMaterialController(WxMpService wxMpService, SysPubMaterialService sysPubMaterialService) {
        this.wxMpService = wxMpService;
        this.sysPubMaterialService = sysPubMaterialService;
    }


    /**
     * 上传图文消息内的图片获取URL
     *
     * @throws WxErrorException
     * @throws IOException
     */
    @PostMapping("/mediaImgUpload")
    public HttpResult mediaImgUpload(HttpServletRequest httpServletRequest) {
        HttpResult httpResult = new HttpResult();
        //List<Map<String, Object>> respList = new ArrayList<>();
        Map<String, Object> materialInfo = new HashMap<>(16);
        materialInfo.put("src", "");

        try {
            List<MultipartFile> uploadFiles = ((MultipartHttpServletRequest) httpServletRequest).getFiles("file");
            if (null == uploadFiles || uploadFiles.size() == 0) {
                logger.error("上传素材为空");
                httpResult.setMsg("上传素材为空");
                httpResult.setCode(RES_ERROR);
                return httpResult;
            }
            String realPath;
            if (isOSLinux()) {
                realPath = linuxImagesPath;
            } else {
                realPath = windowsImagesPath;
            }
            //如果文件夹不存在，则创建该文件夹
            File dir = new File(realPath);
            if (!dir.isDirectory()) {
                dir.mkdirs();
            }
            for (MultipartFile uploadFile : uploadFiles) {
                //检查文件大小
                //文件大小MB
                double fileSize = uploadFile.getSize() / 1048576;
                if (fileSize > 2) {
                    logger.error("上传素材超过最大2MB");
                    httpResult.setMsg("上传素材超过最大2MB");
                    httpResult.setCode(RES_ERROR);
                    materialInfo.put("src", "");
                    materialInfo.put("title", "");
                    httpResult.setData(materialInfo);
                    return httpResult;
                }
            }
            for (MultipartFile uploadFile : uploadFiles) {
                String fileName = uploadFile.getOriginalFilename();
                String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
                fileName = System.currentTimeMillis() + "_" + new Random().nextInt(1000) + fileSuffix;
                File fileServer = new File(dir, fileName);
                uploadFile.transferTo(fileServer);
                //上传图文消息内图片到腾讯
                WxMediaImgUploadResult res = wxMpService.getMaterialService().mediaImgUpload(fileServer);
                logger.info("上传素材收到微信响应: " + res.toString());
                SysPubMaterial sysPubMaterial = new SysPubMaterial();
                String materialId = fileName.substring(0, fileName.indexOf(".")).replace("_", "");
                sysPubMaterial.setMaterialId(materialId);
                sysPubMaterial.setUrl(res.getUrl());
                sysPubMaterial.setType(CONTENT_PIC);
                sysPubMaterial.setCreateTime(new Date());
                sysPubMaterial.setPath(realPath + fileName);
                //保存素材到数据库
                sysPubMaterialService.save(sysPubMaterial);
                materialInfo.put("src", res.getUrl());
                materialInfo.put("title", fileName);
                httpResult.setData(materialInfo);
                //respList.add(materialInfo);
            }
        } catch (WxErrorException e) {
            logger.error("上传素材到腾讯失败: " + e.getMessage());
            httpResult.setMsg("上传素材到腾讯失败: " + e.getMessage());
            httpResult.setCode(RES_ERROR);
        } catch (FileNotFoundException e) {
            logger.error("上传素材到本地失败: " + e.getMessage());
            httpResult.setMsg("上传素材到本地失败: " + e.getMessage());
            httpResult.setCode(RES_ERROR);
        } catch (IOException e) {
            logger.error("上传素材到本地失败: " + e.getMessage());
            httpResult.setMsg("上传素材到本地失败: " + e.getMessage());
            httpResult.setCode(RES_ERROR);
        }
        //layui插件返回0
        httpResult.setCode(0);
        httpResult.setMsg("图文消息内图片上传成功");
        //httpResult.setData(respList);
        return httpResult;

    }


    /**
     * 上传素材到腾讯
     *
     * @param mediaType  分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
     * @param uploadType 0-临时 1-永久
     * @throws WxErrorException
     * @throws IOException
     */
    @PostMapping("/uploadtowx/{mediaType}/{uploadType}")
    public HttpResult uploadMaterialToWx(HttpServletRequest httpServletRequest, @PathVariable String mediaType, @PathVariable Integer uploadType) {
        HttpResult httpResult = new HttpResult();
        List<Map<String, Object>> respList = new ArrayList<>();
        Map<String, Object> materialInfo = new HashMap<>(16);
        try {
            List<MultipartFile> uploadFiles = ((MultipartHttpServletRequest) httpServletRequest).getFiles("file");
            if (null == uploadFiles || uploadFiles.size() == 0) {
                logger.error("上传素材为空");
                httpResult.setMsg("上传素材为空");
                httpResult.setCode(RES_ERROR);
                return httpResult;
            }
            String realPath;
            if (isOSLinux()) {
                realPath = linuxImagesPath;
            } else {
                realPath = windowsImagesPath;
            }
            //如果文件夹不存在，则创建该文件夹
            File dir = new File(realPath);
            if (!dir.isDirectory()) {
                dir.mkdirs();
            }

            for (MultipartFile uploadFile : uploadFiles) {
                //检查文件大小
                double fileSize = uploadFile.getSize() / 1048576;
                if (fileSize > 2) {
                    logger.error("上传素材超过最大2MB");
                    httpResult.setMsg("上传素材超过最大2MB");
                    httpResult.setCode(RES_SUCESS);
                    return httpResult;
                }
            }

            for (MultipartFile uploadFile : uploadFiles) {
                String fileName = uploadFile.getOriginalFilename();
                String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
                fileName = System.currentTimeMillis() + "_" + new Random().nextInt(1000) + fileSuffix;
                File fileServer = new File(dir, fileName);
                uploadFile.transferTo(fileServer);
                WxMpMaterial wxMaterial = new WxMpMaterial();
                wxMaterial.setFile(fileServer);
                wxMaterial.setName(fileName);
                if (WxConsts.MediaFileType.VIDEO.equals(mediaType)) {
                    wxMaterial.setVideoTitle("title");
                    wxMaterial.setVideoIntroduction("test video description");
                }
                if (PERPETUAL.equals(uploadType)) {
                    //上传永久素材
                    WxMediaUploadResult res = wxMpService.getMaterialService().mediaUpload(mediaType, fileServer);
                    materialInfo.put("filename", fileServer.getName());
                    logger.info("上传素材收到微信响应: " + res.toString());
                    if (WxConsts.MediaFileType.IMAGE.equals(mediaType) || WxConsts.MediaFileType.THUMB.equals(mediaType)) {
                        SysPubMaterial sysPubMaterial = new SysPubMaterial();
                        if (!StringUtils.isBlank(res.getMediaId())) {
                            sysPubMaterial.setMediaId(res.getMediaId());
                            materialInfo.put("media_id", res.getMediaId());
                        }
                        if (!StringUtils.isBlank(res.getThumbMediaId())) {
                            sysPubMaterial.setMediaId(res.getThumbMediaId());
                            materialInfo.put("media_id", res.getThumbMediaId());
                        }
                        String materialId = fileName.substring(0, fileName.indexOf(".")).replace("_", "");
                        sysPubMaterial.setMaterialId(materialId);
                        sysPubMaterial.setUrl(res.getUrl());
                        sysPubMaterial.setType(mediaType);
                        sysPubMaterial.setCreateTime(new Date());
                        sysPubMaterial.setPath(realPath + fileName);
                        //保存素材到数据库
                        sysPubMaterialService.save(sysPubMaterial);
                        httpResult.setMsg("上传腾讯图片成功");
                        respList.add(materialInfo);
                        httpResult.setCode(RES_SUCESS);
                    }
                } else {
                    //上传临时素材到腾讯
                    WxMpMaterialUploadResult res = wxMpService.getMaterialService().materialFileUpload(mediaType, wxMaterial);
                    if (WxConsts.MediaFileType.THUMB.equals(mediaType)) {
                        materialInfo.put("filename", fileServer.getName());
                        SysPubMaterial sysPubMaterial = new SysPubMaterial();
                        sysPubMaterial.setMediaId(res.getMediaId());
                        String materialId = fileName.substring(0, fileName.indexOf(".")).replace("_", "");
                        sysPubMaterial.setMaterialId(materialId);
                        sysPubMaterial.setUrl(res.getUrl());
                        sysPubMaterial.setType(mediaType);
                        sysPubMaterial.setCreateTime(new Date());
                        sysPubMaterial.setPath(realPath + fileName);
                        //保存素材到数据库
                        sysPubMaterialService.save(sysPubMaterial);
                        httpResult.setMsg("上传腾讯临时图片成功");
                        materialInfo.put("media_id", res.getMediaId());
                        respList.add(materialInfo);
                    }
                }
                httpResult.setData(respList);
                httpResult.setCode(RES_SUCESS);
            }
        } catch (WxErrorException e) {
            logger.error("上传素材到腾讯失败: " + e.getMessage());
            httpResult.setMsg("上传素材到腾讯失败: " + e.getMessage());
            httpResult.setCode(RES_ERROR);
        } catch (FileNotFoundException e) {
            logger.error("上传素材到本地失败: " + e.getMessage());
            httpResult.setMsg("上传素材到本地失败: " + e.getMessage());
            httpResult.setCode(RES_ERROR);
        } catch (IOException e) {
            logger.error("上传素材到本地失败: " + e.getMessage());
            httpResult.setMsg("上传素材到本地失败: " + e.getMessage());
            httpResult.setCode(RES_ERROR);
        }
        return httpResult;
    }

    /**
     * 分页获取图文素材（没用上，待删除）
     *
     * @param pageNum  素材的该偏移位
     * @param pageSize 返回素材的数量
     */
    @Deprecated
    @GetMapping("/getMaterial/{pageNum}/{pageSize}")
    public HttpResult getMaterial(@PathVariable Integer
                                          pageNum, @PathVariable Integer pageSize) {
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
            WxMpMaterialNewsBatchGetResult wxMpMaterialNewsBatchGetResult = wxMpService.getMaterialService().materialNewsBatchGet(offset, pageSize);
            logger.info("素材总数：" + wxMpMaterialNewsBatchGetResult.getTotalCount() + " 素材的该偏移位: " + wxMpMaterialNewsBatchGetResult.getItemCount());
            int totalPage = (wxMpMaterialNewsBatchGetResult.getTotalCount() + pageSize - 1) / pageSize;
            List<SysPubMaterial> updateList = new ArrayList<>();
            for (WxMpMaterialNewsBatchGetResult.WxMaterialNewsBatchGetNewsItem wxMaterialFileBatchGetNewsItem : wxMpMaterialNewsBatchGetResult.getItems()) {
                logger.info("mediaId: " + wxMaterialFileBatchGetNewsItem.getMediaId() +
                        " updateTime: " + wxMaterialFileBatchGetNewsItem.getUpdateTime() +
                        " content:" + wxMaterialFileBatchGetNewsItem.getContent());
                SysPubMaterial sysPubMaterial = new SysPubMaterial();
                sysPubMaterial.setMediaId(wxMaterialFileBatchGetNewsItem.getMediaId());
                sysPubMaterial.setMaterialId(String.valueOf(System.currentTimeMillis() + new Random().nextInt(1000)));
                sysPubMaterial.setLastUpdateTime(new Date());
                sysPubMaterial.setType(WxConsts.MassMsgType.MPNEWS);
                sysPubMaterial.setContent(wxMaterialFileBatchGetNewsItem.getContent().toJson());
                sysPubMaterialService.update(sysPubMaterial);
                updateList.add(sysPubMaterial);
                list.add(sysPubMaterialService.selectByMediaId(sysPubMaterial.getMediaId()));
            }
            //批量更新数据

            pageResult.setPageNum(pageNum);
            pageResult.setPageSize(pageSize);
            pageResult.setTotalPages(totalPage);
            pageResult.setTotalSize(wxMpMaterialNewsBatchGetResult.getTotalCount());
            pageResult.setContent(list);
        } catch (WxErrorException e) {
            logger.error("获取图文素材失败: " + e.getMessage());
            return HttpResult.error("获取图文素材失败： " + e.getMessage());
        }
        return HttpResult.ok("请求成功", pageResult);
    }


    /**
     * 分页获取多媒体素材（没用上，待删除）
     *
     * @param type     分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
     * @param pageNum  最大支持20条,微信限制
     * @param pageSize 当前页
     */
    @Deprecated
    @GetMapping("/materialFileBatchGet/{type}/{pageNum}/{pageSize}")
    public HttpResult materialFileBatchGet(@PathVariable String type, @PathVariable Integer
            pageNum, @PathVariable Integer pageSize) {
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
                sysPubMaterial.setType(type);
                sysPubMaterialService.update(sysPubMaterial);
                list.add(sysPubMaterialService.selectByMediaId(sysPubMaterial.getMediaId()));
            }

            pageResult.setPageNum(pageNum);
            pageResult.setPageSize(pageSize);
            pageResult.setTotalPages(totalPage);
            pageResult.setTotalSize(wxMpMaterialFileBatchGetResult.getTotalCount());
            pageResult.setContent(list);

        } catch (WxErrorException e) {
            logger.error("获取多媒体素材失败: " + e.getMessage());
            return HttpResult.error("获取多媒体素材失败： " + e.getMessage());
        }
        return HttpResult.ok("请求成功", pageResult);
    }


    /**
     * 删除材料（没用上，待删除）
     *
     * @param id
     * @return
     */
    @Deprecated
    @GetMapping("/delMaterial/{id}")
    public HttpResult delMaterial(@PathVariable Long id) {
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
     * 获取素材数量（没用上，待删除）
     */
    @Deprecated
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
     * @param wxMpMassNews 图文消息
     * @return
     */
    @PostMapping("/materialNewsUpload")
    public HttpResult materialNewsUpload(@RequestBody WxMpMassNews wxMpMassNews) {
        Object[][] messages = new Object[4][];
        SysPubMaterial sysPubMaterial = new SysPubMaterial();
        try {
            logger.info("收到请求参数: " + wxMpMassNews.toJson());
            // 上传图文消息
            WxMpMassUploadResult massUploadResult = wxMpService.getMassMessageService().massNewsUpload(wxMpMassNews);
            logger.info("收到腾讯请求响应: " + massUploadResult.toString());
            messages[3] = new Object[]{WxConsts.MassMsgType.MPNEWS, massUploadResult.getMediaId()};
            //入库保存图文消息
            sysPubMaterial.setType(WxConsts.MassMsgType.MPNEWS);
            sysPubMaterial.setMediaId(massUploadResult.getMediaId());
            sysPubMaterial.setMaterialId(String.valueOf(System.currentTimeMillis() + +new Random().nextInt(1000)));
            sysPubMaterial.setCreateTime(new Date());
            sysPubMaterial.setContent(wxMpMassNews.toJson());
            sysPubMaterial.setSendFlag(SEND_FLAG_FALSE);
            sysPubMaterialService.save(sysPubMaterial);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return HttpResult.ok("上传成功", sysPubMaterial);
    }

    /**
     * 测试用-微信接口调用清零
     *
     * @param appid
     * @throws WxErrorException
     */
    @Deprecated
    @GetMapping("/clearQuota/{appid}")
    public void clearQuota(@PathVariable String appid) throws WxErrorException {
        wxMpService.clearQuota(appid);
    }

    public static boolean isOSLinux() {
        Properties prop = System.getProperties();
        String os = prop.getProperty("os.name");
        if (os != null && os.toLowerCase().indexOf("linux") > -1) {
            return true;
        } else {
            return false;
        }
    }
}

