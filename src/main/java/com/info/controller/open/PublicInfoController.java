package com.info.controller.open;

import com.info.common.ReturnData;
import com.info.common.ReturnValue;
import com.info.common.sysenum.StateMsg;
import com.info.dto.StudentInfoDto;
import com.info.entity.Student;
import com.info.formbean.PageBean;
import com.info.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : yue
 * @Date : 2020/9/4 / 10:10
 */
@RestController
@RequestMapping("/open")
public class PublicInfoController {

    @Autowired
    public StudentService studentService;

    @RequestMapping("/studentInfo")
    public ReturnValue<StudentInfoDto> getStudentInfo(@RequestParam("grade") String grade,
                                                      @RequestParam("id") String id){
        ReturnValue<StudentInfoDto>  result = new ReturnValue<>();
        try {
            List<StudentInfoDto> data = studentService.getStudentList(id, grade);
            result.setList(data);

        }catch (Exception e){
            result.setStateMsg(StateMsg.StateMsg_500);
            result.setSysError(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

}
