package com.info.annotation;

import java.lang.annotation.*;

/**
 * @author : yue 2020/7/21 / 18:13
 * 对应的MySql表格中的column name
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD})
public @interface SQLAlias {

    String name();
}
