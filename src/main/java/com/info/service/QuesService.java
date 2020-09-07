package com.info.service;

import com.info.common.sysenum.StateMsg;
import com.info.dto.QuesDTO;
import com.info.entity.QuesEntity;
import com.info.entity.converter.Converter;
import com.info.mapper.QuesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : yue
 * @Date : 2020/7/20 / 23:13
 */
@Service
public class QuesService {
    @Autowired
    private QuesMapper quesMapper;

    private Converter<QuesEntity> quesEntityConverter = new Converter<>();
    private Converter<QuesDTO> quesDTOConverter = new Converter<>();


    public String saveQuestion(QuesDTO dto) throws Exception {
        QuesEntity entity = quesDTOConverter.clone(dto, QuesEntity.class);
        int res = quesMapper.insertQuest(entity);
        return res > 0 ? StateMsg.StateMsg_200.getMsg() : StateMsg.StateMsg_500.getMsg();
    }


    //分页查询
    public List<QuesDTO> getQuesList(int size) {
        List<QuesEntity> entities = quesMapper.selectQuest(0, size);
        return entities.stream().map(item -> quesEntityConverter.clone(item, QuesDTO.class))
                .collect(Collectors.toList());
    }

    public List<QuesDTO> getQuesList(String studentId, int size) {
        List<QuesEntity> list = quesMapper.selectByStuID(studentId);
        if (list == null || list.isEmpty()) {
            return null;
        }
        if (size <= 0) {
            return list.stream().map(item -> quesEntityConverter.clone(item, QuesDTO.class))
                    .collect(Collectors.toList());
        } else {
            return list.stream().map(item -> quesEntityConverter.clone(item, QuesDTO.class))
                    .limit(size)
                    .collect(Collectors.toList());
        }
    }

    public String getFeedBack(String id){
        return  quesMapper.selectFeedback(id);
    }

}
