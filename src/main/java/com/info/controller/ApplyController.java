package com.info.controller;

import com.info.common.ReturnData;
import com.info.common.sysenum.StateMsg;
import com.info.dto.ApplyDTO;
import com.info.service.ApplyService;
import com.info.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : yue
 * @Date : 2020/9/4 / 19:40
 */
@RestController
@RequestMapping("/apply")
public class ApplyController {

    @Autowired
    private ApplyService applyService;

    @Autowired
    private StudentService stuService;


    //提交申请
    @RequestMapping(value = "/submit",method = RequestMethod.POST)
    public ReturnData<String> submitApply(@RequestParam("file") MultipartFile file,
                                          @RequestParam("formData") String formData,
                                          @RequestParam("userId") String userId,
                                          @RequestParam("applyId") String applyId,
                                          @RequestParam("formTemp") String formTemp,
                                          @RequestParam("applyName") String applyName,
                                          @RequestParam("score") int score){
        ReturnData<String> result = new ReturnData<>();
        try{
            ApplyDTO applyDTO = new ApplyDTO();
            applyDTO.setApplyId(applyId);
            applyDTO.setStudentId(userId);
            applyDTO.setFormData(formData);
            applyDTO.setFormTemp(formTemp);
            applyDTO.setApplyName(applyName);
            applyDTO.setScore(score);
            String data = stuService.submitApply(applyDTO, file);
            result.setData(data);

        }catch (Exception e){
            result.setStateMsg(StateMsg.StateMsg_500);
            result.setSysError(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    //撤销申请
    @RequestMapping(value = "/cancel",method = RequestMethod.GET)
    public  ReturnData<String> cancelApply(@RequestParam("id") String id){

        ReturnData<String> result = new ReturnData<>();
        try{
            String data = applyService.cancelApply(id);
            result.setData(data);
        }catch (Exception e){
            result.setStateMsg(StateMsg.StateMsg_500);
            result.setSysError(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }


    //修改申请内容
    @RequestMapping(value = "/modify",method = RequestMethod.POST)
    public ReturnData<String> modifyApply(@RequestParam("file") MultipartFile file,
                                          @RequestParam("formData") String formData,
                                          @RequestParam("id") String id){
        ReturnData<String> result = new ReturnData<>();
        try{
            String data = applyService.modifyApply(id, formData,file);
            result.setData(data);
        }catch (Exception e){
            result.setSysError(e.getMessage());
            result.setStateMsg(StateMsg.StateMsg_500);
            e.printStackTrace();
        }
        return result;
    }

}
