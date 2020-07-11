package com.info.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author : yue
 * @Date : 2020/7/10 / 21:49
 */
@Data
@TableName("score")
@ToString
public class ScoreEntity {

    private String courseId;

    private String courseName;

    private String semester;

    private Double score;

    private Double dailyScore;

    private Double finalScore;

    private Double rebuildScore;

    private Double retestScore;

}
