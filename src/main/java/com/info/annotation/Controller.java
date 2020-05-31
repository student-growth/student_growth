package com.info.annotation;

import java.lang.annotation.*;

/**
 * @author : yue
 * @Date : 2020/5/31 / 23:20
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Controller {
    String name();
}
