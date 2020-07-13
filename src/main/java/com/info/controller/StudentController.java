package com.info.controller;

import com.info.common.ReturnData;
import com.info.common.ReturnValue;
import com.info.common.sysenum.StateMsg;
import com.info.dto.ScoreDTO;
import com.info.dto.StudentInfoDto;
import com.info.entity.Student;
import com.info.formbean.PageBean;
import com.info.formbean.StudentFormBean;
import com.info.service.StudentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : yue
 * @Date : 2020/7/8 / 20:19
 */
@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    private StudentService stuService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ReturnData<StudentInfoDto> login(@RequestBody StudentFormBean info){
        ReturnData<StudentInfoDto> result = new ReturnData<>();
        if(null==info ||info.getId().isEmpty() || info.getPassword().isEmpty()){
            result.setStateMsg(StateMsg.StateMsg_101);
            return result;
        }
        try{
            StudentInfoDto student = stuService.getStudentInfo(info.getId(), info.getPassword());
            result.setData(student);

        }catch (Exception e){
            result.setSysError(e.getMessage());
            result.setStateMsg(StateMsg.StateMsg_500);
            e.printStackTrace();
        }
        return result;
    }


    @ApiOperation("修改密码")
    @RequestMapping(value = "/modifyPwd",method = RequestMethod.POST)
    public ReturnData<String> modifyPassword(@RequestBody StudentFormBean info){
        ReturnData<String> result = new ReturnData<>();
        if(info.getId()==null || info.getPassword()==null ||
                info.getNewPassword()==null){
            result.setStateMsg(StateMsg.StateMsg_101);
            return result;
        }
        try{
            String data = stuService.updatePassword(info.getId(), info.getPassword(), info.getNewPassword());
            result.setData(data);
        }catch (Exception e){
            e.printStackTrace();
            result.setStateMsg(StateMsg.StateMsg_500);
            result.setSysError(e.getMessage());
        }
        return result;
    }


    @ApiOperation("分页获取学生列表")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ReturnValue<Student> getStudentList(@RequestBody PageBean page){
        ReturnValue<Student> res=new ReturnValue<>();
        try{
            res = stuService.getStudentList(page);

        }catch (Exception e){
            res.setStateMsg(StateMsg.StateMsg_500);
            res.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return res;
    }


    @ApiOperation("查询学生成绩")
    @RequestMapping(value = "/score",method = RequestMethod.GET)
    public ReturnValue<ScoreDTO> getScoreById(@RequestParam("id") String id){
        ReturnValue<ScoreDTO> result=new ReturnValue<>();
        //check param
        if(null==id || id.isEmpty()){
            result.setStateMsg(StateMsg.StateMsg_101);
            return result;
        }
        try{
            List<ScoreDTO> list = stuService.queryScoreById(id);
            result.setList(list);
            result.setStateMsg(StateMsg.StateMsg_200);
        }catch (Exception e){
            result.setSystemerrormsg(e.getMessage());
            result.setStateMsg(StateMsg.StateMsg_500);
            e.printStackTrace();
        }
        return result;
    }



}
