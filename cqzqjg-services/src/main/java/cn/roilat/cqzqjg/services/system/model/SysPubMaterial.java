package cn.roilat.cqzqjg.services.system.model;

/**
 * @program: zqjg
 * @description: 腾讯素材
 * @author: liujing
 * @create: 2020-02-09 15:19
 **/
public class SysPubMaterial extends BaseModel {
    /**
     * 素材本地id
     */
    private String materialId;
    /**
     * 素材腾讯id
     */
    private String mediaId;
    /**
     * 素材腾讯访问url
     */
    private String url;

    private String type;

    /**
     * 素材本地路径
     */
    private String path;

    /**
     * 图文消息内容
     */
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
