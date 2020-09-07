package com.info.controller;

import com.info.common.ReturnData;
import com.info.common.sysenum.StateMsg;
import com.info.dto.TotalScoreDTO;
import com.info.mapper.extra.ApplyExtraMapper;
import com.info.service.ScoreService;
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
            result.setStateMsg(StateMsg.StateMsg_500);
        }
        return result;
    }

    //获取基本评价指标


}
