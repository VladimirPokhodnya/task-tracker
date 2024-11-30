package com.github.vladimirpokhodnya.tasktracker.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TaskAspect {
    private static final Logger logger = LoggerFactory.getLogger(TaskAspect.class.getName());

    @Before("@annotation(com.github.vladimirpokhodnya.tasktracker.aspect.annotation.LogExecution)")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Before calling method {}", joinPoint.getSignature().getName());
    }

    @AfterThrowing(
            pointcut = "@annotation(com.github.vladimirpokhodnya.tasktracker.aspect.annotation.LogException)"
    )
    public void logAfterThrowing(JoinPoint joinPoint) {
        logger.error("AfterThrowing calling method {}", joinPoint.getSignature().getName());
    }

    @AfterReturning(
            pointcut = "@annotation(com.github.vladimirpokhodnya.tasktracker.aspect.annotation.HandlingResult)",
            returning = "result"
    )
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("AfterReturning celling method {} returned value is : {}", joinPoint.getSignature().getName(), result);
    }

    @Around("@annotation(com.github.vladimirpokhodnya.tasktracker.aspect.annotation.LogTracking)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) {
        long start = System.currentTimeMillis();

        Object proceed;
        try {
            proceed = joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException("Around EXCEPTION", e);
        } finally {
            long finish = System.currentTimeMillis();
            logger.info("Around calling method {} executed in {} ms", joinPoint.getSignature().getName(), finish - start);
        }

        return proceed;
    }
}
