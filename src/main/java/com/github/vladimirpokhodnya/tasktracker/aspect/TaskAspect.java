package com.github.vladimirpokhodnya.tasktracker.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TaskAspect {
    private static final Logger logger = LoggerFactory.getLogger(TaskAspect.class.getName());

    @Before("@annotation(com.github.vladimirpokhodnya.tasktracker.aspect.annotation.LoggingExecution)")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Before calling method {}", joinPoint.getSignature().getName());
    }

    @AfterThrowing(pointcut = "@annotation(com.github.vladimirpokhodnya.tasktracker.aspect.annotation.LoggingException)")
    public void logAfterThrowing(JoinPoint joinPoint) {
        logger.error("AfterThrowing method {}", joinPoint.getSignature().getName());
    }
}
