package com.sst.log;

import com.sst.error.BusinessException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class Log {
    private Logger logger = LoggerFactory.getLogger(Log.class);

    @Pointcut("execution(* com.sst.service..*.*(..))")
    public void webLog(){
        //logger.debug("方法已执行");
    }
    /*//前置增强
    @Before("webLog()")
    public void before(JoinPoint joinPoint){
        common(joinPoint);
    }
    //最终增强
    @After("webLog()")
    public void afterLog(JoinPoint joinPoint){
        common(joinPoint);
    }

    //后置返回
    @AfterReturning(pointcut = "webLog()",returning = "result")
    public void afterReturning(JoinPoint joinPoint,Object result){
        common(joinPoint);
        logger.info("返回值："+result);
    }

    @AfterThrowing(pointcut = "webLog()",throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, BusinessException e){
        logger.info("方法"+joinPoint.getSignature()+"参数"+ Arrays.toString(joinPoint.getArgs())+"抛出异常："+e.getErrCode()+","+e.getErrMsg());
    }

    private void common(JoinPoint joinPoint){
        Object target = joinPoint.getTarget();//目标类
        Signature signature = joinPoint.getSignature();//目标方法
        Object[] args = joinPoint.getArgs();//参数列表
        String s = Arrays.toString(args);//把集合转换成String
        logger.info("执行方法："+signature+"。参数为："+s);
    }*/

  /*  @Around("webLog()")
    public Object around(ProceedingJoinPoint joinPoint){
        logger.info("前置增加：执行方法："+joinPoint.getSignature()+"。参数为："+ Arrays.toString(joinPoint.getArgs()));
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
            logger.info("返回值："+proceed);
        } catch (BusinessException e) {
            logger.info("异常代码："+e.getErrCode()+",异常信息："+e.getErrMsg());
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        logger.info(joinPoint.getSignature()+"执行完成。 后置");

        return proceed;
    }*/

    @Before("execution(* com.sst.controller.BaesController.handlerException(..))")
    public void before(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();//参数列表
        String s = Arrays.toString(args);
        logger.info(s);
    }

    @After("webLog()")
    public void afterLog(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();//参数列表
        String s = Arrays.toString(args);
        logger.info(joinPoint.getSignature()+","+s);
    }

    @AfterThrowing(pointcut = "webLog()",throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, BusinessException e){
        logger.info("方法"+joinPoint.getSignature()+"参数"+ Arrays.toString(joinPoint.getArgs())+"抛出异常："+e.getErrCode()+","+e.getErrMsg());
    }
}
