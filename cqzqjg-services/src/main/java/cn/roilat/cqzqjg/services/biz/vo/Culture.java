package cn.roilat.cqzqjg.services.biz.vo;

import lombok.Data;

import java.util.List;

/**
 * @program: zqjg
 * @description: 企业文化
 * @author: liujing
 * @create: 2020-01-04 22:09
 **/
@Data
public class Culture {
    private List<String> picturePath;
    private String content;
    private String title;
}
