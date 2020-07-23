package com.info.entity.converter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author : yue
 * @Date : 2020/7/21 / 19:19
 * java-8 lambda
 * entity converter
 */
public class Converter<T> {

    /**
     * entity converter
     *
     * 用于做复杂的实体类克隆
     * @param entity entity object
     * @param <R>    void
     * @return void
     */
    public <R> R clone(T entity, Class<R> clazz) {
        try {
            Class<?> entity_class = entity.getClass();
            Field[] entity_fields = entity_class.getDeclaredFields();
            Field[] dto_fields = clazz.getDeclaredFields();

            List<String> fieldName = Arrays.stream(entity_fields)
                    .map(Field::getName)
                    .collect(Collectors.toList());
            List<Field> fields = Arrays.stream(dto_fields)
                    .filter(item -> fieldName.contains(item.getName()))
                    .collect(Collectors.toList());
            if (fields == null) {
                return null;
            }
            R dto = clazz.newInstance();
            for (int i = 0; i < fields.size(); i++) {
                String get = createGetMethod(fields.get(i));
                String set = createSetMethod(fields.get(i));
                Method getMethod = entity_class.getMethod(get);
                Method setMethod = clazz.getMethod(set, fields.get(i).getType());
                Object params = getMethod.invoke(entity);
                if (params != null) {
                    setMethod.invoke(dto, params);
                }
            }
            return dto;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private String createSetMethod(Field field) {
        return "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
    }

    private String createGetMethod(Field field) {
        return "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
    }
}
