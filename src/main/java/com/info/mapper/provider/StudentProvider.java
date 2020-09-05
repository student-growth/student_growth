package com.info.mapper.provider;

import com.info.entity.Student;
import com.info.mapper.sql.SQLBuilder;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

/**
 * @author : yue
 * @Date : 2020/7/11 / 16:00
 */
public class StudentProvider {


    public  String selectStudents(Map<String,Object> params){
        String id = (String) params.get("id");
        String grade = (String) params.get("grade");
        SQLBuilder sql = new SQLBuilder();
        sql.SELECT("id","name","grade")
                .FROM("student")
                .WHERE("id!='"+id+"'", "grade='"+grade+"'");

        return sql.getSQL();
    }

    public String queryPasswordById(Map<String ,Object> params){
        return new SQL(){
            {
                SELECT("password");
                FROM("student");
                WHERE("id="+params.get("id"));
            }
        }.toString();
    }

    /**
     * 批量导入学生信息
     * @param params 学生列表
     * @return 插入结果
     */
    public String batchInsert(Map<String,Object> params){
        List<Student> students =(List<Student>) params.get("list");
        StringBuilder sql = new StringBuilder("insert into student");
        sql.append("(id,name,password,department,major,grade)");
        sql.append("VALUES");
        for(Student s : students){
            sql.append("(");
            sql.append("'").append(s.getId()).append("',");
            sql.append("'").append(s.getName()).append("',");
            sql.append("'").append( s.getPassword()).append("',");
            sql.append("'").append(s.getDepartment()).append("',");
            sql.append("'").append(s.getMajor()).append("',");
            sql.append("'").append(s.getGrade()).append("'");
            sql.append("),");
        }
        return sql.toString().substring(0,sql.length()-1);
    }
}
