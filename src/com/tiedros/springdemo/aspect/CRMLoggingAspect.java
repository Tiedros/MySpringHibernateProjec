package com.tiedros.springdemo.aspect;

import java.util.logging.Logger;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

	// setup logger
	Logger myLogger=Logger.getLogger(getClass().getName());
	
	// setup pointcut declararions
	@Pointcut("execution(* com.tiedros.springdemo.controller.*.*(..))")
	public void forControllerPackage() {}
	
	@Pointcut("execution(* com.tiedros.springdemo.service.*.*(..))")
	public void forServicePackage() {}
	
	@Pointcut("execution(* com.tiedros.springdemo.dao.*.*(..))")
	public void forDaoPackage() {}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage() ")
	public void forAppFlow() {}
	
	// add @Before advice
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint) {
		// display method we are calling
		String theMethod=theJoinPoint.getSignature().toShortString();
		myLogger.info("=====>>>> in @Before: calling method:  "+ theMethod);
		
		// display the arguments to the method
		
		// get the arguments
		Object [] args=theJoinPoint.getArgs();
		
		// loop through the args
		for(Object object:args) {
			myLogger.info("=====>>>> argument: "+object);
		}
	}
	
	
	// add @AfterReturning advice
	@AfterReturning(
			pointcut="forAppFlow()",
			returning="theResult"
			)
	public void afterReturning(JoinPoint theJoinPoint,Object theResult) {
		// display method  we are returning from
		String theMethod=theJoinPoint.getSignature().toShortString();
		myLogger.info("=====>>>> in @AfterReturning: from method:  "+ theMethod);
		
		// display data returned
		myLogger.info("=====>>>> result: "+ theResult);
	}
	
	
}
