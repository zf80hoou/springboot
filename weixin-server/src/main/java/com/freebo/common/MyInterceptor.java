package com.freebo.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义拦截器     拦截器 除了 增加 @Component 注解外 还需要在WebConfig 中 将其注册到拦截器链中
 * @author 17031268
 */
@Component   
public class MyInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
			Object handler, Exception exception) throws Exception {
		// 打印处理耗时 以及 可能抛出的异常，这里可以做异常处理用
		System.out.println("========afterCompletion=========");
		Long start = (Long) request.getAttribute("startTime");
		System.out.println("耗时:"+(System.currentTimeMillis() - start));
		System.out.println(exception);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler, ModelAndView modelAndView) throws Exception {
		// 打印业务处理 执行耗时
		System.out.println("========postHandle=========");
		Long start = (Long) request.getAttribute("startTime");
		System.out.println("耗时:"+(System.currentTimeMillis() - start));
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler) throws Exception {
		// 拦截器 导向controller之前 打印 处理业务的 类 和方法名
		System.out.println("========preHandle=========");
		System.out.println(((HandlerMethod)handler).getBean().getClass().getName());
		System.out.println(((HandlerMethod)handler).getMethod().getName());
		request.setAttribute("startTime", System.currentTimeMillis());
		return true;

	}

}
