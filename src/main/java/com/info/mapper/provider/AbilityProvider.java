package com.info.mapper.provider;

import com.info.entity.AbilityEntity;
import com.info.mapper.sql.SQLBuilder;

import java.util.Map;

/**
 * @author : yue
 * @since : 2020/9/16
 */
public class AbilityProvider {


    public String selectAbilityAllScore(Map<String,Object> params){
        String studentId = (String) params.get("studentId");
        String semester=(String)params.get("semester");
        SQLBuilder sql = new SQLBuilder();
        sql.SELECT_ALL(AbilityEntity.class)
                .FROM(" comprehensive_ability_rank")
                .WHERE(" student_id='"+studentId+"'","semester='"+semester+"'");

        return sql.getSQL();
    }



    //用于奖学金申请时候，显示的分数
    public String selectAbilityBaseScore(Map<String,Object> params){
        String studentId = (String) params.get("studentId");
        String semester=(String)params.get("semester");

        StringBuilder sql = new StringBuilder();
        sql.append("select prof_total as profTotal, health_score as healthScore,")
                .append(" base_total as baseTotal, base_total_rank as baseTotalRank, total_score as totalScore, total_rank as totalRank")
                .append(" from comprehensive_ability_rank")
                .append(" where student_id='").append(studentId).append("'")
                .append(" and semester='").append(semester).append("'");

        return sql.toString();
    }

    public String selectPersonalizedList(Map<String,Object> params){
        String studentId = (String) params.get("studentId");
        StringBuilder sql = new StringBuilder();
        sql.append("select example_id from personalized_list")
                .append(" where student_id='").append(studentId).append("'");

        return sql.toString();
    }
}
