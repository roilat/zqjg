package cn.roilat.cqzqjg.services.biz.vo;

import lombok.Data;

import java.util.List;

/**
 * @program: zqjg
 * @description: 成交现场
 * @author: liujing
 * @create: 2020-01-04 22:09
 **/
@Data
public class DealScene {
    private List<String> picturePath;
    private String title;
    private String mainDesc;
}
