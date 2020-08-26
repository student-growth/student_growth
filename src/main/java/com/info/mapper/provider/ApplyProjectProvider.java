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
        StringBuilder sql = new StringBuilder("" +
                "select apply_project.id,menu_id as menuId,apply_project_menu.name as menuName,apply_project.name as name\n" +
                "from apply_project\n" +
                "  LEFT JOIN apply_project_menu\n" +
                "    on apply_project_menu.id= apply_project.menu_id\n" +
                "where apply_project_menu.sort='"+sort+"'");
        return sql.toString();
    }


    public String selectFormTemp(Map<String,Object> params){
        String menuId = (String)params.get("menuId");
        StringBuilder sql  = new StringBuilder();
        sql.append("select form_temp from apply_project_menu where id="+
                menuId+"");
        return sql.toString();
    }
}
