package com.info.controller;

import com.info.common.ReturnData;
import com.info.common.ReturnValue;
import com.info.common.sysenum.StateMsg;
import com.info.dto.AbilityDTO;
import com.info.dto.TotalScoreDTO;
import com.info.formbean.CommonFormBean;
import com.info.mapper.extra.ApplyExtraMapper;
import com.info.service.ScoreService;
import com.info.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author : yue
 * @Date : 2020/9/6 / 11:18
 */
@RestController
@RequestMapping("/score")
public class ScoreController {

//    @Autowired
//    private StudentService studentService;

    @Autowired
    private ScoreService scoreService;


    //获取综合成绩列表
    @RequestMapping(value = "/totalScore", method = RequestMethod.GET)
    public ReturnData<Map<String, List<TotalScoreDTO>>> getTotalScore(@RequestParam("id") String studentId,
                                                                      @RequestParam("semester") String semester) {

        ReturnData<Map<String, List<TotalScoreDTO>>> result = new ReturnData<>();
        try {
            Map<String, List<TotalScoreDTO>> data = scoreService.getTotalScore(studentId, semester);
            result.setData(data);

        } catch (Exception e) {
            e.printStackTrace();
            result.setSysError(e.getMessage());

        }
        return result;
    }

    //获取综合成绩-用于奖学金查询
    @RequestMapping(value = "/comprehensive_score",method = RequestMethod.POST)
    public ReturnData<AbilityDTO> getComprehensiveScore(@RequestBody CommonFormBean formBean){
        ReturnData<AbilityDTO> result = new ReturnData<>();
        try{
            AbilityDTO data = scoreService.getAbilityScore(formBean.getStudentId(), formBean.getSemester());
            result.setData(data);
        }catch (Exception e){
            result.setError(e);
            e.printStackTrace();
        }


        return result;
    }

}
