package cn.roilat.cqzqjg.services.biz.vo;

import lombok.Data;

import java.util.List;

/**
 * @program: zqjg
 * @description: 关于我们
 * @author: liujing
 * @create: 2020-01-04 22:08
 **/
@Data
public class AboutUs {
    private List<String> picturePath;
    private String content;
}
