package com.info.controller;

import com.info.common.ReturnData;
import com.info.common.ReturnValue;
import com.info.common.sysenum.StateMsg;
import com.info.dto.ApplyDTO;
import com.info.dto.ScholarshipDTO;
import com.info.formbean.ScholarshipFormBean;
import com.info.service.ApplyService;
import com.info.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public ReturnData<String> submitApply(@RequestParam("file") MultipartFile file,
                                          @RequestParam("formData") String formData,
                                          @RequestParam("studentId") String userId,
                                          @RequestParam("applyId") String applyId,
                                          @RequestParam("formTemp") String formTemp,
                                          @RequestParam("applyName") String applyName,
                                          @RequestParam("score") int score) {
        ReturnData<String> result = new ReturnData<>();
        try {
            ApplyDTO applyDTO = new ApplyDTO();
            applyDTO.setApplyId(applyId);
            applyDTO.setStudentId(userId);
            applyDTO.setFormData(formData);
            applyDTO.setFormTemp(formTemp);
            applyDTO.setApplyName(applyName);
            applyDTO.setScore(score);
            String data = stuService.submitApply(applyDTO, file);
            result.setData(data);

        } catch (Exception e) {
            result.setStateMsg(StateMsg.StateMsg_500);
            result.setSysError(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/submit_no_image",method = RequestMethod.POST)
    public ReturnData<String> submitApplyWithoutImage(@RequestBody ApplyDTO applyDTO){
        ReturnData<String> result = new ReturnData<>();
        try {
            String data = stuService.submitApply(applyDTO, null);
            result.setData(data);
        } catch (Exception e) {
            result.setError(e);
            e.printStackTrace();
        }
        return result;
    }
    //撤销申请
    @RequestMapping(value = "/cancel", method = RequestMethod.GET)
    public ReturnData<String> cancelApply(@RequestParam("id") String id) {

        ReturnData<String> result = new ReturnData<>();
        try {
            String data = applyService.cancelApply(id);
            result.setData(data);
        } catch (Exception e) {
            result.setError(e);
            e.printStackTrace();
        }
        return result;
    }


    //修改申请内容
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public ReturnData<String> modifyApply(@RequestParam("file") MultipartFile file,
                                          @RequestParam("formData") String formData,
                                          @RequestParam("id") String id) {
        ReturnData<String> result = new ReturnData<>();
        try {
            String data = applyService.modifyApply(id, formData, file);
            result.setData(data);
        } catch (Exception e) {
            result.setError(e);
            e.printStackTrace();
        }
        return result;
    }
    @RequestMapping(value = "modify_no_image",method = RequestMethod.POST)
    public ReturnData<String> modifyApply(@RequestBody ApplyDTO applyDTO) {
        ReturnData<String> result = new ReturnData<>();
        try {
            String data = applyService.modifyApply(applyDTO.getId(), applyDTO.getFormData(), null);
            result.setData(data);
        } catch (Exception e) {
            result.setError(e);
            e.printStackTrace();
        }
        return result;
    }


    //提交奖学金申请
    @RequestMapping(value = "/scholarship", method = RequestMethod.POST)
    public ReturnData<String> applyScholarship(@RequestBody ScholarshipFormBean formBean) {
        ReturnData<String> result = new ReturnData<>();
        try {
            String data = applyService.applyScholarship(formBean);
            result.setData(data);
        } catch (Exception e) {
            result.setError(e);
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/scholarship_list",method = RequestMethod.POST)
    public ReturnValue<ScholarshipDTO> getScholarshipList(@RequestBody ScholarshipFormBean formBean) {
        ReturnValue<ScholarshipDTO> result = new ReturnValue<>();
        try {
            List<ScholarshipDTO> data = applyService.queryScholarship(formBean.getStudentId(),
                    formBean.getApplyName(),formBean.getSemester());
            result.setList(data);
        } catch (Exception e) {
            result.setError(e);
            e.printStackTrace();
        }
        return result;
    }
}
