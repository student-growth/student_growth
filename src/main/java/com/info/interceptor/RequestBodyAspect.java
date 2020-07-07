package com.info.interceptor;

import com.alibaba.fastjson.JSON;
import com.info.util.LogUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by TerryJ on 2016就/03/14.
 */
@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class RequestBodyAspect {

    //线程的时间
    private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("watch_startTime");

    // 定义切点Pointcut
    @Pointcut("execution(public * com.info.controller..*(..))")
    public void excudeService() {
    }

    //不打印参数的接口请求URI
    static Set<String> urlCheck;
    static {
        urlCheck = new HashSet<>();
    }

    /**
     * 前置通知，方法调用前被调用
     *
     * @param pjp
     */
    @Before("excudeService()")
    public void doBefore(JoinPoint pjp) throws Throwable {
        long beginTime = System.currentTimeMillis();
        startTimeThreadLocal.set(beginTime);
        Object[] args = pjp.getArgs();

        Signature signature = pjp.getSignature();
        String methodName = signature.getName();
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String url = request.getRequestURI();//URL

        Map<String, Object> inputValue = new HashMap<>();

        //请求参数处理
        for (Object o : args) {
            //过滤不打印的接口请求
            if (urlCheck.contains(url)) {
                continue;
            }

            try {
                inputValue = (Map<String, Object>) JSON.toJSON(o);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        LogUtil.info("ACTION START=======[" + getIpAddress(request) + "]=======【Method : " + methodName + "】【RequestURL : " + url + "】=============");
        LogUtil.info("请求参数=" + inputValue.toString());
    }

    /**
     * 后置返回通知
     * 这里需要注意的是:
     * 如果参数中的第一个参数为JoinPoint，则第二个参数为返回值的信息
     * 如果参数中的第一个参数不为JoinPoint，则第一个参数为returning中对应的参数
     * returning 限定了只有目标方法返回值与通知方法相应参数类型时才能执行后置返回通知，否则不执行，对于returning对应的通知方法参数为Object类型将匹配任何目标返回值
     *
     * @param jp
     * @param result
     */
    @AfterReturning(pointcut = "excudeService()", returning = "result")
    public void doAfterReturning(JoinPoint jp, Object result) {
        try {
            // 处理完请求，返回内容
            long beginTime = startTimeThreadLocal.get();
            long requestTime = System.currentTimeMillis() - beginTime;

            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
            HttpServletRequest request = sra.getRequest();

            //URL
            String url = request.getRequestURI();
            //方法名
            String methodName = jp.getSignature().getName();

            LogUtil.info("消耗的时间(毫秒):" + requestTime);
            LogUtil.info("ACTION END====================【Method : " + methodName + "】【RequestURL : " + url + "】====================");

        } catch (Exception e) {
            LogUtil.error("***操作请求日志记录失败doAfterReturning()***" + e);
        }

    }

    /**
     * 后置异常通知
     * 定义一个名字，该名字用于匹配通知实现方法的一个参数名，当目标方法抛出异常返回后，将把目标方法抛出的异常传给通知方法；
     * throwing 限定了只有目标方法抛出的异常与通知方法相应参数异常类型时才能执行后置异常通知，否则不执行，
     * 对于throwing对应的通知方法参数为Throwable类型将匹配任何异常。
     *
     * @param joinPoint
     * @param exception
     */
    @AfterThrowing(value = "excudeService()", throwing = "exception")
    public void doAfterThrowingAdvice(JoinPoint joinPoint, Throwable exception) {
        //目标方法名：
        System.out.println(joinPoint.getSignature().getName());
        if (exception instanceof NullPointerException) {
            System.out.println("发生了空指针异常!!!!!");
        }
    }

    /**
     * 后置最终通知（目标方法只要执行完了就会执行后置通知方法）
     *
     * @param joinPoint
     */
    @After("excudeService()")
    public void doAfterAdvice(JoinPoint joinPoint) {
        //System.out.println("后置通知执行了!!!!");
    }

    /**
     * 环绕通知：
     * 环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。
     * 环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
     */
	/*@Around("execution(* com.zkn.learnspringboot.web.controller..*.testAround*(..))")
	public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
		System.out.println("环绕通知的目标方法名："+proceedingJoinPoint.getSignature().getName());
		try {
			Object obj = proceedingJoinPoint.proceed();
			return obj;
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
		return null;
	}*/
    public String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
