package com.freebo.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.freebo.common.constant.FreeboConstant;

/**
 * 自定义过滤器
 * @author 17031268
 *
 */
@Component
public class MyFilter implements Filter {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyFilter.class);
	
	@Autowired
	private ApplicationConfig applicationConfig;
	
	private List<String> notFilterUrl = new ArrayList<String>();

	private HttpServletRequest request;

	private HttpServletResponse response;

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, 
			FilterChain filterChain) throws IOException, ServletException {
		
		long start = System.currentTimeMillis();
		this.request = (HttpServletRequest) servletRequest;
		this.response = (HttpServletResponse) servletResponse;
		// 获取当前请求链接
		String url = request.getRequestURI();
		// 获取网站访问根目录
		String accessPath = request.getContextPath();
		
		LOGGER.debug("请求的链接是：{}", url);
		
		// 获取配置中是否需要 过滤的 配置，开发环境一般不需要
		String useFilter = applicationConfig.getEnvironment().getProperty("use.filter");
		if (FreeboConstant.cons_1.equals(useFilter)) {
			LOGGER.debug("需要 校验 url");
			if (noFilterUrl(url)) {
				LOGGER.debug("传入的url为不需要过滤的");
				// 非过滤性 url  例如 /main /login
				filterChain.doFilter(request, response);
			} else {
				LOGGER.debug("传入的url为需要过滤的");
				// 需要过滤的 url
				HttpSession session = request.getSession();
				String userName = (String) session.getAttribute("userName");
				if (null == userName) {
					LOGGER.debug("未找到用户名，请先登录");
					// session中记录当前页面跳转
					session.setAttribute("lastUrl", url);
					// 当用户名为空时 直接导向登录页面
					response.sendRedirect(accessPath + "/user/turnLogin");
				} else {
					LOGGER.debug("有用户名，校验用户名是否有该权限或者校验用户名，密码是否正确，这里不做权限控制，直接透传");
					filterChain.doFilter(request, response);
				}
			}			
		} else {
			LOGGER.debug("开发环境 不需要 过滤url 直接透传");
			filterChain.doFilter(request, response);
		}

		LOGGER.debug("过滤器监控接口-{} 耗时：{}ms", url,	(System.currentTimeMillis() - start));

	}
	
	private boolean noFilterUrl(String url) {
		for (String noFilterUrl : notFilterUrl) {
			if (url.indexOf(noFilterUrl) > 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOGGER.info("=======初始化过滤器=========");
		String notFilterUrlCongfig = applicationConfig.getEnvironment().getProperty("not.filter.url");
		String[] notFilterUrlCongfigs = notFilterUrlCongfig.split(",");
		if (null != notFilterUrlCongfigs && notFilterUrlCongfigs.length > 0) {
			notFilterUrl.addAll(Arrays.asList(notFilterUrlCongfigs));
		}
	}

	@Override
	public void destroy() {
		LOGGER.info("=======销毁过滤器=========");
		this.notFilterUrl = null;
	}

}
