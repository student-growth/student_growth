package com.info.dto;

import lombok.Data;

/**
 * @author : yue
 * @Date : 2020/7/10 / 20:59
 * 分数 DTO
 * 查询分数
 */
@Data
public class ScoreDTO {

    private String courseId;

    private String courseName;

    /**
     * 期末成绩
     */
    private Double finalScore;

    /**
     * 平时成绩
     */
    private Double dailyScore;

    /**
     * 折算成绩-来源平时成绩和期末成绩
     */
    private  Double score;

    /**
     * 学分
     */
    private Double credit;

    /**
     * 绩点
     */
    private Double point;

    /**
     * 补考成绩
     */
    private Double resetScore;

    /**
     * 重修成绩
     */
    private Double rebuildScore;


    public ScoreDTO(){
        //todo get score
    }

}

