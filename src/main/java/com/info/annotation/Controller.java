package com.info.annotation;

import java.lang.annotation.*;

/**
 * Controller AOP 注解；生成对应的Controller文件
 *
 * @author TerryJ
 * @Date
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Controller {
    String name();

}