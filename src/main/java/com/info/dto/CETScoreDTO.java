package com.info.dto;

import lombok.Data;

/**
 * @author : yue  2020/8/26 / 13:44
 */
@Data
public class CETScoreDTO {

    /**
     * 准考证号
     */
    private String adminId;

    private String cetLevel;

    private int cetListen;

    private int cetRead;

    private int cetWrite;

    private int totalScore;

    private boolean isAbsent;

    private boolean isViolate;

}
