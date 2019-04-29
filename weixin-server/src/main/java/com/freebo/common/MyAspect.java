package com.freebo.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 自定义AOP
 * @author 17031268
 *
 */
@Aspect
@Component
public class MyAspect {

	//@Around("execution(* com.freebo.controller.*.*(..))")
	@Around("execution(* com.freebo.controller.FastJsonController.*(..))")
	public Object method(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("=====controller层 Aspect处理=======");
		Object[] args = pjp.getArgs();
		for (Object arg : args) {
			System.out.println("参数为:" + arg);
		}
		long start = System.currentTimeMillis();
		Object object = pjp.proceed();
		System.out.println("Aspect 耗时:" + (System.currentTimeMillis() - start));
		return object;
	}

	
}
