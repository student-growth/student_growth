package com.info.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by TerryJ on 2020/03/04.
 *
 */

@Data
@Component
@ConfigurationProperties(prefix = "test")
@PropertySource("classpath:test.properties")
public class CommonConfig {

	private String one;
	private String two;

	// 其他地方引用
	// @Autowired
	// private CommonConfig commonConfig;

}
