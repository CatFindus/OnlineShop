package ru.puchinets.ratesservice.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static ru.puchinets.ratesservice.Constants.*;

@Slf4j
@Component
@Aspect
public class LoggingAspect {
    @Before(value = "isControllerPackage() || " +
                    "isServicePackage() || " +
                    "isMapperLayer() || " +
                    "isRepositoryLayer()")
    public void before(JoinPoint joinPoint) {
        log.trace(LOG_BEGIN,
                joinPoint.getSignature().getDeclaringType().getTypeName(),
                joinPoint.getSignature().getName(),
                Arrays.asList(joinPoint.getArgs()));
    }

    @AfterReturning(value = "isControllerPackage() || " +
                            "isServicePackage() || " +
                            "isMapperLayer() || " +
                            "isRepositoryLayer()",
            returning = "result")
    public void after(JoinPoint joinPoint, Object result) {
        log.trace(LOG_END,
                joinPoint.getSignature().getDeclaringType().getTypeName(),
                joinPoint.getSignature().getName(),
                result);
    }

    @AfterThrowing(value = "isControllerPackage() || " +
                           "isServicePackage() || " +
                           "isMapperLayer() || " +
                           "isRepositoryLayer()",
            throwing = "ex")
    public void afterThrow(JoinPoint joinPoint, Throwable ex) {
        log.trace(LOG_EXCEPTION,
                joinPoint.getSignature().getDeclaringType().getTypeName(),
                joinPoint.getSignature().getName(),
                ex.getMessage());
    }

    @Pointcut("within(ru.puchinets.ratesservice.service.impl.*)")
    public void isServicePackage() {
    }

    @Pointcut("within(ru.puchinets.ratesservice.controller.rest.*)")
    public void isControllerPackage() {
    }

    @Pointcut("target(org.springframework.stereotype.Repository)")
    public void isRepositoryLayer() {
    }

    @Pointcut("within(ru.puchinets.ratesservice.mapper.*)")
    public  void isMapperLayer() {
    }
}
