package com.epam.logging;


import com.epam.models.User;
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

    @Around(value = "execution(com.epam.models.User com.epam.services.implementations.UserServiceImpl.*(..))")
    public User returnUser(ProceedingJoinPoint joinPoint) throws Throwable {
        LocalTime start = LocalTime.now();
        log.info("{} requested in {}", joinPoint.getSignature(), start);
        User user = (User) joinPoint.proceed();
        log.info("Returned {} in {}", user, Duration.between(LocalTime.now(), start));
        return user;
    }

    @Around(value = "execution(void com.epam.services.implementations.UserServiceImpl.*(..))")
    public void returnVoid(ProceedingJoinPoint joinPoint) throws Throwable {
        LocalTime start = LocalTime.now();
        log.info("{} requested in {}", joinPoint.getSignature(), start);
        joinPoint.proceed();
        log.info("Executed in {}", Duration.between(LocalTime.now(), start));
    }

    @Around(value = "execution(java.util.List com.epam.services.implementations.UserServiceImpl.*(..))")
    public List<User> returnAll(ProceedingJoinPoint joinPoint) throws Throwable {
        LocalTime start = LocalTime.now();
        log.info("{} requested in {}", joinPoint.getSignature(), start);
        List<User> all = (List<User>) joinPoint.proceed();
        log.info("Returned {} in {}", all, Duration.between(LocalTime.now(), start));
        return all;
    }
}
