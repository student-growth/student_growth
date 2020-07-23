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

    @ExcelColumn(name = "课程类型")
    private String courseType;

    @ExcelColumn(name = "期末成绩")
    private Double finalScore;

    @ExcelColumn(name = "平时成绩")
    private Double dailyScore;

    @ExcelColumn(name = "折算成绩")
    private  Double score;

    @ExcelColumn(name = "学分")
    private Double credit;

    @ExcelColumn(name = "绩点")
    private Double point;

    @ExcelColumn(name="补考成绩")
    private Double retestScore;

    @ExcelColumn(name = "重修成绩")
    private Double rebuildScore;

}

