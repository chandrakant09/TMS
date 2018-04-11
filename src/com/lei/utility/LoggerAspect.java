package com.lei.utility;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * Logging Aspect to log method calls.
 * Provide a Pointcut  OR Annotate any method annotated with custom : @Loggable
 * @author Vinay.Kumar1
 * 
 */
@Slf4j
@Component
@Aspect
public class LoggerAspect {


	
	
	@Before("execution(* com.lei.service..*(..)) || @annotation(Loggable)")
	public void logBefore(JoinPoint joinPoint) {
		String fs;
		fs = String.format("| METHOD CALL START --> %s - %s () ", 
									joinPoint.getSignature().getDeclaringTypeName(), 
									MethodSignature.class.cast(joinPoint.getSignature()).getMethod().getName() );
		log.info(fs);

	}

	
	
	@After("execution(* com.lei.service..*(..)) || @annotation(Loggable)")
	public void logAfter(JoinPoint joinPoint) {
		String fs;
		Throwable e = null;
		fs = String.format("| METHOD CALL END --> %s - %s ()", 
									joinPoint.getSignature().getDeclaringTypeName(), 
									MethodSignature.class.cast(joinPoint.getSignature()).getMethod().getName());
		log.info(fs);
	}

	
	
	
	@AfterReturning(pointcut = "execution(* com.lei.service..*(..)) || @annotation(Loggable)", returning = "result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {
		String fs;
		Object[] passedArgs = joinPoint.getArgs();
		StringBuffer buffer = new StringBuffer("");

		if (passedArgs != null && passedArgs.length > 0) {
			for (int i = 0; i < passedArgs.length; i++) {

				buffer.append(passedArgs[i]);
				buffer.append(",");
			}

			buffer.replace(buffer.length() - 1, buffer.length(), "");
		}

		fs = String.format("| METHOD --> %s - %s (%s) | RETURNED --> %s ", 
									joinPoint.getSignature().getDeclaringTypeName(), 
									MethodSignature.class.cast(joinPoint.getSignature()).getMethod().getName(), 
									buffer.toString(), 
									result);

		log.info(fs);
	}

	
	
	@AfterThrowing(pointcut = "execution(* com.lei.service..*(..)) || @annotation(Loggable)", throwing = "error")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
		String fs;
		Object[] passedArgs = joinPoint.getArgs();
		StringBuffer buffer = new StringBuffer("");

		if (passedArgs != null && passedArgs.length > 0) {
			for (int i = 0; i < passedArgs.length; i++) {

				buffer.append(passedArgs[i]);
				buffer.append(",");
			}

			buffer.replace(buffer.length() - 1, buffer.length(), "");
		}

		fs = String.format("| EXCEPTION OCCURRED --> %s | while calling method --> %s - %s ( %s )",
								error,
								joinPoint.getSignature().getDeclaringTypeName(), 
								MethodSignature.class.cast(joinPoint.getSignature()).getMethod().getName(),
								buffer.toString()
								);
		log.info(fs);

	}

}
