package com.vedha.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AppProxy {

    @Around("execution(* com.vedha.controller.*.*(..)) || execution(* com.vedha.service.*.*(..)) || execution(* com.vedha.repository.*.*(..))")
    public Object logMethodCall(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        log.debug("Method: {} called with arguments: {}", proceedingJoinPoint.getSignature().toShortString(), proceedingJoinPoint.getArgs());
        long startTime = System.currentTimeMillis();

        Object result = proceedingJoinPoint.proceed();

        long endTime = System.currentTimeMillis();
        log.debug("Time taken by {} is {} s", proceedingJoinPoint.getSignature().toShortString(), (endTime - startTime) / 1000);
        log.debug("Method: {} returned: {}", proceedingJoinPoint.getSignature().toShortString(), result);

        return result;
    }
}
