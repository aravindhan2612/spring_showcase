package com.aravind.JobApp.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    // return type, class name, method name, arguments
    @Before("execution(* com.aravind.JobApp.service.JobService.*(..))")
    public  void methodCall(JoinPoint jp) {
        LOGGER.info("Method called "+ jp.getSignature().getName());
    }

    @After("execution(* com.aravind.JobApp.service.JobService.*(..))")
    public  void logMethodExecute(JoinPoint jp) {
        LOGGER.info("Method executed "+ jp.getSignature().getName());
    }

    @AfterThrowing("execution(* com.aravind.JobApp.service.JobService.*(..))")
    public  void logMethodCrash(JoinPoint jp) {
        LOGGER.info("Method some issues "+ jp.getSignature().getName());
    }
}
