package com.bitguiders.bjsbt_01.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/*
   STEP-1: WEB-INF/spr-servlet.xml  
   xmlns:aop="http://www.springframework.org/schema/aop"
   
   xsi:schemaLocation="http://www.springframework.org/schema/aop  
   http://www.springframework.org/schema/aop/spring-aop-3.0.xsd"
   
   STEP-2: WEB-INF/spr-servlet.xml
   <aop:aspectj-autoproxy/>

	STEP-3: Write following code
 */

@Aspect
@Component 
public class UserAspect {
	
    @Before("execution(* com.bitguiders.bjsbt_01.rest..*.*(..))")
	public void before(JoinPoint joinPoint){
		System.out.println("***************aop-> Before****************");
		System.out.println("Method hijacked : "+joinPoint.getSignature().getName());
	}
    @After("execution(* com.bitguiders.bjsbt_01.rest..*.*(..))")
	public void after(JoinPoint joinPoint){
		System.out.println("***************aop-> After****************");
		System.out.println("Method hijacked : "+joinPoint.getSignature().getName());
		System.out.println("Method Argsuments ="+joinPoint.getArgs());
	}
    @AfterReturning(pointcut="execution(* com.bitguiders.bjsbt_01.rest..*.*(..))", returning="result")
	public void afterReturning(JoinPoint joinPoint,Object result){
		System.out.println("***************aop-> AfterReturning****************");
		System.out.println("Method hijacked : "+joinPoint.getSignature().getName());
		System.out.println("Method return value is : "+result);
	}
    @AfterThrowing(pointcut="execution(* com.bitguiders.bjsbt_01.rest..*.*(..))", throwing="error")
 	public void afterThrowing(JoinPoint joinPoint,Throwable error){
 		System.out.println("***************aop-> AfterReturning****************");
 		System.out.println("Method hijacked : "+joinPoint.getSignature().getName());
 		System.out.println("Method return value is : "+error);
 	}
	@Around("execution(* com.bitguiders.bjsbt_01.rest..*.*(..))")
	public Object invoke(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		StopWatch stopWatch = new StopWatch();
		stopWatch.start(proceedingJoinPoint.getSignature().getName());
		Object obj = proceedingJoinPoint.proceed();
		stopWatch.stop();
		System.out.println("Time to execute method = "+stopWatch.getTotalTimeSeconds());
		return obj;
	}
    
}
