package cn.nero.community.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

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

    @Pointcut("execution(* cn.nero.community.service.*.*(..))")
    public void afterServicePoint(){

    }

    @Before("beforePoint()")
    public void beforeAdvice(JoinPoint joinPoint){
        log.info("============Log Start================");
        log.info("目标对象：" + joinPoint.getTarget());
        log.info("目标方法：" + joinPoint.getSignature().getDeclaringTypeName() +"." + joinPoint.getSignature().getName());
        log.info("传入参数：" + Arrays.toString(joinPoint.getArgs()));
        log.info("============Log End================");
    }

    @AfterReturning(pointcut = "afterServicePoint()", returning = "result")
    public void afterAdvice(Object result){
        if (result instanceof List) {
            List<Object> list = (List<Object>) result;
            log.info("服务层查询结果数量:[{}]", list.size());
        } else {
            log.info("服务层查询结果:[{}]", result);
        }
    }

}
