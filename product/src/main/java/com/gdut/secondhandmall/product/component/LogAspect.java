package com.gdut.secondhandmall.product.component;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * @author GGXian
 * @project secondhandmall
 * @createTIme 2020/9/17-21:42
 * @description 日志配置类
 **/
@Aspect
@Component
public class LogAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(* com.gdut.secondhandmall.product..*.*(..))")
    public void allComponent(){
    }

    /**
     * 记录异常信息
     * @param ex 抛出的异常对象
     * @param
     */
    @AfterThrowing(pointcut = "allComponent()", throwing = "ex")
    public void logIfThrowingException(Throwable ex){
        LOGGER.error("发生了错误：" + ex.getMessage());
    }
}
