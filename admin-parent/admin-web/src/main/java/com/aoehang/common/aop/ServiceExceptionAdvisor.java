package com.aoehang.common.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.aoehang.common.exception.ServiceException;

/**
 * Service异常切面
 * 
 * @author QYANZE
 *
 */
@Aspect
public class ServiceExceptionAdvisor {
	private static final Logger LOGGER = LogManager.getLogger();
	
	@Pointcut("@within(org.springframework.stereotype.Service)")
	public void servicePointCut(){}
	
	/**
	 * service 异常处理，直接抛到上层处理
	 * 
	 * @param joinPoint
	 * @param e 异常
	 */
	@AfterThrowing(pointcut="servicePointCut()", throwing="e")
	public void handException(JoinPoint joinPoint, Throwable e) {
		String classname = joinPoint.getTarget().getClass().getName();
		String methodname = joinPoint.getSignature().getName();
		LOGGER.error("{}()方法发生异常*****************************", classname + "." + methodname);
		LOGGER.error("异常信息：", e);
		throw new ServiceException(e);
	}
}
