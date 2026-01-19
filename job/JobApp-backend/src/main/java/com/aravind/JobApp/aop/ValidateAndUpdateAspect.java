package com.aravind.JobApp.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidateAndUpdateAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidateAndUpdateAspect.class);

    @Around("execution(* com.aravind.JobApp.service.JobService.getJob(..)) && args(postId)")
    public Object validateAndUpdate(ProceedingJoinPoint jp, int postId) throws Throwable {

        if(postId < 0) {
            LOGGER.info("Inside negative value " + postId);
            postId = - postId;
            LOGGER.info("updated value" + postId);
        }

       Object obj =  jp.proceed(new Object[] {postId});
        return obj;
    }
}
