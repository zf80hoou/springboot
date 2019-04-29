package com.freebo.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 自定义监听器
 * @author 17031268
 *
 */
@Component
public class MyListener implements ServletContextListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyListener.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		LOGGER.debug("自定义监听器销毁...");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		LOGGER.debug("自定义监听器初始化...");
	}

}
