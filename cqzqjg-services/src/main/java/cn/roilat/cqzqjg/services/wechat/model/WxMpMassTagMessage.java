package cn.roilat.cqzqjg.services.wechat.model;

import lombok.Data;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 按标签群发的消息.
 */
@Data
public class WxMpMassTagMessage implements Serializable {
    private static final long serialVersionUID = -6625914040986749286L;

    private Map<String, Boolean> filter = new HashMap<>();

    private Map<String, String> mpnews = new HashMap<>();

    /**
     * <pre>
     * 消息类型.
     * 请使用
     * {@link WxConsts.MassMsgType#IMAGE}
     * {@link WxConsts.MassMsgType#MPNEWS}
     * {@link WxConsts.MassMsgType#TEXT}
     * {@link WxConsts.MassMsgType#MPVIDEO}
     * {@link WxConsts.MassMsgType#VOICE}
     * 如果msgtype和media_id不匹配的话，会返回系统繁忙的错误
     * </pre>
     */
    private String msgtype;

    private Integer send_ignore_reprint;


    public WxMpMassTagMessage() {
        super();
    }

    public String toJson() {
        return WxMpGsonBuilder.create().toJson(this);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Map<String, Boolean> getFilter() {
        return filter;
    }

    public void setFilter(Map<String, Boolean> filter) {
        this.filter = filter;
    }

    public Map<String, String> getMpnews() {
        return mpnews;
    }

    public void setMpnews(Map<String, String> mpnews) {
        this.mpnews = mpnews;
    }


    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public Integer getSend_ignore_reprint() {
        return send_ignore_reprint;
    }

    public void setSend_ignore_reprint(Integer send_ignore_reprint) {
        this.send_ignore_reprint = send_ignore_reprint;
    }

    public static void main(String[] args) {
        WxMpMassTagMessage massMessage = new WxMpMassTagMessage();
        massMessage.filter.put("is_to_all", true);
        massMessage.mpnews.put("media_id", "1234567890abc");
        massMessage.setMsgtype(WxConsts.MassMsgType.MPNEWS);
        massMessage.setSend_ignore_reprint(0);
        System.out.println(massMessage.toJson());
    }

}
