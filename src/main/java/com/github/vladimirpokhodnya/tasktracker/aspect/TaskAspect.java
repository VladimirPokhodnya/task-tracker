package com.github.vladimirpokhodnya.tasktracker.aspect;

import com.github.vladimirpokhodnya.tasktracker.model.Task;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class TaskAspect {
    private static final Logger logger = LoggerFactory.getLogger(TaskAspect.class.getName());

    @Before("@annotation(com.github.vladimirpokhodnya.tasktracker.aspect.annotation.LoggingExecution)")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Before calling method {}", joinPoint.getSignature().getName());
    }

    @AfterThrowing(
            pointcut = "@annotation(com.github.vladimirpokhodnya.tasktracker.aspect.annotation.LoggingException)"
    )
    public void logAfterThrowing(JoinPoint joinPoint) {
        logger.error("After throwing method {}", joinPoint.getSignature().getName());
    }

    @AfterReturning(
            pointcut = "@annotation(com.github.vladimirpokhodnya.tasktracker.aspect.annotation.HandlingResult)",
            returning = "result"
    )
    public void logAfterReturning(JoinPoint joinPoint, List<Task> result) {
        logger.info("Method {} returned value is : {}", joinPoint.getSignature().getName(), result);
    }
}
