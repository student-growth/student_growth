package com.info.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author : yue
 * @Date : 2020/9/5 / 17:42
 *
 * 综合成绩返回列表
 */
@Data
public class TotalScoreDTO {

    //申请通过的申请号
    private String id;

    //申请的类型Id
    private String menuId;

    //申请奖项的名称
    private String applyName;

    //获得的分数
    private int score;

    //申请的六大类的分类名称
    private String sort;


    //项目通过时间
    private Date passTime;


}

