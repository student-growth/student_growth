package com.info.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.info.annotation.SQLAlias;
import lombok.Data;
import lombok.ToString;

/**
 * @author : yue
 * @Date : 2020/7/10 / 21:49
 */
@Data
@TableName("score")
@ToString
public class ScoreEntity {


    @SQLAlias(name = "course_id")
    private String courseId;

    @SQLAlias(name = "class_id")
    private String classId;

    @SQLAlias(name = "course_type")
    private String courseType;

    @SQLAlias(name = "course_name")
    private String courseName;


    private String semester;

    /**
     * 最总折算成绩
     */
    private String score;


    private String credit;

    private String point;

    /**
     * 平时成绩
     */
    @SQLAlias(name="daily_score")
    private String dailyScore;

    /**
     * 期末成绩
     */
    @SQLAlias(name = "final_score")
    private String finalScore;

    @SQLAlias(name = "rebuild_score")
    private String rebuildScore;

    @SQLAlias(name = "retest_score")
    private String retestScore;

}
