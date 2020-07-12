package com.info.controller;

import com.alibaba.fastjson.JSONObject;
import com.info.entity.ApplyScholarship;
import com.info.service.ScholarshipService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping( value = "query",method = RequestMethod.GET)
    public ApplyScholarship getScholarshipResult(String student_id){
        ApplyScholarship res = null;
        res = schService.getScholarshipResult(student_id);
        return res;
    }

    @ApiOperation("修改奖学金的审核状态")
    @RequestMapping(value = "changeStatus",method = RequestMethod.POST)
    public String changeScholarshipStatus(@RequestBody JSONObject jsonObject) {
        String student_id = jsonObject.getString("student_id");
        String status = jsonObject.getString("status");
        return schService.changeScholarshipStatus(student_id, status);
    }
}
