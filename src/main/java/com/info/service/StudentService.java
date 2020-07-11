package com.info.service;

import com.info.common.ReturnValue;
import com.info.common.sysenum.StateMsg;
import com.info.converter.StudentConverter;
import com.info.dto.ScoreDTO;
import com.info.dto.StudentInfoDto;
import com.info.entity.ScoreEntity;
import com.info.entity.Student;
import com.info.exception.SystemException;
import com.info.formbean.PageBean;
import com.info.mapper.ScoreInfoMapper;
import com.info.mapper.StudentInfoMapper;
import com.info.util.EncryptUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : yue
 * @Date : 2020/7/8 / 20:21
 */
@Service
public class StudentService {
    @Resource
    private StudentInfoMapper studentMapper;

    @Resource
    private ScoreInfoMapper scoreMapper;
    @Resource
    private StudentConverter studentConverter;


    @ApiOperation(value = "通过学号密码 获取学生基本信息")
    public StudentInfoDto getStudentInfo(String id, String password) throws SystemException{
        Student entity = studentMapper.getStudentById(id);
        if(null==entity){
            throw new SystemException(StateMsg.StateMsg_202);
        }
        String encryptCode= EncryptUtil.encryptMD5(password);
        if(!entity.getPassword().equals(encryptCode)){
            throw new SystemException(StateMsg.StateMsg_203);
        }
        return  studentConverter.stuInfoConverter(entity);

    }



    @ApiOperation(value = "分页查询学生信息")
    public ReturnValue<Student> getStudentList(PageBean page)
            throws SystemException{
        if(page.getCurrentPage()<=0) {
            throw  new SystemException(StateMsg.StateMsg_200);
        }
        ReturnValue<Student> result = new ReturnValue<>();
        int start = (page.getCurrentPage() - 1) * page.getPageSize();
        List<Student> students = studentMapper.pageSelectStudent(start, page.getPageSize());
        result.setList(students);
        return result;
    }

    @ApiOperation("修改密码")
    public String changePassword(String id, String oldPassword)
            throws SystemException{
        String password = studentMapper.getPassword(id);
        if(!EncryptUtil.encryptMD5(password).equals(oldPassword)){
            throw new SystemException(StateMsg.StateMsg_203);
        }



        return null;
    }


    @ApiOperation("获取成绩")
    public List<ScoreDTO> queryScoreById(String id) throws SystemException{
        List<ScoreEntity> scores = scoreMapper.selectScoreById(id);
        if(null==scores || scores.size()==0){
            throw new SystemException(StateMsg.StateMsg_104);
        }
        return scores.stream().map(item ->
                studentConverter.scoreInfoConverter(item))
                .collect(Collectors.toList());
    }
}
