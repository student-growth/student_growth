package com.info.entity;

import com.info.annotation.SQLAlias;
import lombok.Data;

/**
 * @author : yue
 * @since : 2020/9/8 / 15:21
 */
@Data
public class AbilityEntity {

    @SQLAlias(name = "student_id")
    private String studentId;


    private String name;

    private String semester;

    @SQLAlias(name = "moral_base")
    private double moralBase;

    @SQLAlias(name = "moral_extra")
    private double moralExtra;

    @SQLAlias(name = "moral_total")
    private double moralTotal;

    @SQLAlias(name = "moral_rank")
    private int moralRank;

    @SQLAlias(name = "prof_base")
    private double profBase;

    @SQLAlias(name = "prof_extra")
    private double profExtra;

    @SQLAlias(name = "prof_total")
    private double profTotal;

    @SQLAlias(name = "prof_rank")
    private int profRank;

    //身体素质分数==>体育分数
    @SQLAlias(name = "health_score")
    private double healthScore;

    //心理健康指数
    @SQLAlias(name = "psych_score")
    private double psychScore;

    @SQLAlias(name = "health_psych_total")
    private double healthPsychTotal;


    @SQLAlias(name = "base_total")
    private double baseTotal;

    @SQLAlias(name = "base_total_rank")
    private int baseTotalRank;


    private double creative;

    private double profession;

    private double leader;

    private double practice;

    private double special;

    @SQLAlias(name = "total_score")
    private double totalScore;

    @SQLAlias(name = "total_rank")
    private int totalRank;

}
