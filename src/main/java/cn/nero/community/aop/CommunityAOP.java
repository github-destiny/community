package cn.nero.community.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/18
 */
@Aspect
@Component
@Slf4j
public class CommunityAOP {

    @Pointcut("execution(* cn.nero.community.controller.*.*(..))")
    public void beforePoint(){

    }

    @Before("beforePoint()")
    public void beforeAdvice(JoinPoint joinPoint){
        log.info("============Log Start================");
        log.info("目标对象：" + joinPoint.getTarget());
        log.info("目标方法：" + joinPoint.getSignature().getDeclaringTypeName() +"." + joinPoint.getSignature().getName());
        log.info("传入参数：" + Arrays.toString(joinPoint.getArgs()));
        log.info("============Log End================");
    }

}
