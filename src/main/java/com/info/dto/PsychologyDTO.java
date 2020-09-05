package com.info.dto;

import lombok.Data;

import java.util.Date;

/**
 *
 * @author : yue
 * @Date : 2020/9/4 / 14:47
 */
@Data
public class PsychologyDTO {

    private String studentId;

   private String testName;

    private String result;

    private Date testTime;
}
