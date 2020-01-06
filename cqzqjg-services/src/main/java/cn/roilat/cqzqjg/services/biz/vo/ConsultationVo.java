package cn.roilat.cqzqjg.services.biz.vo;

import cn.roilat.cqzqjg.core.page.PageRequest;

import java.util.Date;

/**
 * @program: zqjg
 * @description: 咨询vo
 * @author: liujing
 * @create: 2020-01-06 17:30
 **/
public class ConsultationVo extends PageRequest {
    private String begTime;
    private String endTime;
    private Date begDate;
    private Date endDate;

    public Date getBegDate() {
        return begDate;
    }

    public void setBegDate(Date begDate) {
        this.begDate = begDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getBegTime() {
        return begTime;
    }

    public void setBegTime(String begTime) {
        this.begTime = begTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
