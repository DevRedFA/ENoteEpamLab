package com.epam.logging;


import com.epam.service.models.User;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

@Aspect
@Component
@Slf4j
public class UserServiceLoggingAspect {
    private static final String REQUESTED_TEMPLATE = "{} requested in {}";

    @Around(value = "execution(com.epam.service.models.User com.epam.service.implementations.UserServiceImpl.*(..))")
    public User returnUser(ProceedingJoinPoint joinPoint) throws Throwable {
        LocalTime start = LocalTime.now();
        log.info(REQUESTED_TEMPLATE, joinPoint.getSignature(), start);
        User user = (User) joinPoint.proceed();
        log.info("Returned {} in {}", user, Duration.between(LocalTime.now(), start));
        return user;
    }

    @Around(value = "execution(void com.epam.service.implementations.UserServiceImpl.*(..))")
    public void returnVoid(ProceedingJoinPoint joinPoint) throws Throwable {
        LocalTime start = LocalTime.now();
        log.info(REQUESTED_TEMPLATE, joinPoint.getSignature(), start);
        joinPoint.proceed();
        log.info("Executed in {}", Duration.between(LocalTime.now(), start));
    }

    @SuppressWarnings("unchecked")
    @Around(value = "execution(java.util.List com.epam.service.implementations.UserServiceImpl.*(..))")
    public List<User> returnAll(ProceedingJoinPoint joinPoint) throws Throwable {
        LocalTime start = LocalTime.now();
        log.info(REQUESTED_TEMPLATE, joinPoint.getSignature(), start);
        List<User> all = (List<User>) joinPoint.proceed();
        log.info("Returned {} in {}", all, Duration.between(LocalTime.now(), start));
        return all;
    }
}
