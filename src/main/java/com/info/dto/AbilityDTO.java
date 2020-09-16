package com.info.dto;

import lombok.Data;

/**
 * @author : yue
 * @since : 2020/9/16
 * 综合能力分数 DTO
 */
@Data
public class AbilityDTO {

    private String studentId;



    //专业素质
    private double profession;

    //英语平均成绩
    private double englishScore;

    //体育成绩
    private double sport;

    //基本项总分
    private double baseScore;

    //基本项目排名
    private int baseScoreRank;

    //综合能力成绩
    private double abilityScore;

    //综合能力排名
    private int abilityScoreRank;

}
