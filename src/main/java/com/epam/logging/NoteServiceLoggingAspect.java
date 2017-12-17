package com.epam.logging;


import com.epam.models.Note;
import com.epam.models.Tag;
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

    @Around(value = "execution(com.epam.models.Note com.epam.services.implementations.NoteServiceImpl.*(..))")
    public Note returnUser(ProceedingJoinPoint joinPoint) throws Throwable {
        LocalTime start = LocalTime.now();
        log.info("{} requested in {}", joinPoint.getSignature(), start);
        Note note = (Note) joinPoint.proceed();
        log.info("Returned {} in {}", note, Duration.between(LocalTime.now(), start));
        return note;
    }

    @Around(value = "execution(void com.epam.services.implementations.NoteServiceImpl.*(..))")
    public void returnVoid(ProceedingJoinPoint joinPoint) throws Throwable {
        LocalTime start = LocalTime.now();
        log.info("{} requested in {}", joinPoint.getSignature(), start);
        joinPoint.proceed();
        log.info("Executed in {}", Duration.between(LocalTime.now(), start));
    }

    @Around(value = "execution(java.util.List com.epam.services.implementations.NoteServiceImpl.*(..))")
    public List<Note> returnAll(ProceedingJoinPoint joinPoint) throws Throwable {
        LocalTime start = LocalTime.now();
        log.info("{} requested in {}", joinPoint.getSignature(), start);
        List<Note> all = (List<Note>) joinPoint.proceed();
        log.info("Returned {} in {}", all, Duration.between(LocalTime.now(), start));
        return all;
    }
}
