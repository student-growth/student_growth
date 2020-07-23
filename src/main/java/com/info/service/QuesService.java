package com.info.service;

import com.info.common.sysenum.StateMsg;
import com.info.converter.QuesConverter;
import com.info.dto.QuesDTO;
import com.info.entity.QuesEntity;
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

    @Autowired
    private QuesConverter quesConverter;

    public String saveQuestion(QuesDTO dto) throws Exception{
        QuesEntity entity = new QuesEntity();
        entity.setCategory(dto.getCategory());
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setReceiver(dto.getReceiver());
        int res = quesMapper.insertQuest(entity);
        return res>0? StateMsg.StateMsg_200.getMsg():StateMsg.StateMsg_500.getMsg();
    }


    public List<QuesDTO> getQuesList(int size){
        List<QuesEntity> entities = quesMapper.selectQuest(0, size);
        return entities.stream().map(item->quesConverter.entityConv(item))
                .collect(Collectors.toList());

    }

}
