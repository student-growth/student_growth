package com.info.service;

import com.info.common.StateMsg;
import com.info.entity.ApplyScholarship;
import com.info.exception.SystemException;
import com.info.mapper.ScholarshipMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Tenghao Deng
 * @create 2020-07-11 20:54
 **/
@Service
public class ScholarshipService {
    @Resource
    private ScholarshipMapper schMapper;

    @ApiOperation("返回学生奖学金申请结果")
    public ApplyScholarship getScholarshipResult(String student_id){
        return schMapper.idSelectScholarship(student_id);
    }
}
