package cn.nero.community.aop;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.thymeleaf.standard.processor.AbstractStandardDoubleAttributeModifierTagProcessor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

    @AfterReturning(pointcut = "beforePoint()", returning = "result")
    public Map<String, Object> afterAdvice(Object result){
        log.info("后置通知参数:[{}]", result);
        Map<String, Object> map = new HashMap<>();
        map.put("result", "success");
        return map;
    }

}
