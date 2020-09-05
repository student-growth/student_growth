package com.info.controller;


import com.info.common.ReturnData;
import com.info.common.ReturnValue;
import com.info.common.sysenum.StateMsg;
import com.info.dto.ApplyProjectDTO;
import com.info.service.ProjectService;
import com.info.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author : yue
 * @Date : 2020/9/3 / 11:20
 */
@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private StudentService stuService;


    @Autowired
    private ProjectService projectService;



    //获取申请列表表单
    @RequestMapping(value = "/applyList", method = RequestMethod.GET)
    public ReturnData<Map<String, List<ApplyProjectDTO>>> getApplyMenu(
            @RequestParam("sort") String sort) {
        ReturnData<Map<String, List<ApplyProjectDTO>>> result = new ReturnData<>();
        try {
            Map<String, List<ApplyProjectDTO>> data = stuService.getApplyList(sort);
            result.setData(data);
        } catch (Exception e) {
            result.setStateMsg(StateMsg.StateMsg_500);
            result.setSysError(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }



    @RequestMapping(value="/fuzzyQuery",method = RequestMethod.GET)
    public ReturnValue<ApplyProjectDTO> fuzzyQuery(@RequestParam("keyword") String keyword){
        ReturnValue<ApplyProjectDTO> result = new ReturnValue<>();
        try{
            List<ApplyProjectDTO> data = projectService.fuzzyQuery(keyword);
            result.setList(data);
        }catch (Exception e){
            result.setSysError(e.getMessage());
            result.setStateMsg(StateMsg.StateMsg_500);
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/getFormTemp", method = RequestMethod.GET)
    public ReturnData<String> getFormTemp(@RequestParam("menuId") String menuId,
                                          @RequestParam("projectId") String projectId) {
        ReturnData<String> result = new ReturnData<>();
        try{
            String data = stuService.getFormTemp(menuId,projectId);
            result.setData(data);
        }catch (Exception e){
            result.setSysError(e.getMessage());
            result.setStateMsg(StateMsg.StateMsg_500);
            e.printStackTrace();
        }
        return result;
    }


}
