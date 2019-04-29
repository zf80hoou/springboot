package com.freebo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freebo.common.DataSourceMysqlConfig;
import com.freebo.common.ApplicationConfig;

@RestController
public class HelloWorldController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldController.class);
	
	@Autowired
	private ApplicationConfig config;
	
	@Autowired
	private DataSourceMysqlConfig mysqlConfig;
	
    @RequestMapping("/")
    public String helloworld() {
        return "helloworld";
    }
    
    @RequestMapping("/helloworld2")
    public String helloworld2() {
    	LOGGER.debug("debug something");
    	LOGGER.info("info something");
    	LOGGER.warn("warn something");
    	System.out.println(config.getServerPort());
    	System.out.println(config.getEnvironment().getProperty("database.mysql.userName"));
    	System.out.println(config.getEnvironment().getProperty("database.mysql.password"));
    	System.out.println(config.getEnvironment().getProperty("database.mysql.url"));
    	System.out.println(config.getEnvironment().getProperty("database.mysql.driverClassName"));
    	System.out.println(mysqlConfig.getUserName());
    	System.out.println(mysqlConfig.getPassword());
    	System.out.println(mysqlConfig.getUrl());
    	System.out.println(mysqlConfig.getDriverClassName());
    	return "helloworld2";
    }
    
}
