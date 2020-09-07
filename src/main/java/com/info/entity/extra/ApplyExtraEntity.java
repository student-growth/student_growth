package com.info.entity.extra;

import com.info.entity.ApplyEntity;
import lombok.Data;

import java.util.Date;

/**
 * @author : yue
 *  2020/9/6 / 9:50
 * 用于综合成绩的查询
 */
@Data
public class ApplyExtraEntity extends ApplyEntity {


    private String menuId;

    /**
     * 六大类分类名称
     */
    private String sort;


    private Date modifyTime;

    /**
     * 申请通过获得分数
     */
    private int score;
}
