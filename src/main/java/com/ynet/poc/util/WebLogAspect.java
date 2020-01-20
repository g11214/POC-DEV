package com.ynet.poc.util;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ：Tong
 * @date ：Created in 2020/1/20 12:53
 * @description：
 * @version: $
 */
@Aspect
@Component
@Slf4j
public class WebLogAspect {

    @Pointcut("execution( * com.ynet.poc.controller.*.*(..))")
    public void controllerLog() {
    }

    @Pointcut("execution( * com.ynet.poc.service.*.*(..))")
    public void serviceLog() {
    }

    @Before("controllerLog()||serviceLog()")
    public void doBefore(JoinPoint joinPoint) {
        log.info(joinPoint.getTarget().getClass().getName() + ".  " + joinPoint.getSignature().getName());

        for (Object object : joinPoint.getArgs()) {
            if (
                    object instanceof MultipartFile
                            || object instanceof HttpServletRequest
                            || object instanceof HttpServletResponse
            ) {
                continue;
            }
            try {
                log.info(" 请求的参数为: " + JSON.toJSONString(object));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @AfterReturning(returning = "response", pointcut = "controllerLog()||serviceLog()")
    public void doAfterReturning(JoinPoint joinPoint, Object response) throws Throwable {
        log.info(joinPoint.getTarget().getClass().getName() + ".  " + joinPoint.getSignature().getName());
        if (response != null) {
            log.info("返回的参数为 : " + JSON.toJSONString(response));
        }
    }
}
