package com.info.dto;

import com.info.annotation.ExcelColumn;
import lombok.Data;

/**
 * @author : yue
 * 2020/7/10 / 20:59
 * 分数 DTO
 */
@Data
public class ScoreDTO {

    @ExcelColumn(name = "课程号")
    private String courseId;

    @ExcelColumn(name = "课程名称")
    private String courseName;

    @ExcelColumn(name = "学期")
    private String semester;

    @ExcelColumn(name = "课程类型")
    private String courseType;

    @ExcelColumn(name = "期末成绩")
    private String finalScore;

    @ExcelColumn(name = "平时成绩")
    private Double dailyScore;

    @ExcelColumn(name = "折算成绩")
    private  String score;

    @ExcelColumn(name = "学分")
    private String credit;

    @ExcelColumn(name = "绩点")
    private String point;

    @ExcelColumn(name="补考成绩")
    private String retestScore;

    @ExcelColumn(name = "重修成绩")
    private String rebuildScore;

}

