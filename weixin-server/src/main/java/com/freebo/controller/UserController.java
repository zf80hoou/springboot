package com.freebo.controller;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@RequestMapping("turnLogin")
	public String turnLogin(Map<String, Object> map) {
		LOGGER.debug("转向登录页");
		map.put("msg", "我是登录页");
		return "login";
	}
	
	@RequestMapping("doLogin")
	public String doLogin(ServletRequest servletRequest, ServletResponse servletResponse, 
			String userName, String passWord, Map<String, Object> map) throws Exception {
		LOGGER.debug("执行登录操作");
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();
		if (!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(userName)) {
			LOGGER.debug("校验登录名密码成功 记录登录名密码 并导向目标页面");
			// 校验用户名密码 成功之后 会话中记录
			session.setAttribute("userName", userName);
			session.setAttribute("passWord", passWord);
			// 成功之后 转向之前的页面 如果没有 则 导向主页
			String lastUrl = (String) session.getAttribute("lastUrl");
			if (null != lastUrl) {
				response.sendRedirect(lastUrl);
			}
		} else {
			LOGGER.debug("校验登录名密码失败，导向异常页面");
			map.put("msg", "登录异常，用户名或密码不正确");
			return "error";
		}
		return "main";
	}
	
}
