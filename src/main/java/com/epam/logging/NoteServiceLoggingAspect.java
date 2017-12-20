package com.epam.logging;


import com.epam.service.models.Note;
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
public class NoteServiceLoggingAspect {
    private static final String REQUESTED_TEMPLATE = "{} requested in {}";

    @Around(value = "execution(com.epam.service.models.Note com.epam.service.implementations.NoteServiceImpl.*(..))")
    public Note returnUser(ProceedingJoinPoint joinPoint) throws Throwable {
        LocalTime start = LocalTime.now();
        log.info(REQUESTED_TEMPLATE, joinPoint.getSignature(), start);
        Note note = (Note) joinPoint.proceed();
        log.info("Returned {} in {}", note, Duration.between(LocalTime.now(), start));
        return note;
    }

    @Around(value = "execution(void com.epam.service.implementations.NoteServiceImpl.*(..))")
    public void returnVoid(ProceedingJoinPoint joinPoint) throws Throwable {
        LocalTime start = LocalTime.now();
        log.info(REQUESTED_TEMPLATE, joinPoint.getSignature(), start);
        joinPoint.proceed();
        log.info("Executed in {}", Duration.between(LocalTime.now(), start));
    }

    @Around(value = "execution(java.util.List com.epam.service.implementations.NoteServiceImpl.*(..))")
    public List<Note> returnAll(ProceedingJoinPoint joinPoint) throws Throwable {
        LocalTime start = LocalTime.now();
        log.info(REQUESTED_TEMPLATE, joinPoint.getSignature(), start);
        List<Note> all = (List<Note>) joinPoint.proceed();
        log.info("Returned {} in {}", all, Duration.between(LocalTime.now(), start));
        return all;
    }
}
