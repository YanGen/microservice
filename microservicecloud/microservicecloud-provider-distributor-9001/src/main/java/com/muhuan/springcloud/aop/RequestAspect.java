package com.muhuan.springcloud.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @ClassName RequestAspect
 * @Description TODO
 * @Author dong <feng.db@uniteddata.com>
 * @Date 2019/10/16
 * @Version 1.0.0
 **/
@Slf4j
@Component
@Aspect
@Order(1)
public class RequestAspect {
    private static long startTime;
    private static long endTime;

    @Pointcut("execution(public * com.atguigu.springcloud.controller.*.*(..))")
    public void print(){}

    @Before("print()")
    public void before(JoinPoint joinPoint){
        startTime = System.currentTimeMillis();
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String requestURI = request.getRequestURI();
        String remoteAddr = request.getRemoteAddr();
        String requestMethod = request.getMethod();
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        log.info("=====================↓访问日志记录↓======================");
        log.info("请求地址：" + requestURI);
        log.info("访问IP：" + remoteAddr);
        log.info("请求方式：" + requestMethod);
        log.info("调用类：" + declaringTypeName);
        log.info("调用类方法：" + methodName);
        log.info("传入参数：" + args);
    }

    @After("print()")
    public void after() {
        endTime = System.currentTimeMillis() - startTime;
    }

    @AfterReturning(pointcut = "print()", returning = "object")
    public void getAfterReturn(Object object) {
        log.info("本次访问调用耗时:{}ms", endTime);
        log.info("=====================" + LocalDateTime.now() + "===================");
    }
}