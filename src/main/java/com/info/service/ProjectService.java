package com.info.service;

import com.info.dto.ApplyProjectDTO;
import com.info.entity.ApplyProjectEntity;
import com.info.entity.converter.Converter;
import com.info.mapper.ApplyProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : yue
 * @Date : 2020/9/3 / 11:21
 */
@Service
public class ProjectService {

    @Autowired
    private ApplyProjectMapper applyProjectMapper;

    private Converter<ApplyProjectEntity> converter = new Converter<>();


    //模糊查询项目列表
    public List<ApplyProjectDTO> fuzzyQuery(String keyword) throws Exception{
        if(keyword==null){
            //todo 查询所有
            return null;
        }
        List<ApplyProjectEntity> list = applyProjectMapper.fuzzySelect(keyword);
        if(list==null || list.isEmpty()){
            return null;
        }
        return list.stream().map(item->converter.clone(item,ApplyProjectDTO.class))
                .collect(Collectors.toList());
    }
}
