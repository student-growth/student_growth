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
    public ApplyScholarship getScholarshipResult(String student_id) {
        return schMapper.idSelectScholarship(student_id);
    }

    @ApiOperation("修改奖学金的审核状态")
    public String changeScholarshipStatus(String student_id, String status) {
        try {
            schMapper.idChangeStatus(student_id, status);
        } catch (Exception e) {
            return "修改失败";
        }
        return "修改成功";
    }
}
