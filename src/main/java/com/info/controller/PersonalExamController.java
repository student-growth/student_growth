package com.info.controller;

import com.info.common.ReturnData;
import com.info.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : yue
 * @Date : 2020/8/27 / 15:28
 */
@RestController
@RequestMapping("/psy_test")
public class PersonalExamController {

    @Autowired
    private StudentService studentService;


    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ReturnData<String> getTestContent(@RequestParam("id") String id){
        //todo  获取心理测试题目
        return null;
    }
}
