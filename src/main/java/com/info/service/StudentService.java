package com.info.service;

import com.info.common.ReturnValue;
import com.info.converter.StudentConverter;
import com.info.dto.StudentInfoDto;
import com.info.entity.Student;
import com.info.formbean.PageBean;
import com.info.mapper.StudentInfoMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : yue
 * @Date : 2020/7/8 / 20:21
 */
@Service
public class StudentService {
    @Resource
    private StudentInfoMapper studentMapper;

    @Resource
    private StudentConverter studentConverter;

    @ApiOperation(value = "分页查询学生信息")
    public ReturnValue<Student> getStudentList(PageBean page) {
        ReturnValue<Student> result = new ReturnValue<>();
        try {
            int start = (page.getCurrentPage() - 1) * page.getPageSize();
            List<Student> students = studentMapper.pageSelectStudent(start, page.getPageSize());
            result.setList(students);
        } catch (Exception e) {
            result.setMsg(e.getMessage());
        }
        return result;
    }
}
