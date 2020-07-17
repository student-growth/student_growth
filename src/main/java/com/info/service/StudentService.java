package com.info.service;

import com.github.tobato.fastdfs.domain.StorePath;
import com.info.common.ReturnValue;
import com.info.common.sysenum.StateMsg;
import com.info.converter.StudentConverter;
import com.info.dto.FileDTO;
import com.info.dto.ScoreDTO;
import com.info.dto.StudentInfoDto;
import com.info.entity.ScoreEntity;
import com.info.entity.Student;
import com.info.exception.SystemException;
import com.info.formbean.PageBean;
import com.info.mapper.ScoreInfoMapper;
import com.info.mapper.StudentInfoMapper;
import com.info.util.EncryptUtil;
import com.info.util.FastClientUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import jdk.internal.util.xml.impl.Input;
import org.checkerframework.framework.qual.FromByteCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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

    @Autowired
    private FastClientUtil clientUtil;

    @ApiOperation(value = "通过学号密码获取学生基本信息")
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




    @ApiOperation("修改密码")
    public String updatePassword(String id, String password, String newPassword)
            throws SystemException{
        String pwd = studentMapper.getPassword(id);
        if(pwd==null || !pwd.equals(EncryptUtil.encryptMD5(password))){
            throw new SystemException(StateMsg.StateMsg_202);
        }
        String updatePwd =  EncryptUtil.encryptMD5(newPassword);
        int res = studentMapper.updatePassword(id, updatePwd);
        if(res<=0){
            throw new SystemException(StateMsg.StateMsg_500);
        }
        return StateMsg.StateMsg_200.getMsg();
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



    public FileDTO uploadFile(MultipartFile file, String id) throws Exception{
        FileDTO res = new FileDTO();
        StorePath storePath = clientUtil.upload(file);
        res.setGroup(storePath.getGroup());
        res.setPath(storePath.getPath());
        return res;
    }

    public byte[] downloadFile(String group, String path, String fileName)
            throws Exception{
        return clientUtil.download(group, path);
    }
}
