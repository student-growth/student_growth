package com.info.controller;

import com.info.common.ReturnData;
import com.info.common.ReturnValue;
import com.info.common.sysenum.StateMsg;
import com.info.dto.QuesDTO;
import com.info.service.QuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : yue
 * @Date : 2020/7/20 / 23:17
 */
@RestController
@RequestMapping("/question")
public class QuestController {

    @Autowired
    private QuesService quesService;

    @PostMapping("/save")
    public ReturnData<String> saveQuestion(@RequestBody QuesDTO question){
        ReturnData<String> result = new ReturnData<>();
        try{
            String data = quesService.saveQuestion(question);
            result.setData(data);
        }catch (Exception e){
            result.setStateMsg(StateMsg.StateMsg_500);
            result.setSysError(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/getFeedback")
    public ReturnData<String>  getFeedback(@RequestParam("id") String id){
        ReturnData<String> result = new ReturnData<>();
        try{
            String feedBack = quesService.getFeedBack(id);
            result.setData(feedBack);
        }catch (Exception e){
            result.setSysError(e.getMessage());
            result.setStateMsg(StateMsg.StateMsg_500);
            e.printStackTrace();
        }

        return result;
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ReturnValue<QuesDTO> getQuesListByStuID(@RequestParam("studentId") String studentId,
                                                   @RequestParam("size") int size){
        ReturnValue<QuesDTO> result = new ReturnValue<>();
        try{
            List<QuesDTO> data = quesService.getQuesList(studentId,size);
            result.setList(data);
        }catch (Exception e){
            result.setStateMsg(StateMsg.StateMsg_500);
            result.setSysError(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    //PC端页面查询
    @GetMapping("/getQues")
    public ReturnValue<QuesDTO> getQuesList(@RequestParam("size") int size){
        ReturnValue<QuesDTO> result = new ReturnValue<>();
        try{
            List<QuesDTO> list = quesService.getQuesList(size);
            result.setList(list);
        }catch (Exception e){
            result.setStateMsg(StateMsg.StateMsg_500);
            result.setSystemerrormsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }


}
