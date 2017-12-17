package com.epam.logging;


import com.epam.models.Note;
import com.epam.models.Notebook;
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
public class NotebookServiceLoggingAspect {

    @Around(value = "execution(com.epam.models.Notebook com.epam.services.implementations.NotebookServiceImpl.*(..))")
    public Notebook returnUser(ProceedingJoinPoint joinPoint) throws Throwable {
        LocalTime start = LocalTime.now();
        log.info("{} requested in {}", joinPoint.getSignature(), start);
        Notebook notebook = (Notebook) joinPoint.proceed();
        log.info("Returned {} in {}", notebook, Duration.between(LocalTime.now(), start));
        return notebook;
    }

    @Around(value = "execution(void com.epam.services.implementations.NotebookServiceImpl.*(..))")
    public void returnVoid(ProceedingJoinPoint joinPoint) throws Throwable {
        LocalTime start = LocalTime.now();
        log.info("{} requested in {}", joinPoint.getSignature(), start);
        joinPoint.proceed();
        log.info("Executed in {}", Duration.between(LocalTime.now(), start));
    }

    @Around(value = "execution(java.util.List com.epam.services.implementations.NotebookServiceImpl.*(..))")
    public List<Notebook> returnAll(ProceedingJoinPoint joinPoint) throws Throwable {
        LocalTime start = LocalTime.now();
        log.info("{} requested in {}", joinPoint.getSignature(), start);
        List<Notebook> all = (List<Notebook>) joinPoint.proceed();
        log.info("Returned {} in {}", all, Duration.between(LocalTime.now(), start));
        return all;
    }
}
