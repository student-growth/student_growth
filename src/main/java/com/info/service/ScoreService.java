package com.info.service;

import com.info.common.sysenum.ApplyEnum;
import com.info.dto.AbilityDTO;
import com.info.dto.TotalScoreDTO;
import com.info.entity.AbilityEntity;
import com.info.entity.extra.ApplyExtraEntity;
import com.info.mapper.AbilityMapper;
import com.info.mapper.extra.ApplyExtraMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : yue
 * @Date : 2020/9/6 / 11:20
 */
@Service
public class ScoreService {

    @Autowired
    private ApplyExtraMapper applyExtraMapper;

    @Autowired
    private AbilityMapper abilityMapper;

    //获取综合成绩
    public Map<String,List<TotalScoreDTO>> getTotalScore(String studentId, String semester)
            throws Exception{
        //获取已经通过的项目

        String start=semester.substring(0,4)+"-00-00";
        int index = semester.indexOf("-") + 1;
        String end =semester.substring(index,index+4)+"-00-00";

        List<ApplyExtraEntity> list = applyExtraMapper.getTotalScore(studentId,start,end,
                ApplyEnum.PASS.name());
        if(list==null || list.isEmpty()){
            return null;
        }
        //获取申请的
        return  list.stream().map(item->{
            TotalScoreDTO score = new TotalScoreDTO();
            score.setId(item.getId());
            score.setMenuId(item.getMenuId());
            score.setApplyName(item.getApplyName());
            score.setScore(item.getScore());
            score.setSort(item.getSort());
            score.setPassTime(item.getModifyTime());
            return score;
        }).collect(Collectors.groupingBy(TotalScoreDTO::getSort));
    }


    //获取综合能力总体分数
    public AbilityDTO getAbilityScore(String studentId,String semester) throws Exception{
        AbilityEntity data = abilityMapper.selectAbilityBaseScore(studentId, semester);
        if(data==null){
            return  null;
        }
        AbilityDTO result  = new AbilityDTO();
        result.setProfession(data.getProfTotal());
        result.setSport(data.getHealthScore());

        //todo 英语平均分数
        result.setBaseScore(data.getBaseTotal());
        result.setBaseScoreRank(data.getBaseTotalRank());
        result.setAbilityScore(data.getTotalScore());
        result.setAbilityScoreRank(data.getTotalRank());
        return result;
    }
}
