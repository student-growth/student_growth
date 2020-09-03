package com.info.entity;

import com.info.annotation.SQLAlias;
import lombok.Data;

/**
 * @author : yue
 * @Date : 2020/8/26 / 13:48
 */
@Data
public class CETScoreEntity {

    @SQLAlias(name = "student_id")
    private String studentId;

    @SQLAlias(name = "admin_id")
    private String adminId;

    @SQLAlias(name="cet_level")
    private String cetLevel;

    @SQLAlias(name="cet_listen")
    private int cetListen;

    @SQLAlias(name="cet_read")
    private int cetRead;

    @SQLAlias(name = "cet_write")
    private int cetWrite;

    @SQLAlias(name = "total_score")
    private int totalScore;

    @SQLAlias(name = "is_absent")
    private boolean isAbsent;

    @SQLAlias(name = "is_violate")
    private boolean isViolate;


}
