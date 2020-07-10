package com.info.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : yue
 * @Date : 2020/6/2 / 9:43
 *
 */
@Configuration
@MapperScan("com.info.mapper*")
public class MyBatisPluginConf {

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

}
