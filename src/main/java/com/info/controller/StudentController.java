package com.info.controller;

import com.info.common.ReturnValue;
import com.info.entity.Student;
import com.info.formbean.PageBean;
import com.info.service.StudentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : yue
 * @Date : 2020/7/8 / 20:19
 */
@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    private StudentService stuService;

    @ApiOperation("分页获取学生列表")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ReturnValue<Student> getStudentList(@RequestBody PageBean page){
        ReturnValue<Student> res=null;
        try{
            res = stuService.getStudentList(page);

        }catch (Exception e){
            res.setMsg(e.getMessage());
        }
        return res;
    }
}
