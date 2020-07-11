package com.info.converter;


import com.info.dto.ScoreDTO;
import com.info.dto.StudentInfoDto;
import com.info.entity.ScoreEntity;
import com.info.entity.Student;
import org.mapstruct.Mapper;

/**
 * @author : yue
 * @Date : 2020/7/8 / 20:22
 */
@Mapper(componentModel = "spring")
public interface StudentConverter {

    StudentInfoDto stuInfoConverter(Student entity);


    ScoreDTO scoreInfoConverter(ScoreEntity entity);
}
