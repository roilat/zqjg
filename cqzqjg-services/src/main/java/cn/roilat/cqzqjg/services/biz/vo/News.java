package cn.roilat.cqzqjg.services.biz.vo;

import lombok.Data;

import java.util.List;

/**
 * @program: zqjg
 * @description: 消息资讯
 * @author: liujing
 * @create: 2020-01-04 22:08
 **/
@Data
public class News {
    private List<String> picturePath;
    private String content;


}
