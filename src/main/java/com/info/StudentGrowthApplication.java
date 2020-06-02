package com.info;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * @author yue
 */
@SpringBootApplication
@EnableScheduling
public class StudentGrowthApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentGrowthApplication.class, args);
        System.out.println("start success! ");
    }

}
