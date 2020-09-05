package com.info.service;

import com.info.common.sysenum.StateMsg;
import com.info.dto.AssessDTO;
import com.info.entity.AssessEntity;
import com.info.entity.converter.Converter;
import com.info.exception.SystemException;
import com.info.mapper.AssessMapper;
import com.info.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : yue
 * @Date : 2020/9/4 / 16:21
 * 学生评价服务
 */
@Service
public class CommentService {


    @Autowired
    private AssessMapper assessMapper;


    private Converter<AssessEntity> converter = new Converter<>();

    /**
     * 学生评估分数
     *
     * @param id    学号
     * @param psy   心理成绩
     * @param moral 品德成绩
     * @return void
     */
    public String assess(String id, String other, int psy, int moral) throws Exception {
        if (psy > 100 || moral > 100 || psy < 0 || moral < 0) {
            throw new SystemException(StateMsg.StateMsg_101);
        }
        //如果数据已经存在，则更新数据
        AssessEntity entity = new AssessEntity(id, other, psy, moral);
        //计算学年
        int year = DateUtil.getNowYear();
        String semester=year+"-"+(year+1);
        entity.setSemester(semester);
        if (assessMapper.selectByPrimary(id, other) != null) {
            int res = assessMapper.update(entity);
            return res == 1 ? StateMsg.StateMsg_200.getMsg() :
                    StateMsg.StateMsg_500.getMsg();
        }
        Integer res = assessMapper.insert(entity);
        return res == 1 ? StateMsg.StateMsg_200.getMsg() :
                StateMsg.StateMsg_500.getMsg();
    }



    public List<AssessDTO> getCommentScore(String id){
        int year = DateUtil.getNowYear();
        String semester = year+"-"+(year+1);
        List<AssessEntity> list = assessMapper.selectAllScore(id, semester);
        if(list==null || list.isEmpty()){
            return null;
        }
        return list.stream().map(item->converter.clone(item, AssessDTO.class))
                .collect(Collectors.toList());
    }

}
