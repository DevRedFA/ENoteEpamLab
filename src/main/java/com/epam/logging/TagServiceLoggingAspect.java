package com.epam.logging;


import com.epam.models.Tag;
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
public class TagServiceLoggingAspect {

    @Around(value = "execution(com.epam.models.Tag com.epam.services.implementations.TagServiceImpl.*(..))")
    public Tag returnUser(ProceedingJoinPoint joinPoint) throws Throwable {
        LocalTime start = LocalTime.now();
        log.info("{} requested in {}", joinPoint.getSignature(), start);
        Tag tag = (Tag) joinPoint.proceed();
        log.info("Returned {} in {}", tag, Duration.between(LocalTime.now(), start));
        return tag;
    }

    @Around(value = "execution(void com.epam.services.implementations.TagServiceImpl.*(..))")
    public void returnVoid(ProceedingJoinPoint joinPoint) throws Throwable {
        LocalTime start = LocalTime.now();
        log.info("{} requested in {}", joinPoint.getSignature(), start);
        joinPoint.proceed();
        log.info("Executed in {}", Duration.between(LocalTime.now(), start));
    }

    @Around(value = "execution(java.util.List com.epam.services.implementations.TagServiceImpl.*(..))")
    public List<Tag> returnAll(ProceedingJoinPoint joinPoint) throws Throwable {
        LocalTime start = LocalTime.now();
        log.info("{} requested in {}", joinPoint.getSignature(), start);
        List<Tag> all = (List<Tag>) joinPoint.proceed();
        log.info("Returned {} in {}", all, Duration.between(LocalTime.now(), start));
        return all;
    }
}
