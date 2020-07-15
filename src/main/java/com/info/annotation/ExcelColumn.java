package com.info.annotation;

import java.lang.annotation.*;

/**
 * @author : yue
 * 2020/7/15 / 12:50
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD})
public @interface ExcelColumn {

    String name();


}
