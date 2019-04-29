package com.freebo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 该注解指定项目为springboot，由此类当作程序入口 自动装配 web 依赖的环境
 */
@SpringBootApplication
public class WeixinServerApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WeixinServerApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(WeixinServerApplication.class, args);
	}

}
