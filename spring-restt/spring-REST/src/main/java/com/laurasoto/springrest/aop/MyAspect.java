package com.laurasoto.springrest.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {
    private static final Logger log = LoggerFactory.getLogger(MyAspect.class);
    @Before("execution(* com.laurasoto.springrest.aop.TargetObject.*(..))")
    public void before(JoinPoint joinPoint){
        log.info("method name {}",joinPoint.getSignature().getName());
        log.info("object type {}",joinPoint.getSignature().getDeclaringType());
        log.info("Modifiers {}",joinPoint.getSignature().getModifiers());
        log.info("Arguments {}",joinPoint.getArgs());
        log.info("Before Advice");
    }
}
