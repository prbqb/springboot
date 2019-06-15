package com.prb.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class StatisticsTime {

    @Pointcut("@annotation(com.prb.annotation.StatisticsTimeLog)")
    public void statisticPointCut(){}


    @Around("statisticPointCut()")
    public Object statisticMethodExecutor(ProceedingJoinPoint proceedingJoinPoint){

        Object result = null;
        try {
            log.info("=================统计时间开始了====================");
            Long startTime = System.currentTimeMillis();
            result = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
            Long endTime = System.currentTimeMillis();

            log.info("method {} is exec finish",proceedingJoinPoint.getSignature());
            log.info("花费了 {} 时间",endTime - startTime);
            log.info("返回结果是：{}",result );
        }catch (Throwable e){

        }

        return result;


    }

}
