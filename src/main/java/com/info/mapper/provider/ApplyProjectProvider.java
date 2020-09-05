package com.info.mapper.provider;

import java.util.Map;

/**
 * @author : yue
 * @Date : 2020/8/22 / 22:21
 */
public class ApplyProjectProvider {

    private final static String TABLE_NAME="apply_project";
    private final static String ALL_COLUMNS="id,menu_id,name";

    public String selectAll(Map<String,Object> params){
        String sort = (String) params.get("sort");

        StringBuilder sql =  new StringBuilder("select");
        sql.append(" apply_project.id,menu_id as menuId").append(",")
                .append("apply_project_menu.name as menuName").append(",")
                .append("apply_project.name as name").append(",")
                .append("apply_project.abbreviation")
                .append(" from apply_project LEFT JOIN apply_project_menu")
                .append(" on apply_project_menu.id=apply_project.menu_id")
                .append(" where apply_project_menu.sort='").append(sort).append("'");

//        StringBuilder sql = new StringBuilder("" +
//                "select apply_project.id,menu_id as menuId," +
//                "apply_project_menu.name as menuName," +
//                "apply_project.name as name\n" +
//                "from apply_project\n" +
//                "  LEFT JOIN apply_project_menu\n" +
//                "    on apply_project_menu.id= apply_project.menu_id\n" +
//                "where apply_project_menu.sort='"+sort+"'");
        return sql.toString();
    }


    public String selectFormTempInMenu(Map<String,Object> params){
        String menuId = (String)params.get("menuId");
        StringBuilder sql  = new StringBuilder();
        sql.append("select form_temp")
                .append(" from apply_project_menu")
                .append(" where id='").append(menuId).append("'");
        return sql.toString();
    }

    public String selectFormTempInProject(Map<String,Object> params){
        String projectId = (String) params.get("projectId");
        StringBuilder sql = new StringBuilder();
        sql.append("select form_temp from apply_project where id="+
                projectId+"");

        return sql.toString();
    }
    /**
     * 模糊查询
     * @param param
     * @return
     */
    public String fuzzySelect(Map<String,Object> param){
        String keyword = (String) param.get("keyword");
        StringBuilder sql = new StringBuilder();
        sql.append("select id, menu_id as menuId, name, abbreviation")
                .append(" from apply_project")
                .append(" where name like")
                .append("'%").append(keyword).append("%'");

        return sql.toString();
    }


}
