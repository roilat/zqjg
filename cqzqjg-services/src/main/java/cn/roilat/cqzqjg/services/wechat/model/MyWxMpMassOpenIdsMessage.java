package cn.roilat.cqzqjg.services.wechat.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

/**
 * @program: weixin-java-mp-demo-springboot
 * @description: 图文消息对象
 * @author: liujing
 **/
public class MyWxMpMassOpenIdsMessage implements Serializable {

    /**
     * openid列表，最多支持10,000个
     */
    private List<String> touser = new ArrayList<>();

    /**
     * <pre>
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
    private Map<String, String> mpnews = new HashMap<>();


    public String toJson() {
        return WxMpGsonBuilder.create().toJson(this);
    }


    /**
     * 文章被判定为转载时，是否继续进行群发操作。
     */
    private boolean send_ignore_reprint = false;

    public static void main(String[] args) {
        MyWxMpMassOpenIdsMessage myWxMpMassOpenIdsMessage = new MyWxMpMassOpenIdsMessage();
        myWxMpMassOpenIdsMessage.mpnews.put("media_id", "UdKH6vEPHzI-u6qeboaat2h_dmUSs-Cy1M2GkRtIAOH-_Xs6uqSAB5ePIimbZICG");
        myWxMpMassOpenIdsMessage.setMsgtype("mpnews");
        System.out.println(JSON.toJSONString(myWxMpMassOpenIdsMessage));

    }

	public List<String> getTouser() {
		return touser;
	}

	public void setTouser(List<String> touser) {
		this.touser = touser;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public Map<String, String> getMpnews() {
		return mpnews;
	}

	public void setMpnews(Map<String, String> mpnews) {
		this.mpnews = mpnews;
	}

	public boolean isSend_ignore_reprint() {
		return send_ignore_reprint;
	}

	public void setSend_ignore_reprint(boolean send_ignore_reprint) {
		this.send_ignore_reprint = send_ignore_reprint;
	}

}
