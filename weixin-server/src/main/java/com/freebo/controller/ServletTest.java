package com.freebo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义Servlet 
 * 这个自定义servlet 需要在 配置类中注入  
 * 自定义servlet不被 监听器 AOP管理
 * 自定义servlet抛出的异常不被 异常处理类管理
 * @author 17031268
 *
 */
public class ServletTest extends HttpServlet {

	private static final long serialVersionUID = 2676827375714172754L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int k = 1/0;
		System.out.println(k);
		resp.setContentType("text/html;charset=utf-8");
		resp.getWriter().write("自定义 Servlet");
	}
	
}

