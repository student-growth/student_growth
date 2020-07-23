package com.info.mapper.sql;
import com.info.annotation.SQLAlias;

import java.lang.reflect.Field;

/**
 * @author : yue 2020/7/19 / 15:08
 */
public class SQLBuilder {

    private StringBuilder sql  = new StringBuilder();
    private static final String AND = " ) and ( ";
    private static final String OR =" ) or (";

    public SQLBuilder INSERT_INTO(String table,String...column){
        sql.append(" insert into ");
        sql.append(table);
        sql.append(" ( ");
        for(int i=0;i<column.length-1;i++){
           sql.append(column[i]).append(",");
        }
        sql.append(column[column.length-1]);
        sql.append(" ) ");
        return this;
    }

    public SQLBuilder VALUE(Object...value){
        sql.append(" value ( ");
        for(int i=0;i<value.length-1;i++){
            if(value[i] instanceof String){
                sql.append("'").append(value[i]).append("',");
            }else{
                sql.append(value[i]).append(",");
            }
        }
        Object last = value[value.length - 1];
        if(last instanceof String){
            sql.append("'").append(last).append("'");
        }else{
            sql.append(last);
        }
        sql.append(" ) ");
        return this;
    }




    public SQLBuilder SELECT(String...columns){
        sql.append(" select ");
        for(int i=0;i<columns.length-1;i++){
            sql.append(columns[i]).append(",");
        }
        sql.append(columns[columns.length-1]);
        return this;
    }


    /**
     * `@SQLAlias
     * use this function must add annotation to the entity,
     * mark the sql column to the field of entity
     * @param clazz void
     * @param <T> void
     * @return void
     */
    public <T> SQLBuilder SELECT_ALL(Class<T> clazz){
        sql.append(" select ");
        Field[] fields = clazz.getDeclaredFields();
        for(int i=0;i<fields.length-1;i++){
            SQLAlias alias = fields[i].getAnnotation(SQLAlias.class);
            if(alias!=null){
                sql.append(alias.name()).append(" as ")
                        .append(fields[i].getName()).append(",");
            }else{
                sql.append(fields[i].getName()).append(",");
            }
        }
        Field field = fields[fields.length - 1];
        SQLAlias alias = field.getAnnotation(SQLAlias.class);
        if(alias!=null){
            sql.append(alias.name()).append(" as ").append(field.getName());
        }else{
            sql.append(field.getName());
        }
        return this;

    }

    public SQLBuilder FROM(String table){
        sql.append(" from ").append(table);
        return this;
    }

    public SQLBuilder WHERE(String... conditions){
        sql.append(" where ( ");
        for(int i=0;i<conditions.length-1;i++){
            sql.append(conditions[i]).append(AND);
        }
        sql.append(conditions[conditions.length-1]);
        sql.append(" ) ");
        return this;
    }

    public SQLBuilder OR(String condition){
        sql.append(" or ").append(condition);
        return this;
    }

    public SQLBuilder ORDER(String...column){
        sql.append(" order by ");
        for(int i=0;i<column.length-1;i++){
            sql.append(column[i]).append(",");
        }
        sql.append(column[column.length-1]);
        return this;
    }

    public SQLBuilder LIMIT(int start,int size){
        sql.append( " limit ").append(start)
                .append(",").append(size);
        return this;
    }



    public String getSQL(){
        sql.append(";");
        return this.sql.toString();
    }




}
