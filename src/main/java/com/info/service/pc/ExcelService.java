package com.info.service.pc;

import com.info.common.sysenum.StateMsg;
import com.info.dto.StudentInfoDto;
import com.info.entity.Student;
import com.info.entity.converter.Converter;
import com.info.exception.SystemException;
import com.info.mapper.StudentInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : yue
 * @Date : 2020/7/15 / 16:44
 */
@Service
public class ExcelService {

    @Autowired
    private StudentInfoMapper studentInfoMapper;

    private Converter<StudentInfoDto> studentConverter = new Converter<>();

    public String saveStudents(List<StudentInfoDto> list)
            throws SystemException{
        if(list==null || list.isEmpty()){
            throw new SystemException(StateMsg.StateMsg_101);
        }
        List<Student> studentList = list.stream()
                .map(item -> studentConverter.clone(item,Student.class))
                .collect(Collectors.toList());
        int i = studentInfoMapper.batchInsert(studentList);
        return i==studentList.size()?StateMsg.StateMsg_200.getMsg()
                :StateMsg.StateMsg_500.getMsg();
    }

}
