package com.shop.shop.Aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Aspect
@Component
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.ERM.erm.Controller..*(..))")
    public void controllerMethods() {
    }

    @Around("controllerMethods()")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) {
        String requestId = UUID.randomUUID().toString();
        MDC.put("requestId", requestId);

        long startTime = System.currentTimeMillis();
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            logger.error("An exception occurred in {}: {}", joinPoint.getSignature().toShortString(), throwable.getMessage());
        }
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        logger.info("{} executed in {} ms", joinPoint.getSignature().toShortString(), executionTime);

        MDC.clear();

        return result;
    }


}
