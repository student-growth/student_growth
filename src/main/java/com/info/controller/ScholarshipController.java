package com.info.controller;

import com.info.common.StateMsg;
import com.info.entity.ApplyScholarship;
import com.info.service.ScholarshipService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tenghao Deng
 * @create 2020-07-11 20:52
 **/
@RestController
@RequestMapping(value = "/scholarship")
public class ScholarshipController {

    @Autowired
    private ScholarshipService schService;

    @ApiOperation("返回学生奖学金申请结果")
    @RequestMapping( method = RequestMethod.GET)
    public ApplyScholarship getScholarshipResult(String student_id){
        ApplyScholarship res = null;
        res = schService.getScholarshipResult(student_id);
        return res;
    }
}
