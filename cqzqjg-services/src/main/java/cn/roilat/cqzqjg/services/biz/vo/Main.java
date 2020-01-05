package cn.roilat.cqzqjg.services.biz.vo;

import lombok.Data;

import java.util.List;

/**
 * @program: zqjg
 * @description: 顶部信息
 * @author: liujing
 * @create: 2020-01-04 22:08
 **/

@Data
public class Main {

    private List<String> picturePath;
    private String title;
    private String mainDesc;
    private String subDesc;


}
