package com.ohgiraffers.section01.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* com.ohgiraffers.section01.aop.*Service.*(..))")
    public void logPointcut() {

    }

    @Before("LoggingAspect.logPointcut()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Before joinPoint.getTarget(): " + joinPoint.getTarget());

        System.out.println("Before joinPoint.getSignature(): " + joinPoint.getSignature());

        if (joinPoint.getArgs().length > 0) {
            System.out.println("Before joinPoint.getArgs()[0]: " + joinPoint.getArgs()[0]);
        }
    }

    @After("logPointcut()")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("After joinPoint.getSignature(): " + joinPoint.getSignature());
    }

    @AfterReturning(pointcut = "logPointcut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("After Returning result: " + result);
    }

    @AfterThrowing(pointcut = "logPointcut()", throwing = "exception")
    public void logAfterThrowing(Throwable exception) {
        System.out.println("After Throwing exception: " + exception);
    }

    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Arount Before: " + joinPoint.getSignature().getName());

        Object result = joinPoint.proceed();
        System.out.println("Around after: " + joinPoint.getSignature().getName());

        return result;
    }
}
