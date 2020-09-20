package com.info.controller;

import com.info.common.ReturnData;
import com.info.common.ReturnValue;
import com.info.common.sysenum.StateMsg;
import com.info.dto.PsychologyDTO;
import com.info.formbean.PsyFormBean;
import com.info.service.PersonalExamService;
import com.info.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : yue
 * @since : 2020/8/27 / 15:28
 */
@RestController
@RequestMapping("/psy_test")
public class PersonalExamController {


    @Autowired
    private PersonalExamService examService;

    @RequestMapping(value = "/savePsyResult",method = RequestMethod.POST)
    public ReturnData<String> savePsyResult(@RequestBody PsyFormBean form){
        ReturnData<String> result = new ReturnData<>();
        try{
            String data = examService.savePsyTestResult(form.getId(),
                    form.getTestName(), form.getResult());
            result.setData(data);
        }catch (Exception e){
            result.setSysError(e.getMessage());
            result.setStateMsg(StateMsg.StateMsg_500);
            e.printStackTrace();
        }
        return result;
    }


    @RequestMapping(value = "/getPsyList",method = RequestMethod.GET)
    public ReturnValue<PsychologyDTO> getPsyList(@RequestParam("id") String id,
                                                 @RequestParam("name") String name){
        ReturnValue<PsychologyDTO> result = new ReturnValue<>();
        try{
            List<PsychologyDTO> data = examService.getPsyTestList(id,name);
            result.setList(data);
        }catch (Exception e){
            result.setSysError(e.getMessage());
            result.setStateMsg(StateMsg.StateMsg_500);
            e.printStackTrace();
        }


        return result;
    }

}
