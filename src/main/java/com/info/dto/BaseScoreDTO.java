package com.info.dto;

import lombok.Data;

/**
 * @author : yue
 * @Date : 2020/9/6 / 17:26
 * 基本评价指标 参数和计算参数
 */
@Data
public class BaseScoreDTO {

    //学号
    private String studentId;

    //品德素质分数
    private double moralScore;

    //专业素质分数
    private double professionScore;

    //健康素质
    private double healthScore;

    //基本项总分排名
    private int rank;


    public double getBaseScore(){
        if(this.studentId==null){
            return 0.0;
        }
        //基本评价指标=品德素质*25%+专业素质*60%+身心素质*15%
        return moralScore*0.25+professionScore*0.6+healthScore*15;
    }
}
