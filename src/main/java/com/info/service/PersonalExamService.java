package com.info.service;

import com.info.common.sysenum.StateMsg;
import com.info.dto.PsychologyDTO;
import com.info.entity.PsychologyEntity;
import com.info.entity.converter.Converter;
import com.info.mapper.PsychologyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : yue
 * @Date : 2020/8/27 / 15:36
 * 个人测试
 */
@Service
public class PersonalExamService {

    @Autowired
    private PsychologyMapper psyMapper;


    private Converter<PsychologyEntity> converter =new Converter<>();

    //存储测试结果
    public String savePsyTestResult(String studentId,String testName,String result){

        PsychologyEntity entity = new PsychologyEntity();
        entity.setStudentId(studentId);
        entity.setTestName(testName);
        entity.setResult(result);

        int res = psyMapper.insertOne(entity);
        return res==1? StateMsg.StateMsg_200.getMsg():StateMsg.StateMsg_500.getMsg();
    }

    //查询测试结果
    public List<PsychologyDTO> getPsyTestList(String id,String name){
        List<PsychologyEntity> data = psyMapper.selectById(id,name);
        return data.stream().map(item->converter.clone(item,PsychologyDTO.class))
                .collect(Collectors.toList());
    }
}
