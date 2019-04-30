package com.caoyuqian.blog.aspect;

import com.caoyuqian.blog.pojo.Log;
import com.caoyuqian.blog.service.LogService;
import com.caoyuqian.blog.utils.DateUtil;
import com.caoyuqian.blog.utils.NetworkUtil;
import com.caoyuqian.blog.utils.SnowFlake;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author qian
 * @version V1.0
 * @Title: LogAsPect
 * @Package: com.caoyuqian.blog.aspect
 * @Description: 切面日志配置
 * @date 2019-04-15 11:15
 **/
@Aspect
@Component
public class LogAsPect {
    private final static Logger logger = LoggerFactory.getLogger(LogAsPect.class);
    @Autowired
    private LogService logService;

    // 表示匹配带有自定义注解的方法
    @Pointcut("@annotation(com.caoyuqian.blog.aspect.Log)")
    public void  pointcut() {}

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point){
        Object result = null;
        long beginTime = DateUtil.getNow().getTime();
        try {
            logger.info("在目标方法之前执行！");
            result = point.proceed();
            long endTime = DateUtil.getNow().getTime();
            insertLog(point,endTime-beginTime);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }
    private void insertLog(ProceedingJoinPoint point, long time) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Log log = new Log();
        SnowFlake snowFlake = new SnowFlake(2,3);

        com.caoyuqian.blog.aspect.Log userAction = method.getAnnotation(com.caoyuqian.blog.aspect.Log.class);
        if (userAction!=null){
            log.setOperation(userAction.value());
        }
        // 请求类名
        String className = point.getTarget().getClass().getName();
        // 请求方法名
        String methodName = signature.getName();
        // 请求方法参数值
        String args = Arrays.toString(point.getArgs());
        // 获取登录人id
        String userId =SecurityContextHolder.getContext().getAuthentication().getName();
        // 获取用户ip地址
        //获取用户ip地址
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        String ip = NetworkUtil.getIpAddress(request);
        log.setUserId(userId);
        log.setCreatedDate(DateUtil.getNow());
        log.setLogId(snowFlake.nextId());
        log.setIp(ip);
        logger.info("当前登陆人：{},类名:{},方法名:{},参数：{},执行时间：{},登录IP：{}",userId, className, methodName, args, time,ip);
        logService.save(log);
    }
}
