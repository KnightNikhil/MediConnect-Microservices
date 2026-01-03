package com.mediconnect.service.common_entities.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ExecutionTimeAspect {

    @Around("@annotation(ExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint proceedingJoinPoint){
        long startTime = System.currentTimeMillis();
        try {
            return proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }finally {
            log.info("Method {} execution completed in {} ms",
                    proceedingJoinPoint.getSignature().toShortString(),
                    System.currentTimeMillis() - startTime);
        }
    }

}
